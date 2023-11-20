package blocksworld;

import java.util.*;

import modelling.BooleanVariable;
import modelling.Variable;
import planning.Action;

public class MainActions {
    
    public static void main(String[] args) {

        System.out.println("Test BlockToPile :");
        testBlockToPile(2,2);
 
        System.out.println("Test BlockToBlock :");
        testBlockToBlock(3,2);

        System.out.println("Test BlockPileToBlock :");
        testBlockPileToBlock(2,2);

        System.out.println("Test BlockPileToPile :");
        testBlockPileToPile(1,2);

        //test des plans pour le monde des blocs avec A*
        // testBlockWoldPlan(6, 4);
    }
    
    //--------------------------------------------------------------------------------
    //TEST BLOCK ON PILE
    private static void testBlockToPile(int numBlocks, int numPiles) {
        // Création des actions pour le monde des blocs (BlockToPile)
        BWActions blockWorldActions = new BWActions(numBlocks, numPiles);
        Set<Action> allActions = blockWorldActions.getActions();
        System.out.println(allActions.size());

        // Création d'un état initial
        Map<Variable, Object> initialState = createInitialStateBlockToPile(numBlocks, numPiles);

        // Affichage de l'état initial
        System.out.println("État initial pour BlockToPile :");
        printState(initialState);
        System.out.println();

        // Test des actions avec l'état initial pour BlockToPile
        testActions(initialState, allActions);

        // Création d'un état final (but)
        Map<Variable, Object> goalState = createGoalStateBlockToPile(numBlocks, numPiles);
        System.out.println("État final :");
        printState(goalState);
        System.out.println();
    }

    private static Map<Variable, Object> createInitialStateBlockToPile(int numBlocks, int numPiles) {
        Map<Variable, Object> initialState = new HashMap<>();

        initialState.put(new Variable("On0", Set.of(0, -1, 1)), 1);
        initialState.put(new Variable("On1", Set.of(0, -1, 1)), -1);
        initialState.put(new BooleanVariable("Fi0"), false);
        initialState.put(new BooleanVariable("Fi1"), true);
        initialState.put(new BooleanVariable("Fr1"), false);
        initialState.put(new BooleanVariable("Fr2"), true);

        return initialState;
    }

    private static Map<Variable, Object> createGoalStateBlockToPile(int numBlocks, int numPiles) {
        Map<Variable, Object> goalState = new HashMap<>();

        goalState.put(new Variable("On0", Set.of(-1)), -1);
        goalState.put(new Variable("On1", Set.of(-2)), -2);
        goalState.put(new Variable("Fi0", Set.of(false)), false);
        goalState.put(new Variable("Fi1", Set.of(false)), false);
        goalState.put(new Variable("Fr1", Set.of(false)), false);
        goalState.put(new Variable("Fr2", Set.of(false)), false);

        return goalState;
    }

    //--------------------------------------------------------------------------------
    //TEST BLOCK ON BlOCK
    private static void testBlockToBlock(int numBlocks, int numPiles) {
        // Création des actions pour le monde des blocs (BlockToBlock)
        BWActions blockWorldActions = new BWActions(numBlocks, numPiles);
        Set<Action> allActions = blockWorldActions.getActions();
        System.out.println(allActions.size());

        // Création d'un état initial
        Map<Variable, Object> initialState = createInitialStateBlockToBlock(numBlocks, numPiles);

        // Affichage de l'état initial
        System.out.println("État initial pour BlockToBlock :");
        printState(initialState);
        System.out.println();

        // Test des actions avec l'état initial pour BlockToBlock
        testActions(initialState, allActions);

        // Création d'un état final (but)
        Map<Variable, Object> goalState = createGoalStateBlockToBlock(numBlocks, numPiles);
        System.out.println("État final :");
        printState(goalState);
        System.out.println();
    }

