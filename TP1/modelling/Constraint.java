package modelling;

import java.util.Map;
import java.util.Set;

// Cette interface représente une contrainte dans un modèle
public interface Constraint {

    /**
     * Recupere l'ensemble des variables sur lesquelles porte la contrainte
     * @return Un ensemble de variables impliquees dans la contrainte
     */
    public Set<Variable> getScope();

    /**
     * Vérifie si l'instantiation donnée satisfait cette contrainte
     * @param instantiation l'instantiation à vérifier
     * @return true si l'instantiation satisfait la contrainte, false sinon
     */
    public boolean isSatisfiedBy(Map<Variable, Object> instantiation);

}
