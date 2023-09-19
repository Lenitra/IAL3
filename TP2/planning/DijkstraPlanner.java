package planning;

import java.util.*;

import modelling.Variable;

public class DijkstraPlanner implements Planner {

    private Set<Map<Variable, Object>> not_visited;
    private Map<Map<Variable, Object>, Map<Variable, Object>> father;
    // ditance 
    private Map<Map<Variable, Object>, Integer> distance;

    public DijkstraPlanner(Map<Variable, Object> initialState, Set<Action> actions, Goal goal) {
        this.not_visited = new HashSet<>();
        this.not_visited.add(initialState);
        this.father = new HashMap<>();
        this.father.put(initialState, null);
        this.distance = new HashMap<>();
        this.distance.put(initialState, 0);
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