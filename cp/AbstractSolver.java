package cp;

import java.util.Map;
import java.util.Set;

import modelling.Constraint;
import modelling.Variable;

public abstract class AbstractSolver implements Solver{
    //constructeur qui prend un ensemble de variables (de type Set<Variable>) et un ensemble de contraintes (de type Set<Constraint>) en arguments
    //et qui initialise les attributs correspondants
    protected Set<Variable> variables;
    protected Set<Constraint> constraints;

    public AbstractSolver(Set<Variable> variables, Set<Constraint> constraints) {
        this.variables = variables;
        this.constraints = constraints;
    }

    
    public boolean isConsistent(Map<Variable, Object> solution) {
        for (Constraint c : constraints) {
            if (solution.keySet().containsAll(c.getScope())) {
                if (!c.isSatisfiedBy(solution)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Set<Variable> getVariables() {
        return variables;
    }

}
