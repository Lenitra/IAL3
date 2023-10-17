package cp;

import java.util.*;

import modelling.Variable;

/**
 * Classe qui genere des valeurs aleatoires
 */
public class RandomValueHeuristic implements ValueHeuristic {

    /**
     * generateur aleatoire
     */
    protected Random random = new Random();

    /**
     * Constructeur de la classe RandomValueHeuristic 
     * @param random generateur aleatoire
     *
     */
    public RandomValueHeuristic(Random random) {
        this.random = random;
    }

    /**
     * La methode ordering() retourne une liste contenant les valeurs du domaine, ordonnées selon la heuristique.
     * @param var la variable
     * @param domains ensemble des domaines
     * @return liste mélangée uniformément 
     *
     */
    @Override
    public Set<Object> ordering(Variable var, Set<Object> domains) {
        List<Object> list = new ArrayList<>();
        for (Object obj : domains) {
            list.add(obj);
        }
        Collections.shuffle(list, random);
        // parse list to set
        Set<Object> set = new HashSet<>();
        for (Object obj : list) {
            set.add(obj);
        }
        return set;
    }

    
}
