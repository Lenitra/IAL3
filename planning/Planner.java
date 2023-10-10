package planning;

import java.util.List;
import java.util.Map;
import java.util.Set;

import modelling.Variable;

/**
 * L'interface Planner définit les méthodes nécessaires pour un planificateur.
 * Un planificateur est utilisé pour générer un plan d'actions permettant d'atteindre un certain objectif.
 */
public interface Planner {

    /**
     * Genere un plan pour atteindre l'objectif
     * @return Une liste d'actions représentant le plan pour atteindre l'objectif 
     */
    public List<Action> plan();

    /**
     * Recupere l'objectif à atteindre
     * @return L'objectif à atteindre
     */
    public Goal getGoal();

    /**
     * Recupere l'etat initial
     * @return Un ensemble de variables et de valeurs initiales
     */
    public Map<Variable, Object> getInitialState();

    /**
     * Recupere l'ensemble des actions possibles pour le planificateur
     * @return Un ensemble d'actions possibles que le planificateur peut utiliser
     */
    public Set<Action> getActions();

    /**
     * Recupere le nombre de noeuds explorés par le planificateur
     * @return Le nombre de noeuds explorés par le planificateur
     */
    public int getNombresNoeuds();
}
