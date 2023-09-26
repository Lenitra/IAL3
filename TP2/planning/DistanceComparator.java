package planning;

import java.util.Map;
import java.util.Comparator;

import modelling.Variable;

public class DistanceComparator implements Comparator<Map<Variable, Object>> {

    private Map<Map<Variable, Object>, Float> distance;

    public DistanceComparator(Map<Map<Variable, Object>, Float> distance) {
        this.distance = distance;
    }

    /**
     * Compare deux états en fonction de leur distance
     * 
     * @param map0 Le premier état à comparer
     * @param map1 Le deuxième état à comparer
     * @return     Un entier négatif, zéro ou positif si le premier état est plus petit, égal ou plus grand que le deuxième
     */
    @Override
    public int compare(Map<Variable, Object> map0, Map<Variable, Object> map1) {
        float distance0 = distance.get(map0);
        float distance1 = distance.get(map1);
        return Float.compare(distance0, distance1);
    }

}
