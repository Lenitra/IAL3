package planning;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

import modelling.Variable;

public class DijkstraPlanner implements Planner {

    private Map<Variable, Object> initialState;
    private Set<Action> actions;
    private Goal goal;
    private int nombresNoeuds;

        /**
     * Construit un DijkstraPlanner avec un état initial, un ensemble d'actions et l'objectif à atteindre
     * 
     * @param initialState L'état initial du problème
     * @param actions      L'ensemble d'actions disponibles qui peuvent être appliquées aux états
     * @param goal         L'état objectif que le planificateur vise à atteindre
     */
    public DijkstraPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.initialState = initialState;
        this.actions = actions;
        this.goal = goal;
        this.nombresNoeuds = 0;
    }


    public List<Action> dijkstra(Map<Map<Variable, Object>, Action> plan, Map<Map<Variable, Object>, Float> distance, Map<Map<Variable, Object>, Map<Variable, Object>> father) {

        // on initialise la liste des objectifs avec une file de priorité
        PriorityBlockingQueue<Map<Variable, Object>> goals = new PriorityBlockingQueue<>(1,
            new DistanceComparator(distance));
        // on initialise la liste des ouverts avec une file de priorité
        PriorityBlockingQueue<Map<Variable, Object>> open = new PriorityBlockingQueue<>(1, 
            new DistanceComparator(distance));
        
        // on initialise les HashMaps avec l'état initial
        father.put(initialState, null);
        distance.put(initialState, (float) 0);
        open.add(initialState);
        
        // tant que la liste des ouverts n'est pas vide
        while (!open.isEmpty()) {
            //on récupère l'état actuel
            Map<Variable, Object> currentState = open.poll();

            // on vérifie si l'état actuel satisfait l'objectif
            if (goal.isSatisfiedBy(currentState)) {
                // si oui on ajoute l'état actuel à la liste des objectifs
                goals.add(currentState);
            }

            // si l'état courant n'est pas l'état objectif, on parcourt les toutes les actions possibles
            for (Action action : actions) {
                if (action.isApplicable(currentState)) {
                    Map<Variable, Object> nextState = action.successor(currentState);
                    if (!distance.containsKey(nextState)) {
                        distance.put(nextState, Float.MAX_VALUE);
                    }
                    float newDist = distance.get(currentState) + action.getCost();
                    // on vérifie si la distance de l'état suivant est plus grande que la distance de l'état actuel + le coût de l'action
                    if (distance.get(nextState) > newDist) {
                        // on met à jour les HashMaps
                        distance.put(nextState, newDist);
                        father.put(nextState, currentState);
                        plan.put(nextState, action);
                        open.add(nextState);
                        nombresNoeuds++;
                    }
                }
            }
        }
        if (goals.isEmpty()){
            return null;
        }
        // on retourne le plan
        return getPlan(father, plan, goals, distance);
    }

    /**
     * Récupère le plan final à partir de Map des relations parent-enfant et de plans.
     *
     * @param father   La Map des relations parent-enfant.
     * @param plan     La Map des plans.
     * @param goals    La file de priorité des objectifs.
     * @param distance La Map des distances.
     * @return Une liste d'actions représentant le plan final pour atteindre l'objectif.
     */
    public List<Action> getPlan(Map<Map<Variable, Object>, Map<Variable, Object>> father, Map<Map<Variable, Object>, Action> plan, Queue<Map<Variable, Object>> goals, Map<Map<Variable, Object>, Float> distance) {
        //
        LinkedList<Action> dijkstraPlan = new LinkedList<>();
        // On récupère le dernier objectif
        Map<Variable, Object> goal = goals.poll();
        while (goal != null) {
            Action nextAction = plan.get(goal);
            if (nextAction == null) {
                break;
            }
            goal = father.get(goal);
            dijkstraPlan.addFirst(nextAction);
            }
        return dijkstraPlan;
    }


    @Override
    public List<Action> plan() {
        // Retourne un plan qui atteint l'état objectif à partir de HashMap vide
        return dijkstra(new HashMap<>(), new HashMap<>(), new HashMap<>());
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
