package blocksworld;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import modelling.Constraint;
import modelling.Implication;
import modelling.Variable;

/**
 * Classe BWConstraintesCroissant dérivée de BWConstraintes.
 * Cette classe est utilisée pour générer des contraintes croissantes
 * dans le monde des blocs (BlocksWorld).
 */
public class BWConstraintesCroissant extends BWConstraintes {

    protected int nbBlocks; // Nombre de blocs dans le monde des blocs
    protected int nbPiles;  // Nombre de piles dans le monde des blocs

    /**
     * Constructeur pour la classe BWConstraintesCroissant.
     *
     * @param nbBlocks Nombre de blocs dans le monde des blocs.
     * @param nbPiles  Nombre de piles dans le monde des blocs.
     */
    public BWConstraintesCroissant(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
        this.constraints = generateConstraints();
    }

    /**
     * Méthode pour générer les contraintes croissantes.
     * 
     * @return Un ensemble de contraintes pour le monde des blocs.
     */
    public Set<Constraint> generateConstraints() {
        Set<Constraint> constraints = new HashSet<>();

        // Générer les identifiants des piles sous forme de nombres négatifs
        Set<Integer> piles = new HashSet<>();
        for (int i = -1; i >= -nbPiles; i--) {
            piles.add(i);
        }

        // Obtenir toutes les permutations possibles des variables onb
        Collection<Variable> permutations = getOnbV();

        // Créer des contraintes pour chaque couple de variables
        for (Variable v : permutations) {
            for (Variable v2 : permutations) {
                if (!v.equals(v2)) { // S'assurer que v et v2 ne sont pas identiques
                    int v2S = bw.getIndex(v); // Obtenir l'index de v dans bw

                    Set<Object> ens1 = new HashSet<>();
                    ens1.add(v2S);

                    Set<Integer> ens2 = new HashSet<>();
                    for (int j = 0; j < v2S; j++) {
                        ens2.add(j);
                    }

                    ens1.addAll(ens2);

                    // Créer une nouvelle contrainte d'implication
                    Constraint c1 = new Implication(v, Set.of(v2S), v2, ens1);
                    constraints.add(c1); // Ajouter la contrainte à l'ensemble
                }
            }
        }
        return constraints; // Retourner l'ensemble de contraintes
    }
}
