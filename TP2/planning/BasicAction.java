package planning;

import java.util.HashMap;
import java.util.Map;

import modelling.Variable;

// Implémentation d'une action de base dans un système de planification
public class BasicAction implements Action{

    private Map<Variable, Object> precondition;
    private Map<Variable, Object> effect;
    private int cout;

    /**
     * Construit une action de base avec les préconditions, les effets et le coût donnés
     * @param precondition  Les preconditions de l'action qui doivent être satisfaites pour que l'action soit applicable
     * @param effect        Les effets de l'action qui modifient l'état apres l'application de l'action
     * @param cout          Le coût de l'action
     */
    public BasicAction(Map<Variable, Object> precondition, Map<Variable, Object> effect, int cout) {
        this.precondition = precondition;
        this.effect = effect;
        this.cout = cout;
    }

    /**
     * On vérifie si l'action est applicable à l'état donné
     * 
     * @param state L'état sur lequel vérifier l'applicabilité de l'action
     * @return      vrai ou faux en fonction de si l'action est applicable ou non
     */
    public boolean isApplicable(Map<Variable, Object> state) {
        // On vérifie si l'état contient toutes les préconditions de l'action
        for (Variable v : this.precondition.keySet()) {
            if (!state.containsKey(v) || !state.get(v).equals(this.precondition.get(v))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calcule l'état résultant après l'exécution de l'action dans un état donné
     * @param state L'état courant sur lequel appliquer l'action
     * @return      Le nouvel état résultant de l'application de l'action
     */
    public Map<Variable, Object> successor(Map<Variable, Object> state) {
        // On copie l'état courant
        Map<Variable, Object> newState = new HashMap<>(state);
        // On ajoute les effets de l'action à l'état courant
        for (Variable v : this.effect.keySet()) {
            newState.put(v, this.effect.get(v));
        }
        return newState;
    }


    public int getCost() {
        return this.cout;
    }
}
