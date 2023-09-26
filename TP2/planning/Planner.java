package planning;

import java.util.List;
import java.util.Map;
import java.util.Set;

import modelling.Variable;

public interface Planner {

    public List<Action> plan();

    public Goal getGoal();

    public Map<Variable, Object> getInitialState();

    public Set<Action> getActions();
}
