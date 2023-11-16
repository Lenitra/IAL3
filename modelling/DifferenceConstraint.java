package modelling;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Cette classe représente une contrainte de différence entre deux variables v1 et v2
public class DifferenceConstraint implements Constraint {

    private Variable v1;
    private Variable v2;

    /**
     * Constructeur de la classe DifferenceConstraint
     * @param v1 la première variable
     * @param v2 la deuxième variable
     */
    public DifferenceConstraint(Variable v1, Variable v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    /**
     * Recupere l'ensemble des variables sur lesquelles porte la contrainte
     * @return Un ensemble contenant v1 et v2
     */
    public Set<Variable> getScope() {
        Set<Variable> scope = new HashSet<>();
        scope.add(this.v1);
        scope.add(this.v2);
        return scope;
    }

    /**
     * Vérifie si l'instantiation donnée satisfait cette contrainte
     * @param instantiation une instantiation des variables v1 et v2
     * @return true si l'instantiation satisfait la contrainte, false sinon
     * @throws IllegalArgumentException si l'instantiation ne contient pas toutes les variables nécessaires
     */
    public boolean isSatisfiedBy(Map<Variable, Object> instantiation) {
        if (!instantiation.containsKey(v1) || !instantiation.containsKey(v2)) {
            throw new IllegalArgumentException("L'instantiation ne contient pas toutes les variables nécessaires.");
        }
        // On vérifie que les variables sont bien différentes
        return instantiation.get(v1) != instantiation.get(v2);
    }

    // toString
    public String toString() {
        return "DifferenceConstraint(" + this.v1 + ", " + this.v2 + ")";
    }

}