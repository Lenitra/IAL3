package modelling;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UnaryConstraint implements Constraint {
    
        private Variable v;
        protected Set<Object> value;
    
        public UnaryConstraint(Variable v, Set<Object> value) {
            this.v = v;
            this.value = value;
        }
    
        public Set<Variable> getScope() {
            Set<Variable> scope = new HashSet<>();
            scope.add(this.v);
            return scope;
        }
    
        public boolean isSatisfiedBy(Map<Variable, Object> instantiation) {
            Object name = instantiation.get(this.v);
            if (name == null){
                throw new IllegalArgumentException("L'instantiation ne contient pas toutes les variables nécessaires.");
            }
    
            return this.value.contains(name);
        }
}
