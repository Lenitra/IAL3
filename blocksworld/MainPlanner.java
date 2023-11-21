package blocksworld;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import modelling.BooleanVariable;
import modelling.Variable;
import planning.AStarPlanner;
import planning.Action;
import planning.BFSPlanner;
import planning.BasicGoal;
import planning.DFSPlanner;
import planning.DijkstraPlanner;
import planning.Goal;
import planning.Planner;

public class MainPlanner {
    public static void main(String[] args) {
        Map<Variable, Object> initialState = new HashMap<>();
        Map<Variable, Object> goalState = new HashMap<>();
        
        // Création de l'état initial
        // [0 , 2]
        // [1]
        initialState.put(new Variable("On0", Set.of(-1, -2,-3, 1, 2)), -1);
        initialState.put(new Variable("On1", Set.of(-1, -2,-3, 0, 2)), -2);
        initialState.put(new Variable("On2", Set.of(-1, -2,-3, 0, 1)), 0);
        initialState.put(new BooleanVariable("Fi0"),true);
        initialState.put(new BooleanVariable("Fi1"),false);
        initialState.put(new BooleanVariable("Fi2"),false);
        initialState.put(new BooleanVariable("Fr1"),false);
        initialState.put(new BooleanVariable("Fr2"),false);
        initialState.put(new BooleanVariable("Fr3"),true);


        // Création du goal
        //[]
        //[]
        //[2, 1, 0]
        goalState.put(new Variable("On2", Set.of(-1)),-3);
        goalState.put(new Variable("On1", Set.of(-2)), 2);
        goalState.put(new Variable("On0", Set.of(1)), 1);
        goalState.put(new BooleanVariable("Fi0"),false);
        goalState.put(new BooleanVariable("Fi1"),true);
        goalState.put(new BooleanVariable("Fi2"),true);
        goalState.put(new BooleanVariable("Fr1"),true);
        goalState.put(new BooleanVariable("Fr2"),true);
        goalState.put(new BooleanVariable("Fr3"),false);

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
        
        planner.getInitialState();
        planner.getGoal();
        planner.plan();
        // System.out.println("Etat initial : " + planner.getInitialState());
        // System.out.println("-----------------");
        // System.out.println("Etat final : " + planner.getGoal().toString());
        // System.out.println("-----------------");
        // System.out.println("Plan : " + planner.plan());
        System.out.println("-----------------");
        System.out.println("Temps d'execution : " + (System.currentTimeMillis() - startTime) + " ms");
        System.out.println("Nombre de noeuds : " + planner.getNombresNoeuds());

        System.out.println();

    }
}
