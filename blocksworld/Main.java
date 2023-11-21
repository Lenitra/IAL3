package blocksworld;

import java.util.Map;
import java.util.Set;

import modelling.*;
import planning.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import java.awt.Dimension;

import javax.swing.JFrame;

import bwmodel.BWState;
import bwmodel.BWStateBuilder;
import bwui.BWComponent;
import bwui.BWIntegerGUI;

public class Main {

    private BWVariable bwV;
    private Map<Variable, Object> state;
    private String title;

    /**
     * Constructor
     * 
     * @param bwV   : Variables du block world
     * @param state : Etat initial
     * @param title : Titre de la fenêtre
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
     * Méthode appelée pour afficher la simulation d'un état
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
     * Méthode appelée pour afficher la simulation d'un plan
     * 
     * @param actions : the plan
     */
    public void displayPlan(List<Action> actions) {
        int numberOfOnb = bwV.getOnb().size();
        BWIntegerGUI gui = new BWIntegerGUI(numberOfOnb);
        JFrame frame = new JFrame(title);
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

    /**
     * Méthode qui permet de transformer une liste de liste d'entiers en une map de
     * variables et d'objets
     * 
     * @param state : l'état à transformer
     * @return la map de variables et d'objets
     */
    public static Map<Variable, Object> fromListListToMapVarObj(List<List<Integer>> state) {
        Map<Variable, Object> map = new HashMap<>();

        List<Integer> domaineOn = new ArrayList<>();
        for (int i = 0; i < state.size(); i++) {
            domaineOn.add(-i - 1);
        }

        for (int i = 0; i < state.size(); i++) {
            if (state.get(i).isEmpty()) {
                map.put(new BooleanVariable("Fr" + (i + 1)), true);
            } else {
                map.put(new BooleanVariable("Fr" + (i + 1)), false);
            }

            domaineOn.add(-i - 1);

            for (int j = 0; j < state.get(i).size(); j++) {
                domaineOn.add(state.get(i).get(j));
            }

            for (int j = 0; j < state.get(i).size(); j++) {
                Set<Object> domaineOnCopy = new HashSet<>(domaineOn);
                domaineOnCopy.remove(Integer.valueOf(state.get(i).get(j)));

                if (j == 0) {

                    map.put(new Variable("On" + state.get(i).get(j), domaineOnCopy), -i - 1);
                } else {
                    map.put(new Variable("On" + state.get(i).get(j), domaineOnCopy), state.get(i).get(j - 1));
                }

                if (j == state.get(i).size() - 1) {
                    map.put(new BooleanVariable("Fi" + state.get(i).get(j)), false);
                } else {
                    map.put(new BooleanVariable("Fi" + state.get(i).get(j)), true);
                }
            }
        }

        return map;
    }

    
    public static void main(String[] args) {

        Map<Variable, Object> initialState = new HashMap<>();
        Map<Variable, Object> goalState = new HashMap<>();

        /*
         * 
         * VARIABLES
         * 
         */

        int nbBlocks = 3;
        int nbPiles = 3;

        // Création de l'état initial
        // [0 , 2]
        // [1]
        // []
        List<List<Integer>> initialStateList = List.of(
                List.of(0, 2),
                List.of(1),
                List.of());

        // Création du goal
        // [2, 1, 0]
        // []
        // []
        List<List<Integer>> goalStateList = List.of(
                List.of(2, 1, 0),
                List.of(),
                List.of());



        initialState = fromListListToMapVarObj(initialStateList);
        goalState = fromListListToMapVarObj(goalStateList);
        Goal goal = new BasicGoal(goalState);

        // on va tester dfs et bfs avec un etat initial et un etat final
        BWActions bwActions = new BWActions(nbBlocks, nbPiles);
        Set<Action> actions = bwActions.getActions();

        // initialisation des planificateurs
        Planner dfs = new DFSPlanner(initialState, actions, goal);

        Planner bfs = new BFSPlanner(initialState, actions, goal);

        Planner djikstra = new DijkstraPlanner(initialState, actions, goal);

        HeuristicNombreBlocksMalPlaces HeurisNBMP = new HeuristicNombreBlocksMalPlaces(goalState);
        Planner astar = new AStarPlanner(initialState, actions, goal, HeurisNBMP);

        BWVariable bwV = new BWVariable(nbBlocks, nbPiles);

        // Création d'une instance de Main
        Main mainView = new Main(bwV, initialState, "Blocksworld Simulator");

        // Choix du planificateur à utiliser pour la simulation
        Planner planner = astar; // ou autre planificateur

        // Génération du plan
        List<Action> plan = planner.plan();

        // Vérification que le plan n'est pas null et contient des actions
        if (plan != null && !plan.isEmpty()) {
            // Affichage de la simulation du plan
            mainView.displayPlan(plan);
        } else {
            System.out.println("Le plan n'a pas pu être généré ou est vide.");
        }

    }

}