package planning;

import java.util.List;
import java.util.Map;
import java.util.Set;

import modelling.Variable;

public class AStarPlanner implements Planner {

    // constructeur prend en arguments, dans cet ordre, un état initial, un ensemble d’actions, un but (des mêmes types que les autres classes de planificateurs), et une heuristique (de type Heuristic).
    public AStarPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal, Heuristic heuristic) {
        
    }





    @Override
    public List<Action> plan() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'plan'");
    }

    @Override
    public Map<Variable, Object> getInitialState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInitialState'");
    }

    @Override
    public Set<Action> getActions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActions'");
    }

    @Override
    public Goal getGoal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGoal'");
    }
    
}
