package planning;

import java.util.Map;
import modelling.Variable;

// Implémentation d'un objectif à atteindre
public interface Goal {
        
        /**
         * Vérifie si l'objectif est satisfait par un état
         * @param state L'état à vérifier
         * @return vrai ou faux en fonction de si l'objectif est satisfait ou non
         */
        public boolean isSatisfiedBy(Map<Variable, Object> state);
}
