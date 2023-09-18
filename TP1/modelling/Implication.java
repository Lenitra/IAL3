package modelling;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Écrire une classe nommée Implication, pour représenter les contraintes de la forme (v1 ∈
// S1) → (v2 ∈ S2), où v1 et v2 sont des variables, et S1 et S2 des sous-ensembles de leurs domaines
// respectifs. Une telle contrainte est satisfaite si, dès lors qu’une valeur de S1 est affectée à v1, une valeur
// de S2 est affectée à v2 (et elle est satisfaite si ce n’est pas une valeur de S1 qui est affectée à v1, quelle
// que soit la valeur affectée à v2). Munir la classe d’un constructeur prenant en arguments, dans cet ordre :
// une instance de Variable (pour v1), une instance de Set<Object> (pour S1), une instance de Variable
// pour v2, et une instance de Set<Object> pour S2. Faire implémenter l’interface Constraint à la classe
// Implication.


public class Implication implements Constraint {

    private Variable v1;
    private Variable v2;
    private Set<Object> S1;
    private Set<Object> S2;

    public Implication(Variable v1, Set<Object> S1, Variable v2, Set<Object> S2) {
        this.v1 = v1;
        this.v2 = v2;
        this.S1 = S1;
        this.S2 = S2;
    }

    @Override
    public Set<Variable> getScope() {
        Set<Variable> scope = new HashSet<>();
        scope.add(this.v1);
        scope.add(this.v2);
        return scope;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, Object> instantiation) {
        if (!instantiation.containsKey(v1) || !instantiation.containsKey(v2)) {
            throw new IllegalArgumentException("L'instantiation ne contient pas toutes les variables nécessaires.");
        }

        if (S1.contains(instantiation.get(v1))) {
            return S2.contains(instantiation.get(v2));
        }
        return true;
    }

}