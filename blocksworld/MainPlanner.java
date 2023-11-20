package blocksworld;

import java.util.*;

import modelling.*;
import planning.*;

public class MainPlanner {
    public static void main(String[] args) {
        //on va tester dfs et bfs avec un etat initial et un etat final

        //etat initial
        Map<Variable, Object> initialState = new HashMap<>();
        Map<Variable, Object> goalState = new HashMap<>();

        Variable On0 = new Variable("On0", Set.of(-1,-2,-3,1, 2));
        Variable On1 = new Variable("On1", Set.of(-1,-2,-3,0, 2));
        Variable On2 = new Variable("On2", Set.of(-1, -2,-3, 0, 1));
        Variable Fi2 = new BooleanVariable("Fi2");
        Variable Fi0 = new BooleanVariable("Fi0");
        Variable Fi1 = new BooleanVariable("Fi1");
        Variable Fr1 = new BooleanVariable("Fr1");
        Variable Fr2 = new BooleanVariable("Fr2");
        Variable Fr3 = new BooleanVariable("Fr3");

        // Création de l'état initial
        initialState.put(On0, -1);
        initialState.put(On1, -2);
        initialState.put(On2, 0);
        initialState.put(Fi2, false);
        initialState.put(Fr1, false);
        initialState.put(Fr2, false);
        initialState.put(Fi1, false);
        initialState.put(Fi0, true);
        initialState.put(Fr3, true);

        // Création du goal
        goalState.put(On2, -3);
        goalState.put(On1, 2);
        goalState.put(On0, 1);
        goalState.put(Fi0, false);
        goalState.put(Fi1, true);
        goalState.put(Fi2, true);
        goalState.put(Fr1, true);
        goalState.put(Fr2, true);
        goalState.put(Fr3, false);

        Goal goal = new BasicGoal(goalState);

        //on va tester dfs et bfs avec un etat initial et un etat final
        BWActions bwActions = new BWActions(3,3);
        Set<Action> actions = bwActions.getActions();
        
        //on va tester dfs et bfs avec un etat initial et un etat final
        Planner dfs = new DFSPlanner(initialState, actions, goal);
        PlannerTest(dfs, "DFS");

        Planner bfs = new BFSPlanner(initialState, actions, goal);
        PlannerTest(bfs, "BFS");

        Planner djikstra = new DijkstraPlanner(initialState, actions, goal);
        PlannerTest(djikstra, "Dijkstra");

        HeuristicNombreBlocksMalPlaces HeurisNBMP = new HeuristicNombreBlocksMalPlaces(goalState);
        Planner astar = new AStarPlanner(initialState, actions, goal, HeurisNBMP);
        PlannerTest(astar, "AStar");
    }

    private static void PlannerTest(Planner planner, String nom) {
        long startTime = System.currentTimeMillis(); 
        System.out.println("################# TEST "+ nom + " #################");
        System.out.println("Etat initial : " + planner.getInitialState());
        System.out.println("Etat final : " + planner.getGoal().toString());
        // System.out.println("Actions : " + planner.getActions());
        System.out.println("Plan : " + planner.plan());
        System.out.println("Temps d'execution : " + (System.currentTimeMillis() - startTime) + " ms");
        System.out.println("Nombre de noeuds : " + planner.getNombresNoeuds());
        // if (planner instanceof AStarPlanner) {
        //     System.out.println("Nombre blocs mal placés : " + ((AStarPlanner) planner).getHeuristic().estimate(planner.getInitialState()));
        // }
        System.out.println("################# FIN TEST "+ nom +" #################");

    }
}
