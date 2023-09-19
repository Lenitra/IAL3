package planning;

import java.util.*;

import modelling.Variable;

public interface Heuristic {
    
        public float estimate(Map<Variable, Object> state, Goal goal);

}
