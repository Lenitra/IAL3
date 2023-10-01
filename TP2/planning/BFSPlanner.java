package planning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import modelling.Variable;

//Implémentation d'un planificateur de recherche en largeur (BFS)
public class BFSPlanner implements Planner {

    private Map<Variable, Object> initialState;
    private Set<Action> actions;
    private Goal goal;

    /**
     * Constructeur pour BFSPlanner
     * @param initialState  Etat inital du probleme de planification
     * @param actions       Ensemble des actions possibles
     * @param goal          Objectif a atteindre
     */
    public BFSPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
    }

    /**
     * Planification d'une séquence d'action afin d'atteindre l'objectif à partir de l'état initial
     * 
     * @return Une liste d'actions représentant le plan pour atteindre l'objectif ou null si aucun plan n'est trouvé (pas de solution)
     */
    public List<Action> plan() {

        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<>();
        Map<Map<Variable, Object>, Action> plan = new HashMap<>();
        ArrayList<Map<Variable, Object>> closed = new ArrayList<>();
        Queue<Map<Variable, Object>> open = new LinkedList<>();
        
        // on initialise les HashMaps avec l'état initial
        open.add(initialState);
        father.put(initialState, null);
        plan.put(initialState, null);

        // tant que la liste des ouverts n'est pas vide
        while (!open.isEmpty()) {
            // on récupère le premier élement de la liste des ouverts
            Map<Variable, Object> instantiation = open.poll();
            //on place l'état actuel dans la liste des fermés
            closed.add(instantiation);
            // on vérifie si l'état actuel satisfait l'objectif
            if (satisfies(instantiation, goal)) {
                return getBFSPlan(father, plan, instantiation);
            }
            // On parcourt toutes les actions possibles
            for (Action action : actions) {
                // si l'action est applicable à l'état actuel
                if (isApplicable(action, instantiation)) {
                    // on récupère l'état suivant
                    Map<Variable, Object> next = apply(action, instantiation);

                    // si l'état suivant n'a pas déjà été visité
                    if (!father.containsKey(next)) {
                        // on l'ajoute à la liste des ouverts
                        father.put(next, instantiation);
                        plan.put(next, action);
                        open.add(next);
                    }
                }
            }
        }
        return null;
    }

    /**
     * On vérifie si l'état satisfait l'objectif
     * @param state Etat à vérifier
     * @param goal  Objectif à atteindre
     * @return true ou false en fonction de si l'état satisfait ou non l'ojectif
     */
    private boolean satisfies(Map<Variable, Object> state, Goal goal) {
        return goal.isSatisfiedBy(state);
    }

    /**
     * On vérifie si l'action est applicable à l'état donné
     * @param action Action à vérifier
     * @param state  Etat à vérifier
     * @return true ou false en fonction de si l'action est applicable ou non à l'état
     */
    private boolean isApplicable(Action action, Map<Variable, Object> state) {
        return action.isApplicable(state);
    }

    /**
     * On applique une action donnée à l'état donné pour obtenir l'état suivant
     * @param action Action à appliquer
     * @param state  Etat sur lequel appliquer l'action
     * @return Le nouvel état après application de l'action
     */
    private Map<Variable, Object> apply(Action action, Map<Variable, Object> state) {
        return action.successor(state);
    }

    /**
     * Construit la séquence d'actions à partir du plan trouvé par BFS
     * @param father    Une map des parents pour retracer le plan
     * @param plan      Une map des actions associées à chaque état
     * @param current   L'état actuel à partir duquel on construit le plan
     * @return La liste d'actions représentant le plan
     */
    public List<Action> getBFSPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father, Map<Map<Variable, Object>, Action> plan, Map<Variable, Object> current) {
        List<Action> result = new ArrayList<>();
        while (current != null) {
            Action action = plan.get(current);
            if (action != null) {
                result.add(0, action);
            }
            current = father.get(current);
        }
        return result;
    }

    @Override
    public Map<Variable, Object> getInitialState() {
       return this.initialState;
    }

    @Override
    public Set<Action> getActions() {
        return this.actions;
    }

    @Override
    public Goal getGoal() {
        return this.goal;
    }
}