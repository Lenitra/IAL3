package planning;

import java.util.Map;
import java.util.Comparator;

import modelling.Variable;

public class DistanceComparator implements Comparator<Map<Variable, Object>> {

    private Map<Map<Variable, Object>, Float> distance;

    public DistanceComparator(Map<Map<Variable, Object>, Float> distance) {
        this.distance = distance;
    }

    @Override
    public int compare(Map<Variable, Object> map0, Map<Variable, Object> map1) {
        float distance0 = distance.get(map0);
        float distance1 = distance.get(map1);
        return Float.compare(distance0, distance1);
    }

}
