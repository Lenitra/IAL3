package planning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

import modelling.Variable;

public class AStarPlanner implements Planner {
    
    private Map<Variable, Object> initialState;
    private Set<Action> actions;
    private Goal goal;
    private Heuristic heuristic;
    private BFSPlanner bfs = new BFSPlanner(initialState, actions, goal);
    private int nombresNoeuds;
    
    /**
     * Construit un AStarPlanner avec un état initial, un ensemble d'actions, l'objectif à atteindre et la fonction heuristique
     * 
     * @param initialState L'état initial du problème
     * @param actions      L'ensemble d'actions disponibles qui peuvent être appliquées aux états
     * @param goal         L'état objectif que le planificateur vise à atteindre
     * @param heuristic    La fonction heuristique utilisée pour estimer le coût restant pour atteindre l'objectif
     */
    public AStarPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal, Heuristic heuristic) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.heuristic = heuristic;
        this.nombresNoeuds = 0;
    }

    /**
     * Effectue une recherche AStar pour trouver un plan permettant d'atteindre l'objectif à partir de l'état initial
     * 
     * @param father   Une Map qui stocke la relation parent-enfant des états
     * @param plan     Une Map qui stocke l'action menant à chaque état dans le plan
     * @param distance Une Map qui stocke la distance/coût pour atteindre chaque état
     * @param value    Une Map qui stocke la valeur AStar (distance + heuristique) de chaque état
     * @return         Une liste d'actions représentant le plan pour atteindre l'objectif sinon null si aucun plan n'est trouvé
     */
    public List<Action> AStar(Map<Map<Variable, Object>, Map<Variable, Object>> father,Map<Map<Variable, Object>, Action> plan, 
        Map<Map<Variable, Object>, Integer> distance, Map<Map<Variable, Object>, Float> value) {

        // on initialise la liste des open avec une file de priorité
        PriorityBlockingQueue <Map<Variable, Object>> open = new PriorityBlockingQueue<>(1, new DistanceComparator(value));
        // on initialise les HashMaps avec l'état initial
        father.put(initialState, null);
        distance.put(initialState, 0);
        value.put(initialState, heuristic.estimate(initialState));
        open.add(initialState);

        // tant que la liste des ouverts n'est pas vide 
        while (!open.isEmpty()) {
            // on récupère la valeur la plus petite de la liste des ouverts
            Map<Variable, Object> currentState = open.poll();
            // on vérifie si l'état actuel satisfait l'objectif
            if (goal.isSatisfiedBy(currentState)) {
                // si oui on retourne le plan en utlisant une recherche en largeur
                return bfs.getBFSPlan(father, plan, currentState);
            }

            // si l'état courant n'est pas l'état objectif, on parcourt les toutes les actions possibles
            for (Action action : actions) {
                // on verifie si l'action est applicable à l'état courant
                if (action.isApplicable(currentState)) {
                    // on récupère l'état suivant 
                    Map<Variable, Object> nextState = action.successor(currentState);
                    // on vérifie si l'état suivant n'a pas déjà été visité
                    if (!distance.containsKey(nextState)) {
                        // si non on l'ajoute aux HashMaps
                        distance.put(nextState, Integer.MAX_VALUE);
                    }
                    //si la distance de l'état suivant est supérieure à la distance de l'état courant + le coût de l'action
                    if (distance.get(nextState) > distance.get(currentState) + action.getCost()) {
                        // on met à jour les HashMaps
                        distance.put(nextState, distance.get(currentState) + action.getCost());
                        value.put(nextState, distance.get(nextState) + heuristic.estimate(nextState));
                        father.put(nextState, currentState);
                        plan.put(nextState, action);
                        // on ajoute l'état suivant à la liste des ouverts
                        open.add(nextState);
                        nombresNoeuds++;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<Action> plan() {
        // Retourne un plan qui atteint l'état objectif à partir de HashMap vide
        return AStar(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    @Override
    public Map<Variable, Object> getInitialState() {
        // Retourne l'état initial du problème de planification
        return initialState;
    }

    @Override
    public Set<Action> getActions() {
        // Retourne l'ensemble d'actions disponibles qui peuvent être appliquées aux états
        return actions;
    }

    @Override
    public Goal getGoal() {
        // Retourne l'état objectif que le planificateur vise à atteindre
        return goal;
    }

    /**
     * Méthode qui permet de calculer le nombre de noeuds explorés
     * @return Le nombre de noeuds explorés
     */
    public int getNombresNoeuds() {
        return nombresNoeuds;
    }
}