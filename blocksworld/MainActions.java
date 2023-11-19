package blocksworld;


import java.util.*;

import modelling.BooleanVariable;
import modelling.Variable;
import planning.Action;

public class MainActions {
    
    public static void main(String[] args) {

        // Affichage des actions générées
        // System.out.println("Actions générées :");
        // for (Action action : allActions) {
        //     System.out.println(action);
        // }
        // System.out.println();

        // System.out.println("Test BlockOnPile :");
        // testBlockOnPile(2,2);
 
        // System.out.println("Test BlockOnBlock :");
        // testBlockOnBlock(3,2);

        System.out.println("Test BlockPileOnBlock :");
        testBlockPileOnBlock(2,2);

    }
    
    //--------------------------------------------------------------------------------
    //TEST BLOCK ON PILE
    private static void testBlockOnPile(int numBlocks, int numPiles) {
        // Paramètres pour le monde des blocs
        // Création des actions pour le monde des blocs (BlockOnPile)
        BWActions2 blockWorldActions = new BWActions2(numBlocks, numPiles);
        Set<Action> allActions = blockWorldActions.getActions();
        System.out.println(allActions.size());

        // Création d'un état initial
        Map<Variable, Object> initialState = createInitialStateBlockOnPile(numBlocks, numPiles);

        // Affichage de l'état initial
        System.out.println("État initial pour BlockOnPile :");
        printState(initialState);
        System.out.println();

        // Test des actions avec l'état initial pour BlockOnPile
        testActionsBlockOnPile(initialState, allActions);

        // Création d'un état final (but)
        Map<Variable, Object> goalState = createGoalStateBlockOnPile(numBlocks, numPiles);
        System.out.println("État final :");
        printState(goalState);
        System.out.println();
    }

    private static Map<Variable, Object> createInitialStateBlockOnPile(int numBlocks, int numPiles) {
        Map<Variable, Object> initialState = new HashMap<>();

        initialState.put(new Variable("On0", Set.of(0, -1, 1)), 1);
        initialState.put(new Variable("On1", Set.of(0, -1, 1)), -1);
        initialState.put(new BooleanVariable("Fi0"), false);
        initialState.put(new BooleanVariable("Fi1"), true);
        initialState.put(new BooleanVariable("Fr1"), false);
        initialState.put(new BooleanVariable("Fr2"), true);

        return initialState;
    }

    private static Map<Variable, Object> createGoalStateBlockOnPile(int numBlocks, int numPiles) {
        Map<Variable, Object> goalState = new HashMap<>();

        goalState.put(new Variable("On0", Set.of(-1)), -1);
        goalState.put(new Variable("On1", Set.of(-2)), -2);
        goalState.put(new Variable("Fi0", Set.of(false)), false);
        goalState.put(new Variable("Fi1", Set.of(false)), false);
        goalState.put(new Variable("Fr1", Set.of(false)), false);
        goalState.put(new Variable("Fr2", Set.of(false)), false);

        return goalState;
    }

