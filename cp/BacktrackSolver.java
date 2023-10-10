package cp;

import java.util.*;

import modelling.Constraint;
import modelling.Variable;



public class BacktrackSolver extends AbstractSolver {
    Set<Variable> variables;
    Set<Constraint> constraints;


    public BacktrackSolver(Set<Variable> variables, Set<Constraint> constraints) {
        super(variables, constraints);
        this.variables = variables;
        this.constraints = constraints;
    }



    // Pour la définition de la méthode solve, on pourra faire appel à une seconde méthode, récursive, qui prend en argument une solution partielle et une liste de variables non instanciées, et retourne elle-même une solution étendant la solution partielle, ou null s’il n’y en a pas.

    @Override
    public Map<Variable, Object> solve() {
        List<Variable> varList = new ArrayList<>(variables);
        return backtrack(new HashMap<>(), varList);
    }


    /**
     * Methode recursive qui satisfait des contraintes en fonction des variables et des contraintes du probleme
     * 
     * 
     * @param partialAssignment une affectation partielle
     * @param varList une liste de variables non instanciées
     * @return une solution etandant la solution partielle si elle existe, null s'il y en a pas
     * 
     * 
     */
    private Map<Variable, Object> backtrack(Map<Variable, Object> partialAssignment, List<Variable> varList) {
    	List<Variable>  variList = new ArrayList<>(varList);
        if (variList.isEmpty()) {
            return partialAssignment;
        }
        
        Variable var = variList.get(0);
        for (Object value : var.getDomain()) {
        	Map<Variable, Object> partialAssignment2 = new HashMap<Variable , Object>(partialAssignment);
            partialAssignment2.put(var, value);
            if (isConsistent(partialAssignment2)) {
                Map<Variable, Object> result = backtrack(partialAssignment2, variList.subList(1, variList.size()));
                if (result != null) {
                    return result;
                }
            }
           
        }
        variList.add(var);
        return null;
    }

}
