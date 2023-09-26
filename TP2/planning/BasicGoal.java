package planning;

import java.util.Map;
import modelling.Variable;

public class BasicGoal implements Goal {

    private Map<Variable, Object> instantiation;

    public BasicGoal(Map<Variable, Object> instantiation) {
        this.instantiation = instantiation;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> state) {
        for (Variable v : instantiation.keySet()) {
            if (!state.containsKey(v) || !state.get(v).equals(instantiation.get(v))) {
                return false;
            }
        }
        return true;
    }

    public Map<Variable, Object> successor(Map<Variable, Object> state) {
        Map<Variable, Object> newState = state;
        for (Variable v : this.instantiation.keySet()) {
            newState.put(v, this.instantiation.get(v));
        }
        return newState;
    }

}