
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
        Queue<Map<Variable, Object>> queue = new LinkedList<>();
        queue.add(initialState);
        father.put(initialState, null);
        plan.put(initialState, null);
        while (!queue.isEmpty()) {
            Map<Variable, Object> current = queue.poll();
            if (satisfies(current, goal)) {
                return getBFSPlan(father, plan, current);
            }
            for (Action action : actions) {
                if (isApplicable(action, current)) {
                    Map<Variable, Object> next = apply(action, current);
                    if (!father.containsKey(next)) {
                        father.put(next, current);
                        plan.put(next, action);
                        queue.add(next);
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

    private List<Action> getBFSPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father, Map<Map<Variable, Object>, Action> plan, Map<Variable, Object> current) {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInitialState'");
    }

    @Override
    public Set<Action> getActions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActions'");
    }

    @Override
    public Goal getGoal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGoal'");
    }
}
