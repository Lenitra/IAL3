package blocksworld;


import java.util.*;

import modelling.BooleanVariable;
import modelling.Variable;
import planning.Action;

public class MainActions {
    
    public static void main(String[] args) {
        // Paramètres pour le monde des blocs
        int numBlocks = 2;
        int numPiles = 2;

        // Création des actions pour le monde des blocs
        BWActions blockWorldActions = new BWActions(numBlocks, numPiles);
        Set<Action> allActions = blockWorldActions.getActions();
        System.out.println(allActions.size());
        // Affichage des actions générées
        System.out.println("Actions générées :");
        // for (Action action : allActions) {
        //     System.out.println(action);
        // }
        System.out.println();

        // Création d'un état initial
        Map<Variable, Object> initialState = createInitialState(numBlocks, numPiles);

        // Affichage de l'état initial
        System.out.println("État initial :");
        printState(initialState);
        System.out.println();

        // Test des actions avec l'état initial
        testActions(initialState, allActions);

        // Création d'un état final (but)
        Map<Variable, Object> goalState = createGoalState(numBlocks, numPiles);
        System.out.println("État final :");
        printState(goalState);
        System.out.println();
    }

    private static Map<Variable, Object> createInitialState(int numBlocks, int numPiles) {
        Map<Variable, Object> initialState = new HashMap<>();

        initialState.put(new Variable("on0", Set.of(-1)), -1);
        initialState.put(new Variable("on1", Set.of(0)), 0);
        initialState.put(new BooleanVariable("fixed0"), true);
        initialState.put(new BooleanVariable("fixed1"), false);
        initialState.put(new BooleanVariable("free1"), false);
        initialState.put(new BooleanVariable("free2"), true);

        return initialState;
    }

    private static Map<Variable, Object> createGoalState(int numBlocks, int numPiles) {
        Map<Variable, Object> goalState = new HashMap<>();

        goalState.put(new Variable("on0", Set.of(-1)), -1);
        goalState.put(new Variable("on1", Set.of(-2)), -2);
        goalState.put(new Variable("fixed0", Set.of(false)), false);
        goalState.put(new Variable("fixed1", Set.of(false)), false);
        goalState.put(new Variable("free1", Set.of(false)), false);
        goalState.put(new Variable("free2", Set.of(false)), false);

        return goalState;
    }

    private static void testActions(Map<Variable, Object> initialState, Set<Action> allActions) {
        // Tester chaque action avec l'état initial
        for (Action action : allActions) {
            //System.out.println("Testing action: " + action);
            
            if (action.isApplicable(initialState)) {
                System.out.println("Action is applicable.");
                //System.out.println(action.toString());
                Map<Variable, Object> nextState = action.successor(initialState);
                System.out.println("Resulting state:");
                printState(nextState);
                System.out.println();
            } 
            else {
                System.out.println("Action is not applicable.");
            }
        }
    }

    private static void printState(Map<Variable, Object> state) {
        for (Map.Entry<Variable, Object> entry : state.entrySet()) {
            System.out.println(entry.getKey().toString()+":"+ entry.getValue());
        }
    }
}