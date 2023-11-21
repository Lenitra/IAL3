package planning;

import java.util.Map;
import modelling.Variable;

// Implémentation d'un objectif à atteindre
public class BasicGoal implements Goal {

    private Map<Variable, Object> instantiation;

    /**
     * Constructeur d'un objectif
     * @param instantiation L'instantiation de l'objectif
     */
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

    /**
     * Genere un nouvel etat en ajoutant les variables et valeurs de cette instantiation a l'etat courant
     * @param state L'etat courant sur lequel appliquer l'action
     * @return Le nouvel etat resultant de l'application de l'action
     */
    public Map<Variable, Object> successor(Map<Variable, Object> state) {
        Map<Variable, Object> newState = state;
        for (Variable v : this.instantiation.keySet()) {
            newState.put(v, this.instantiation.get(v));
        }
        return newState;
    }

    @Override
    public String toString() {
        //etat final
        String s = "";
        for (Variable v : instantiation.keySet()) {
            s += "\n"+ v.getName() + " = " + instantiation.get(v);
        }
        return s;

    }
}