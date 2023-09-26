package planning;

import java.util.*;
import modelling.Variable;

public class BFSPlanner implements Planner {

    private Map<Variable, Object> initialState;
    private Set<Action> actions;
    private Goal goal;

    public BFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
    }

    public List<Action> plan() {
        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
        Map<Map<Variable, Object>, Action> plan = new HashMap<>();
        ArrayList<Map<Variable, Object>> closed = new ArrayList<>();
        Queue<Map<Variable, Object>> open = new LinkedList<>();
        open.add(initialState);
        father.put(initialState, null);
        plan.put(initialState, null);
        while (!open.isEmpty()) {
            Map<Variable, Object> instantiation = open.poll();
            closed.add(instantiation);
            if (satisfies(instantiation, goal)) {
                return getBFSPlan(father, plan, instantiation);
            }
            for (Action action : actions) {
                if (isApplicable(action, instantiation)) {
                    Map<Variable, Object> next = apply(action, instantiation);
                    if (!father.containsKey(next)) {
                        father.put(next, instantiation);
                        plan.put(next, action);
                        open.add(next);
                    }
                }
            }
        }
        return null;
    }

    private boolean satisfies(Map<Variable, Object> state, Goal goal) {
        return goal.isSatisfiedBy(state);
    }

    private boolean isApplicable(Action action, Map<Variable, Object> state) {
        return action.isApplicable(state);
    }

    private Map<Variable, Object> apply(Action action, Map<Variable, Object> state) {
        return action.successor(state);
    }

    public List<Action> getBFSPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father, Map<Map<Variable, Object>, Action> plan, Map<Variable, Object> current) {
        List<Action> result = new ArrayList<>();
        while (current != null) {
            Action action = plan.get(current);
            if (action != null) {
                result.add(0, action);
            }
            current = father.get(current);
        }
        return result;
    }

    @Override
    public Map<Variable, Object> getInitialState() {
       return this.initialState;
    }

    @Override
    public Set<Action> getActions() {
        return this.actions;
    }

    @Override
    public Goal getGoal() {
        return this.goal;
    }
}