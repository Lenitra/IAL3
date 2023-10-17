package cp;

import java.util.Map;
import java.util.Set;

import modelling.Variable;

public class DomainSizeVariableHeuristic implements VariableHeuristic{
    
    protected boolean bool;

    public DomainSizeVariableHeuristic(boolean bool) {
        this.bool = bool;
    }

    @Override
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domains) {
        Variable best = null;
        int size = 0;
        for (Variable var : variables) {
            int domainSize = domains.get(var).size();
            if (best == null) {
                best = var;
                size = domainSize;
            } else {
                if (bool) {
                    if (domainSize > size) {
                        best = var;
                        size = domainSize;
                    }
                } else if (!bool) {
                    if (domainSize < size) {
                        best = var;
                        size = domainSize;
                    }
                }
            }
        }
        return best;
    }


}
