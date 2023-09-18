package modelling;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DifferenceConstraint implements Constraint {

    private Variable v1;
    private Variable v2;

    public DifferenceConstraint(Variable v1, Variable v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Set<Variable> getScope() {
        Set<Variable> scope = new HashSet<>();
        scope.add(this.v1);
        scope.add(this.v2);
        return scope;
    }

    public boolean isSatisfiedBy(Map<Variable, Object> instantiation) {
        if (!instantiation.containsKey(v1) || !instantiation.containsKey(v2)) {
            throw new IllegalArgumentException("L'instantiation ne contient pas toutes les variables nécessaires.");
        }

        return instantiation.get(v1) != instantiation.get(v2);
    }

}