package planning;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import modelling.*;

import planningtests.AStarPlannerTests;
import planningtests.BFSPlannerTests;
import planningtests.BasicActionTests;
import planningtests.BasicGoalTests;
import planningtests.DFSPlannerTests;
import planningtests.DijkstraPlannerTests;

public class Main {
    public static void main(String[] args) {
        boolean ok = true;
        ok = ok && BasicActionTests.testIsApplicable();
        ok = ok && BasicActionTests.testSuccessor();
        ok = ok && BasicActionTests.testGetCost();
        ok = ok && BasicGoalTests.testIsSatisfiedBy();
        ok = ok && DFSPlannerTests.testPlan();
        ok = ok && BFSPlannerTests.testPlan();
        ok = ok && DijkstraPlannerTests.testPlan();
        ok = ok && AStarPlannerTests.testPlan();
        System.out.println(ok ? "All tests OK" : "At least one test KO");
    
    
        // tests avec des valeurs en exemple
        
        BooleanVariable A = new BooleanVariable("A");
        BooleanVariable B = new BooleanVariable("B");
        BooleanVariable C = new BooleanVariable("C");
        BooleanVariable D = new BooleanVariable("D");
        HashMap<Variable, Object> initialState = new HashMap<>();
        initialState.put(A, true);
        initialState.put(B, true);
        initialState.put(C, true);
        initialState.put(D, true);
        
        // contraintes
        // HashSet<Constraint> constraints = new HashSet<>();
        // constraints.add(new DifferenceConstraint(A, B));
        // constraints.add(new DifferenceConstraint(A, C));
        // constraints.add(new DifferenceConstraint(B, C));
        // constraints.add(new DifferenceConstraint(C, D));



        // Set actions
        HashSet<Action> actions = new HashSet<>();
        
        
        //     public BasicAction(Map<Variable, Object> precondition, Map<Variable, Object> effect, int cout) {
                // this.precondition = precondition;
                // this.effect = effect;
                // this.cout = cout;
    
        
        // actions.add(new BasicAction(precondition, effect, 1));
        actions.add(new BasicAction(new HashMap<Variable, Object>() {{ put(A, true);}}, new HashMap<Variable, Object>() {{ put(A, false);}}, 1));
        actions.add(new BasicAction(new HashMap<Variable, Object>() {{ put(A, false);}}, new HashMap<Variable, Object>() {{ put(A, true);}}, 1));
        actions.add(new BasicAction(new HashMap<Variable, Object>() {{ put(B, true);}}, new HashMap<Variable, Object>() {{ put(B, false);}}, 1));
        actions.add(new BasicAction(new HashMap<Variable, Object>() {{ put(B, false);}}, new HashMap<Variable, Object>() {{ put(B, true);}}, 1));
        actions.add(new BasicAction(new HashMap<Variable, Object>() {{ put(C, true);}}, new HashMap<Variable, Object>() {{ put(C, false);}}, 1));
        actions.add(new BasicAction(new HashMap<Variable, Object>() {{ put(C, false);}}, new HashMap<Variable, Object>() {{ put(C, true);}}, 1));
        actions.add(new BasicAction(new HashMap<Variable, Object>() {{ put(D, true);}}, new HashMap<Variable, Object>() {{ put(D, false);}}, 1));
        actions.add(new BasicAction(new HashMap<Variable, Object>() {{ put(D, false);}}, new HashMap<Variable, Object>() {{ put(D, true);}}, 1));


        // Set goal
        Goal goal = new BasicGoal(new HashMap<Variable, Object>() {{ put(D, false); }});


        // Apply the algorithm of BSP
        Planner planner = new BFSPlanner(initialState, actions, goal);
        planner.plan();
        System.out.println(planner.getNombresNoeuds());

        planner = new DFSPlanner(initialState, actions, goal);
        planner.plan();
        System.out.println(planner.getNombresNoeuds());
        
        planner = new DijkstraPlanner(initialState, actions, goal);
        planner.plan();
        System.out.println(planner.getNombresNoeuds());

    }
}
