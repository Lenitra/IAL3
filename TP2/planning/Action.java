package planning;
import modelling.Variable;
import java.util.Map;

public interface Action {
    public boolean isApplicable(Map<Variable, Object> state);

    public Map<Variable, Object> successor(Map<Variable, Object> state);

    public int getCost();

}
