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



    @Override
    public Map<Variable, Object> solve() {
        List<Variable> varList = new ArrayList<>(variables);
        return backtrack(new HashMap<>(), varList);
    }


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
