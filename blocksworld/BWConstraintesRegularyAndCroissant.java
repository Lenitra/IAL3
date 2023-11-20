package blocksworld;

import modelling.Constraint;
import modelling.Implication;
import modelling.Variable;

import java.util.HashSet;
import java.util.Set;

public class BWConstraintesRegularyAndCroissant extends BWRegularyConstraintes {

    public BWConstraintesRegularyAndCroissant(int block, int pile) {
        super(block, pile);
    }

    @Override
    public Set<Constraint> allConstraints() {
        Set<Constraint> resultat = super.allConstraints(); // On récupère les contraintes de la classe parente

        BWVariable variables = new BWVariable(this.nbBlocks, this.nbPiles); // On créé les variables

        // Obtention des variables de type "On"
        Set<Variable> variablesOn = variables.getOnb();

        for (Variable variable1 : variablesOn) {
            int blockNum1 = Integer.parseInt(variable1.getName().substring(2)); // Numéro du bloc de la variable1

            for (Variable variable2 : variablesOn) {
                int blockNum2 = Integer.parseInt(variable2.getName().substring(2)); // Numéro du bloc de la variable2

                // Ajout d'une implication pour la régularité
                // si on1 = blockNum1 alors on2 != blockNum1
                if (blockNum1 != blockNum2) {
                    Set<Object> valeurs = new HashSet<>();
                    int ecart = blockNum2 * 2 - blockNum1;
                    valeurs.add(ecart);

                    for (int i = 1; i <= nbPiles; i++) {
                        valeurs.add(-i);
                    }
                    Constraint constraint = new Implication(variable1, Set.of(blockNum2), variable2, valeurs);
                    resultat.add(constraint);
                }
            }
        }

        // Add constraints for croissant
        Set<Constraint> croissantConstraints = getConstraints();
        resultat.addAll(croissantConstraints);

        return resultat;
    }
}
