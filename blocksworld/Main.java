package blocksworld;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import bwmodel.BWState;
import bwmodel.BWStateBuilder;
import bwui.BWComponent;
import bwui.BWIntegerGUI;
import planning.*;
import modelling.*;

/**
 * Class for realising the view representing
 * the state of the block world.
 * Make use of library blocksworld
 */
public class Main {

    private BWVariable bwV;
    private Map<Variable, Object> state;
    private String title;

    /**
     * Constructor
     * 
     * @param bwV   : reference to block world variables
     * @param state : the actual state
     * @param title : frame title
     */
    public Main(BWVariable bwV, Map<Variable, Object> state, String title) {
        this.bwV = bwV;
        this.state = state;
        this.title = title;
    }

    public BWState<Integer> makeBwState() {
        int numberOfOnb = bwV.getOnb().size();
        BWStateBuilder<Integer> builder = BWStateBuilder.makeBuilder(numberOfOnb);
        for (Variable var : bwV.getOnb()) {
            int index = bwV.getIndex(var);
            int under = (int) state.get(var);
            if (under >= 0)
                builder.setOn(index, under);
        }
        BWState<Integer> bwState = builder.getState();
        return bwState;
    }

    /**
     * Method displaying a given state
     */
    public void display(int x, int y) {
        int numberOfOnb = bwV.getOnb().size();
        BWState<Integer> bwState = makeBwState();
        BWIntegerGUI gui = new BWIntegerGUI(numberOfOnb);
        JFrame frame = new JFrame(title);
        frame.setLocation(x, y);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.add(gui.getComponent(bwState));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Method the displaying the execution of a plan
     * 
     * @param actions : the plan
     */
    public void displayPlan(List<Action> actions) {
        int numberOfOnb = bwV.getOnb().size();
        BWIntegerGUI gui = new BWIntegerGUI(numberOfOnb);
        JFrame frame = new JFrame("BLOCK WORLD");
        frame.setPreferredSize(new Dimension(500, 500));
        BWState<Integer> bwState = makeBwState();
        BWComponent<Integer> component = gui.getComponent(bwState);
        frame.add(component);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (Action action : actions) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            state = action.successor(state);
            component.setState(makeBwState());
        }
        System.out.println("Fin de simulation");
    }


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

        Planner bfs = new BFSPlanner(initialState, actions, goal);

        Planner djikstra = new DijkstraPlanner(initialState, actions, goal);

        HeuristicNombreBlocksMalPlaces HeurisNBMP = new HeuristicNombreBlocksMalPlaces(goalState);
        Planner astar = new AStarPlanner(initialState, actions, goal, HeurisNBMP);
    


        // COMPLETER ICI pour ouvir une fenetre et afficher l'état initial
    BWVariable bwV = new BWVariable(3, 3);
    

    // Création d'une instance de Main
    Main mainView = new Main(bwV, initialState, "");

    // Choix du planificateur à utiliser pour la simulation
    Planner planner = djikstra; // Par exemple, on peut choisir AStarPlanner ici

    // Génération du plan
    List<Action> plan = planner.plan();

    // Affichage de la simulation du plan
    mainView.displayPlan(plan);

    }

}