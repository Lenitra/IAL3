package cp;

import java.util.Set;

import modelling.Variable;

public interface ValueHeuristic {
        
        public Set<Object> ordering (Variable var, Set<Object> domain);
}
