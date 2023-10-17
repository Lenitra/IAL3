package cp;

import java.util.Map;
import java.util.Set;

import modelling.Variable;

public class DomainSizeVariableHeuristic implements VariableHeuristic{
    
    private boolean bool;

    public DomainSizeVariableHeuristic(boolean bool) {
        this.bool = bool;
    }

    @Override
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domaines) {
        Variable best = null;
        int sizeref = 0;
        for (Variable var : variables) {
            int sizedom = domaines.get(var).size();

            if (best == null) {
                best = var;
                sizeref = sizedom;
            } else {
                if (bool) {
                    if (sizedom > sizeref) {
                        best = var;
                        sizeref = sizedom;
                    }
                } else {
                    if (sizedom < sizeref) {
                        best = var;
                        sizeref = sizedom;
                    }
                }
            }
        }
        return best;
    }
}
