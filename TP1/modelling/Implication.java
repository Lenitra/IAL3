package modelling;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Cette classe représente une contrainte d'implication entre deux variables
public class Implication implements Constraint {

    private Variable v1;
    private Variable v2;
    private Set<Object> S1;
    private Set<Object> S2;

    /**
     * Constructeur de la classe Implication
     * @param v1    la première variable
     * @param S1    l'ensemble des valeurs de la première variable
     * @param v2    la deuxième variable
     * @param S2    l'ensemble des valeurs de la deuxième variable
     */
    public Implication(Variable v1, Set<Object> S1, Variable v2, Set<Object> S2) {
        this.v1 = v1;
        this.v2 = v2;
        this.S1 = S1;
        this.S2 = S2;
    }

    /**
     * Recupere l'ensemble des variables sur lesquelles porte la contrainte
     * @return Un ensemble contenant v1 et v2
     */
    @Override
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
    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> instantiation) {
        if (!instantiation.containsKey(v1) || !instantiation.containsKey(v2)) {
            throw new IllegalArgumentException("L'instantiation ne contient pas toutes les variables nécessaires.");
        }

        // On vérifie que la première variable est bien dans S1
        if (S1.contains(instantiation.get(v1))) {
            // On vérifie que la deuxième variable est bien dans S2
            return S2.contains(instantiation.get(v2));
        }
        // Si la première variable n'est pas dans S1, on renvoie forcement true
        return true;
    }

}