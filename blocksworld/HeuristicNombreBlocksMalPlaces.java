package blocksworld;

import java.util.*;

import modelling.Variable;
import planning.Heuristic;

public class HeuristicNombreBlocksMalPlaces implements Heuristic{

    // Etat final du problème
    private Map<Variable, Object> finalState;

    public HeuristicNombreBlocksMalPlaces(Map<Variable, Object> finalState) {
        this.finalState = finalState;
    }

    @Override
    public float estimate(Map<Variable, Object> state) {
        // On compte le nombre de blocks mal placés
        float estiblocmalplace = 0;
        // On parcourt les variables de l'état
        for (Variable var : state.keySet()) {
        // On regarde si la variable est une variable de position et si elle est mal placée
          if (var.getName().startsWith("On") && !state.get(var).equals(finalState.get(var))) {
            estiblocmalplace += 1;
          }
        }
        return estiblocmalplace;
      }
    
}
