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

        Variable on0 = new Variable("on0", Set.of(-1,-2,-3,1, 2));
        Variable on1 = new Variable("on1", Set.of(-1,-2,-3,0, 2));
        Variable on2 = new Variable("on2", Set.of(-1, -2,-3, 0, 1));
        Variable fi2 = new BooleanVariable("fi2");
        Variable fi0 = new BooleanVariable("fi0");
        Variable fi1 = new BooleanVariable("fi1");
        Variable frm1 = new BooleanVariable("fr1");
        Variable frm2 = new BooleanVariable("fr2");
        Variable frm3 = new BooleanVariable("fr3");
        // Création de l'état initial
        initialState.put(on0, -1);
        initialState.put(on1, -2);
        initialState.put(on2, 0);
        initialState.put(fi2, false);
        initialState.put(frm1, false);
        initialState.put(frm2, false);
        initialState.put(fi1, false);
        initialState.put(fi0, true);
        initialState.put(frm3, true);

        // Création du goal
        goalState.put(on2, -3);
        goalState.put(on1, 2);
        goalState.put(on0, 1);
        goalState.put(fi0, false);
        goalState.put(fi1, true);
        goalState.put(fi2, true);
        goalState.put(frm1, true);
        goalState.put(frm2, true);
        goalState.put(frm3, false);

        Goal goal = new BasicGoal(goalState);

        //on va tester dfs et bfs avec un etat initial et un etat final
        BWActions bwActions = new BWActions(3,3);
        Set<Action> actions = bwActions.getActions();
        
        //on va tester dfs et bfs avec un etat initial et un etat final
        Planner dfs = new DFSPlanner(initialState, actions, goal);
        PlannerTest(dfs, "DFS");

        Planner bfs = new BFSPlanner(initialState, actions, goal);
        PlannerTest(bfs, "BFS");

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
        System.out.println("################# FIN TEST "+ nom +" #################");
    }
}
