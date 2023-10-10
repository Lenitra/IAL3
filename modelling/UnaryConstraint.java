package modelling;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Cette classe représente une contrainte unaire sur une variable (c'est-à-dire une contrainte portant sur une seule variable)
public class UnaryConstraint implements Constraint {
    
        private Variable v;
        protected Set<Object> value;
    
        /**
         * Constructeur de la classe UnaryConstraint
         * @param v        variable sur laquelle porte la contrainte
         * @param value    l'ensemble des valeurs de la variable
         */
        public UnaryConstraint(Variable v, Set<Object> value) {
            this.v = v;
            this.value = value;
        }
    
        /**
         * Recupere l'ensemble des variables sur lesquelles porte la contrainte
         * @return Un ensemble contenant v
         */
        public Set<Variable> getScope() {
            Set<Variable> scope = new HashSet<>();
            scope.add(this.v);
            return scope;
        }
    
        /**
         * Vérifie si cette contrainte est satisfaite par l'instantiation donnée
         * @param instantiation instantiation a verifier
         * @return true si l'instantiation satisfait la contrainte, false sinon
         * @throws IllegalArgumentException si l'instantiation ne contient pas toutes les variables nécessaires
         */
        public boolean isSatisfiedBy(Map<Variable, Object> instantiation) {
            // On obtient la valeur de la variable dans l'instantiation
            Object name = instantiation.get(this.v);
            if (name == null){
                throw new IllegalArgumentException("L'instantiation ne contient pas toutes les variables nécessaires.");
            }
            // On vérifie que la valeur est contenue dans l'ensemble des valeurs de la variable
            return this.value.contains(name);
        }
}
