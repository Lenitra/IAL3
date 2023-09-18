package planning;

import java.util.HashMap;
import java.util.Map;

import modelling.Variable;

public class BasicAction implements Action{

    private Map<Variable, Object> precondition;
    private Map<Variable, Object> effect;
    private int cout;

    public BasicAction(Map<Variable, Object> precondition, Map<Variable, Object> effect, int cout) {
        this.precondition = precondition;
        this.effect = effect;
        this.cout = cout;
    }

    public boolean isApplicable(Map<Variable, Object> state) {
        for (Variable v : this.precondition.keySet()) {
            if (!state.containsKey(v) || !state.get(v).equals(this.precondition.get(v))) {
                return false;
            }
        }
        return true;
    }

    public Map<Variable, Object> successor(Map<Variable, Object> state) {
        Map<Variable, Object> newState = new HashMap<>(state);
        for (Variable v : this.effect.keySet()) {
            newState.put(v, this.effect.get(v));
        }
        return newState;
    }


    public int getCost() {
        return this.cout;
    }
}
