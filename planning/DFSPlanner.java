package planning;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelling.Variable;

//Implémentation d'un planificateur de recherche en profondeur (DFS)
public class DFSPlanner implements Planner {

    private Map<Variable, Object> initialState;
    private Set<Action> actions;
    private Goal goal;
    private Set<Map<Variable, Object>> visited;

    /**
     * Constructeur pour DFSPlanner
     * @param initialState  Etat inital du probleme de planification
     * @param actions       Ensemble des actions possibles
     * @param goal          Objectif a atteindre
     */
    public DFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.visited = new HashSet<>();
    }

    /**
     * On planifie une séquence d'actions pour atteindre l'objectif à partir de l'état initial
     * 
     * @return Une liste d'actions représentant le plan pour atteindre l'objectif ou null si aucun plan n'est trouvé (pas de solution   )
     */
    @Override
    public List<Action> plan() {
        visited.clear();
        return plan(initialState, new ArrayList<>());
    }

    // On récupère un état et un plan et on retourne une liste d'actions
    private List<Action> plan(Map<Variable, Object> state, List<Action> plan) {
        // si l'état actuel satisfait l'objectif on retourne le plan
        if (goal.isSatisfiedBy(state)) {
            return plan;
        }

        // sinon on ajoute l'état actuel à la liste des états visités
        // ca permet d'éviter une boucle infinie !!!
        visited.add(state);

        // On parcourt toutes les actions possibles
        for (Action action : actions) {
            // si l'action est applicable à l'état actuel
            if (action.isApplicable(state)) {
                // on récupère l'état suivant
                Map<Variable, Object> successor = action.successor(state);

                // si l'état suivant n'a pas déjà été visité
                if (!visited.contains(successor)) {
                    // on ajoute l'action au plan 
                    List<Action> newPlan = new ArrayList<>(plan);
                    newPlan.add(action);
                    // on appelle la fonction récursivement à partir de l'état suivant
                    List<Action> result = plan(successor, newPlan);
                    // si on trouve une solution, on retourne le plan
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        // si aucun plan n'est trouvé on retourne null
        return null;
    }

    public int getNombresNoeuds()
    {
        return visited.size();
    }

    @Override
    public Goal getGoal() {
        return goal;
    }

    @Override
    public Map<Variable, Object> getInitialState() {
        return initialState;
    }

    @Override
    public Set<Action> getActions() {
        return actions;
    }

}