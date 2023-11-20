package blocksworld;

import java.util.*;

import modelling.*;
import cp.*;

public class MainSatisfaction {
    public static void main(String[] args) {
        BWConstraintes bw = new BWConstraintes(8, 3);
        bw.allConstraints();
        System.out.println(" ################# TEST Contraintes #################");
        TestSolverBlocksworld(bw);

        BWConstraintesCroissant bw2 = new BWConstraintesCroissant(8, 3);
        bw2.allConstraints();
        System.out.println(" ################# TEST Contraintes Croissantes #################");
        TestSolverBlocksworld(bw2);

        BWRegularyConstraintes bw3 = new BWRegularyConstraintes(8, 3);
        bw3.allConstraints();
        System.out.println(" ################# TEST Contraintes Régulières #################");
        TestSolverBlocksworld(bw3);
    }

        public static void TestSolverBlocksworld(BWConstraintes bw) {

        Set<Variable> variables = new HashSet<>();
        variables.addAll(bw.getBWVariable().getOnb());
        variables.addAll(bw.getBWVariable().getFixedb());
        variables.addAll(bw.getBWVariable().getFreep());

        Set<Solver> solvers = new HashSet<>();
        Solver solver1 = new BacktrackSolver(variables, bw.getConstraints());
        Solver solver2 = new MACSolver(variables, bw.getConstraints());
        Solver solver3 = new HeuristicMACSolver(variables, bw.getConstraints(), new NbConstraintsVariableHeuristic(bw.getConstraints(), true), new RandomValueHeuristic(new Random()));

        solvers.add(solver1);
        solvers.add(solver2);
        solvers.add(solver3);

        for (Solver solver : solvers) {
            System.out.println(" ################# TEST " + solver.getClass().getSimpleName() + " #################");
            long startTime = System.nanoTime();
            Map<Variable, Object> solution = solver.solve();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Temps de calcul : " + duration + " ns");
            System.out.println("Solution : " + solution);
        }
    }

    public static void TestSolverBlocksworld(BWConstraintesCroissant bw) {

        Set<Variable> variables = new HashSet<>();
        variables.addAll(bw.getBWVariable().getOnb());
        variables.addAll(bw.getBWVariable().getFixedb());
        variables.addAll(bw.getBWVariable().getFreep());

        Set<Solver> solvers = new HashSet<>();
        Solver solver1 = new BacktrackSolver(variables, bw.getConstraints());
        Solver solver2 = new MACSolver(variables, bw.getConstraints());
        Solver solver3 = new HeuristicMACSolver(variables, bw.getConstraints(), new NbConstraintsVariableHeuristic(bw.getConstraints(), true), new RandomValueHeuristic(new Random()));

        solvers.add(solver1);
        solvers.add(solver2);
        solvers.add(solver3);

        for (Solver solver : solvers) {
            System.out.println(" ################# TEST " + solver.getClass().getSimpleName() + " #################");
            long startTime = System.nanoTime();
            Map<Variable, Object> solution = solver.solve();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Temps de calcul : " + duration + " ns");
            System.out.println("Solution : " + solution);
        }
    }

        public static void TestSolverBlocksworld(BWRegularyConstraintes bw) {

        Set<Variable> variables = new HashSet<>();
        variables.addAll(bw.getBWVariable().getOnb());
        variables.addAll(bw.getBWVariable().getFixedb());
        variables.addAll(bw.getBWVariable().getFreep());

        Set<Solver> solvers = new HashSet<>();
        Solver solver1 = new BacktrackSolver(variables, bw.getConstraints());
        Solver solver2 = new MACSolver(variables, bw.getConstraints());
        Solver solver3 = new HeuristicMACSolver(variables, bw.getConstraints(), new NbConstraintsVariableHeuristic(bw.getConstraints(), true), new RandomValueHeuristic(new Random()));

        solvers.add(solver1);
        solvers.add(solver2);
        solvers.add(solver3);

        for (Solver solver : solvers) {
            System.out.println(" ################# TEST " + solver.getClass().getSimpleName() + " #################");
            long startTime = System.nanoTime();
            Map<Variable, Object> solution = solver.solve();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Temps de calcul : " + duration + " ns");
            System.out.println("Solution : " + solution);
        }
    }
}