    private static Map<Variable, Object> createInitialStateBlockToBlock(int numBlocks, int numPiles) {
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

    private static Map<Variable, Object> createGoalStateBlockToBlock(int numBlocks, int numPiles) {
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

    //--------------------------------------------------------------------------------
    //TEST BLOCK PILE ON BLOCK
    private static void testBlockPileToBlock(int numBlocks, int numPiles) {
        // Création des actions pour le monde des blocs (BlockPileToBlock)
        BWActions blockWorldActions = new BWActions(numBlocks, numPiles);
        Set<Action> allActions = blockWorldActions.getActions();
        System.out.println(allActions.size());

        // Création d'un état initial
        Map<Variable, Object> initialState = createInitialStateBlockPileToBlock(numBlocks, numPiles);

        // Affichage de l'état initial
        System.out.println("État initial pour BlockPileToBlock :");
        printState(initialState);
        System.out.println();

        // Test des actions avec l'état initial pour BlockPileToBlock
        testActions(initialState, allActions);

        // Création d'un état final (but)
        Map<Variable, Object> goalState = createGoalStateBlockPileToBlock(numBlocks, numPiles);
        System.out.println("État final :");
        printState(goalState);
        System.out.println();
    }

    private static Map<Variable, Object> createInitialStateBlockPileToBlock(int numBlocks, int numPiles) {
        Map<Variable, Object> initialState = new HashMap<>();

        initialState.put(new Variable("On0", Set.of(0, -1, -2, 1)), -1);
        initialState.put(new Variable("On1", Set.of(0, -1, -2, 1)), -2);
        initialState.put(new BooleanVariable("Fi0"), false);
        initialState.put(new BooleanVariable("Fi1"), false);
        initialState.put(new BooleanVariable("Fr1"), false);
        initialState.put(new BooleanVariable("Fr2"), false);

        return initialState;
    }

    private static Map<Variable, Object> createGoalStateBlockPileToBlock(int numBlocks, int numPiles) {
        Map<Variable, Object> goalState = new HashMap<>();

        goalState.put(new Variable("On0", Set.of(0, -1, -2, 1)), 1);
        goalState.put(new Variable("On1", Set.of(0, -1, -2, 1)), -2);
        goalState.put(new Variable("Fi0", Set.of(false, true)), false);
        goalState.put(new Variable("Fi1", Set.of(false, true)), true);
        goalState.put(new Variable("Fr1", Set.of(false, true)), true);
        goalState.put(new Variable("Fr2", Set.of(false, true)), false);

        return goalState;
    }

    //--------------------------------------------------------------------------------
    //TEST BlOCK PILE ON PILE
    private static void testBlockPileToPile(int numBlocks, int numPiles) {
        // Création des actions pour le monde des blocs (BlockPileToPile)
        BWActions blockWorldActions = new BWActions(numBlocks, numPiles);
        Set<Action> allActions = blockWorldActions.getActions();
        System.out.println(allActions.size());

        // Création d'un état initial
        Map<Variable, Object> initialState = createInitialStateBlockPileToPile(numBlocks, numPiles);

        // Affichage de l'état initial
        System.out.println("État initial pour BlockPileToPile :");
        printState(initialState);
        System.out.println();

        // Test des actions avec l'état initial pour BlockPileToPile
        testActions(initialState, allActions);

        // Création d'un état final (but)
        Map<Variable, Object> goalState = createGoalStateBlockPileToPile(numBlocks, numPiles);
        System.out.println("État final :");
        printState(goalState);
        System.out.println();
    }

    private static Map<Variable, Object> createInitialStateBlockPileToPile(int numBlocks, int numPiles) {
        Map<Variable, Object> initialState = new HashMap<>();

        initialState.put(new Variable("On0", Set.of(0, -1, -2,-3)), -1);
        initialState.put(new BooleanVariable("Fi0"), false);
        initialState.put(new BooleanVariable("Fr1"), false);
        initialState.put(new BooleanVariable("Fr2"), true);

        return initialState;
    }

    private static Map<Variable, Object> createGoalStateBlockPileToPile(int numBlocks, int numPiles) {
        Map<Variable, Object> goalState = new HashMap<>();

        goalState.put(new Variable("On0", Set.of(0, -1, -2)), -2);
        goalState.put(new Variable("Fi0", Set.of(false, true)), false);
        goalState.put(new Variable("Fi1", Set.of(false, true)), true);
        goalState.put(new Variable("Fr1", Set.of(false, true)), true);
        goalState.put(new Variable("Fr2", Set.of(false, true)), false);

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

    //--------------------------------------------------------------------------------
    //TEST PLAN BLOCKWORLD
    // private static void testBlockWoldPlan(int numBlocks, int numPiles) {
    //     // Création des actions pour le monde des blocs (BlockPileToPile)
    //     BWActions blockWorldActions = new BWActions(numBlocks, numPiles);
    //     Set<Action> allActions = blockWorldActions.getActions();
    //     System.out.println(allActions.size());

    //     // Création d'un état initial
    //     Map<Variable, Object> initialState = createInitialStateBlockWoldPlan(numBlocks, numPiles);

    //     // Affichage de l'état initial
    //     System.out.println("État initial pour BlockWoldPlan :");
    //     printState(initialState);
    //     System.out.println();

    //     // Création d'un état final (but)
    //     Map<Variable, Object> goalState = createGoalStateBlockWoldPlan(numBlocks, numPiles);
    //     System.out.println("État final :");
    //     printState(goalState);
    //     System.out.println();

    //    DijkstraPlanner planner = new DijkstraPlanner(initialState,allActions,goalState);
    // }

}