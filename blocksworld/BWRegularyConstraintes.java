package blocksworld;

import java.util.HashSet;
import java.util.Set;

import modelling.Constraint;
import modelling.Implication;
import modelling.Variable;

// Classe qui représente les contraintes régulières du problème
public class BWRegularyConstraintes extends BWConstraintes {
    
    public BWRegularyConstraintes(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
    }

    /**
     * Méthode qui retourne l’ensemble des contraintes régulières du problème
     * @return un set de contraintes régulières
     */
    @Override
    public Set<Constraint> allConstraints() {
        Set<Constraint> resultat = super.allConstraints(); // On récupère les contraintes de la classe parente

        BWVariable variables = new BWVariable(this.nbBlocks, this.nbPiles); // On créé les variables

        // Obtention des variables de type "On"
        Set<Variable> variablesOn = variables.getOnb();

        // On parcourt les variables de type "On"
        for (Variable variable1 : variablesOn) {
            // On récupère le numéro du bloc en retirant le "On" de la variable
            int blockNum1 = Integer.parseInt(variable1.getName().substring(2));

            // On parcourt les variables de type "On"
            for (Variable variable2 : variablesOn) {
                // On récupère le numéro du bloc en retirant le "On" de la variable
                int blockNum2 = Integer.parseInt(variable2.getName().substring(2));

                // Ajout d'une implication pour la régularité
                // si on1 = blockNum1 alors on2 != blockNum1
                if (blockNum1 != blockNum2) {
                    Set<Object> valeurs = new HashSet<>();
                    int ecart = blockNum2*2 - blockNum1;
                    valeurs.add(ecart);
                    
                    for(int i = 1; i <= nbPiles; i++) {
                        valeurs.add(-i);
                    }
                    Constraint constraint = new Implication(variable1, Set.of(blockNum2), variable2, valeurs);
                    resultat.add(constraint);
                }
            }
        }
        // On retourne l'ensemble des contraintes régulières
        return resultat;
    }
}