    private static void testActionsBlockOnPile(Map<Variable, Object> initialState, Set<Action> allActions) {
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

    //--------------------------------------------------------------------------------
    //TEST BLOCK ON BlOCK
    private static void testBlockOnBlock(int numBlocks, int numPiles) {
        // Paramètres pour le monde des blocs
        // Création des actions pour le monde des blocs (BlockOnPile)
        BWActions2 blockWorldActions = new BWActions2(numBlocks, numPiles);
        Set<Action> allActions = blockWorldActions.getActions();
        System.out.println(allActions.size());

        // Création d'un état initial
        Map<Variable, Object> initialState = createInitialStateBlockOnBlock(numBlocks, numPiles);

        // Affichage de l'état initial
        System.out.println("État initial pour BlockOnBlock :");
        printState(initialState);
        System.out.println();

        // Test des actions avec l'état initial pour BlockOnPile
        testActionsBlockOnBlock(initialState, allActions);

        // Création d'un état final (but)
        Map<Variable, Object> goalState = createGoalStateBlockOnBlock(numBlocks, numPiles);
        System.out.println("État final :");
        printState(goalState);
        System.out.println();
    }

    private static Map<Variable, Object> createInitialStateBlockOnBlock(int numBlocks, int numPiles) {
        Map<Variable, Object> initialState = new HashMap<>();

        initialState.put(new Variable("On0", Set.of(0,-1,-2, 1,2)), -1);
        initialState.put(new Variable("On1", Set.of(0,-1,-2, 1,2)), 0);
        initialState.put(new Variable("On2", Set.of(0,-1,-2, 1,2)), -2);
        initialState.put(new BooleanVariable("Fi0"), true);
        initialState.put(new BooleanVariable("Fi1"), false);
        initialState.put(new BooleanVariable("Fi2"), false);
        initialState.put(new BooleanVariable("Fr1"), false);
        initialState.put(new BooleanVariable("Fr2"), false);

        return initialState;
    }

    private static Map<Variable, Object> createGoalStateBlockOnBlock(int numBlocks, int numPiles) {
        Map<Variable, Object> goalState = new HashMap<>();

        goalState.put(new Variable("On0", Set.of(0,-1,-2, 1,2)), -1);
        goalState.put(new Variable("On1", Set.of(0,-1,-2, 1,2)), 2);
        goalState.put(new Variable("On2", Set.of(0,-1,-2, 1,2)), -2);
        goalState.put(new Variable("Fi0", Set.of(false)), false);
        goalState.put(new Variable("Fi1", Set.of(false)), false);
        goalState.put(new Variable("Fi2", Set.of(false)), true);
        goalState.put(new Variable("Fr1", Set.of(false)), false);
        goalState.put(new Variable("Fr2", Set.of(false)), false);

        return goalState;
    }

    private static void testActionsBlockOnBlock(Map<Variable, Object> initialState, Set<Action> allActions) {
        System.out.println("testActionsBlockOnBlock");
        // Tester chaque action avec l'état initial
        for (Action action : allActions) {
            //System.out.println("Testing action: " + action);
            
            if (action.isApplicable(initialState)) {
                System.out.println("Action is applicable.");
                System.out.println(action.toString());
                Map<Variable, Object> nextState = action.successor(initialState);
                System.out.println("Resulting state:");
                printState(nextState);
                System.out.println();
            } 
            else {
                // System.out.println("Action is not applicable.");
            }
        }
    }

    //--------------------------------------------------------------------------------
    //TEST BLOCK PILE ON BLOCK
    private static void testBlockPileOnBlock(int numBlocks, int numPiles) {
        // Paramètres pour le monde des blocs
        // Création des actions pour le monde des blocs (BlockPileOnBlock)
        BWActions2 blockWorldActions = new BWActions2(numBlocks, numPiles);
        Set<Action> allActions = blockWorldActions.getActions();
        System.out.println(allActions.size());

        // Création d'un état initial
        Map<Variable, Object> initialState = createInitialStateBlockPileOnBlock(numBlocks, numPiles);

        // Affichage de l'état initial
        System.out.println("État initial pour BlockPileOnBlock :");
        printState(initialState);
        System.out.println();

        // Test des actions avec l'état initial pour BlockPileOnBlock
        testActionsBlockPileOnBlock(initialState, allActions);

        // Création d'un état final (but)
        Map<Variable, Object> goalState = createGoalStateBlockPileOnBlock(numBlocks, numPiles);
        System.out.println("État final :");
        printState(goalState);
        System.out.println();
    }

    private static Map<Variable, Object> createInitialStateBlockPileOnBlock(int numBlocks, int numPiles) {
        Map<Variable, Object> initialState = new HashMap<>();

        initialState.put(new Variable("On0", Set.of(0, -1, -2, 1,2)), -1);
        initialState.put(new Variable("On1", Set.of(0, -1, -2, 1,2)), -2);
        initialState.put(new BooleanVariable("Fi0"), false);
        initialState.put(new BooleanVariable("Fi1"), false);
        initialState.put(new BooleanVariable("Fr1"), false);
        initialState.put(new BooleanVariable("Fr2"), false);

        return initialState;
    }

    private static Map<Variable, Object> createGoalStateBlockPileOnBlock(int numBlocks, int numPiles) {
        Map<Variable, Object> goalState = new HashMap<>();

        goalState.put(new Variable("On0", Set.of(0, -1, -2, 1)), 1);
        goalState.put(new Variable("On1", Set.of(0, -1, -2, 1)), -2);
        goalState.put(new Variable("Fi0", Set.of(false, true)), false);
        goalState.put(new Variable("Fi1", Set.of(false, true)), true);
        goalState.put(new Variable("Fr1", Set.of(false, true)), true);
        goalState.put(new Variable("Fr2", Set.of(false, true)), false);

        return goalState;
    }

    private static void testActionsBlockPileOnBlock(Map<Variable, Object> initialState, Set<Action> allActions) {
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