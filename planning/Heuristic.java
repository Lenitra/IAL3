package planning;

import java.util.Map;
import modelling.Variable;

// Implémentation d'une heuristique
public interface Heuristic {
    
    /**
     * Estime le coût restant pour atteindre l'objectif à partir d'un état
     * 
     * @param state L'état à partir duquel estimer le coût restant
     * @return      Le coût restant pour atteindre l'objectif à partir de l'état
     */
    public float estimate(Map<Variable, Object> state);

    
}
