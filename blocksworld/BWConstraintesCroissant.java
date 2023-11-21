package blocksworld;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import modelling.Constraint;
import modelling.Implication;
import modelling.Variable;

// Classe qui représente les contraintes croissantes du problème
public class BWConstraintesCroissant extends BWConstraintes {

    protected int nbBlocks; // nombre de blocs
    protected int nbPiles; // nombre de piles

    public BWConstraintesCroissant(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
        this.constraints = generateConstraints();
    }

    /**
     * Méthode qui retourne l’ensemble des contraintes croissante du problème
     * @return un set de contraintes croissantes
     */
    public Set<Constraint> generateConstraints() {
        // Création d'un set de contraintes vide
        Set<Constraint> constraints = new HashSet<>();

        // Création d'un set de piles
        Set<Integer> piles = new HashSet<>();
        for (int i = -1; i >= -nbPiles; i--) {
            piles.add(i);
        }

        // Création d'un set de blocs
        Collection<Variable> ColVarOnb = getOnbV();

        // On parcourt les variables de type "On"
        for (Variable v : ColVarOnb) {
            for (Variable v2 : ColVarOnb) {
                if (!v.equals(v2)) { 
                    int num = bw.getIndex(v);

                    // Création d'un set de valeurs qui contient le numéro du bloc
                    Set<Object> ens1 = new HashSet<>();
                    ens1.add(num);

                    // Création d'un set de valeurs qui contient toutes les piles
                    Set<Integer> ens2 = new HashSet<>();
                    for (int j = 0; j < num; j++) {
                        ens2.add(j);
                    }
                    ens1.addAll(ens2);
                    
                    // Création d'une contrainte d'implication
                    Constraint c1 = new Implication(v, Set.of(num), v2, ens1);
                    constraints.add(c1);
                }
            }
        }
        return constraints;
    }
}
