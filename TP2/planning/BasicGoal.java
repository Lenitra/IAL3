package planning;

//Créer une classe nommée BasicGoal implémentant l’interface Goal, pour représenter les
// buts basiques. Munir cette classe d’un constructeur prenant en argument une instanciation partielle des
// variables (de type Map<Variable, Object>).

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

    //add a successor method to Goal

    public Map<Variable, Object> successor(Map<Variable, Object> state) {
        Map<Variable, Object> newState = state;
        for (Variable v : this.instantiation.keySet()) {
            newState.put(v, this.instantiation.get(v));
        }
        return newState;
    }

}