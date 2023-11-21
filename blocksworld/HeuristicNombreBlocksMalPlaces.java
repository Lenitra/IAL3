package blocksworld;

import java.util.Map;

import modelling.Variable;
import planning.Heuristic;

// Classe qui permet de calculer l'heuristique du nombre de blocks mal placés
public class HeuristicNombreBlocksMalPlaces implements Heuristic{

    // Etat final du problème
    private Map<Variable, Object> goal;

    public HeuristicNombreBlocksMalPlaces(Map<Variable, Object> goal) {
        this.goal = goal;
    }

    /**
     * Méthode qui permet d'estimer le nombre de blocks mal placés
     */
    @Override
    public float estimate(Map<Variable, Object> state) {
        // On compte le nombre de blocks mal placés
        float estiblocmalplace = 0;
        int nbvar = 0;
        // On parcourt les variables de l'état
        for (Variable var : state.keySet()) {
          nbvar += 1;
          // On regarde si la variable est une variable de position et si elle est mal placée
          if (var.getName().startsWith("On") && !state.get(var).equals(goal.get(var))) {
            estiblocmalplace += 1;
          }
        }
        return estiblocmalplace/nbvar;
      }
    
}
