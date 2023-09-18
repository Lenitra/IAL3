package planning;

import java.util.*;

import modelling.Variable;

public class DijkstraPlanner implements Planner {

    private Map<Variable, Object> initialState;
    private Set<Action> actions;
    private Goal goal;
    private Set<Map<Variable, Object>> visited;

    public DijkstraPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.visited = new HashSet<>();
    }

    @Override
    public List<Action> plan() {
        visited.clear(); // Assurez-vous de réinitialiser la liste à chaque appel
        return plan(initialState, new ArrayList<>());
    }

    private List<Action> plan(Map<Variable, Object> state, List<Action> plan) {
        if (goal.isSatisfiedBy(state)) {
            return plan;
        }

        visited.add(state); // Ajoutez l'état actuel à la liste des états visités

        for (Action action : actions) {
            if (action.isApplicable(state)) {
                Map<Variable, Object> successor = action.successor(state);

                // Vérifiez si l'état suivant a déjà été visité
                if (!visited.contains(successor)) {
                    List<Action> newPlan = new ArrayList<>(plan);
                    newPlan.add(action);
                    List<Action> result = plan(successor, newPlan);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Goal getGoal() {
        return goal;
    }

    @Override
    public Map<Variable, Object> getInitialState() {
        return initialState;
    }

    @Override
    public Set<Action> getActions() {
        return actions;
    }

}