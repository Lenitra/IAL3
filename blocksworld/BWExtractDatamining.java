import java.util.HashSet;
import java.util.Set;

import modelling.Constraint;
import modelling.Variable;

public class BWExtractDatamining extends BWVariable {
    
    protected int nbBlocks;
    protected int nbPiles;

    public BWExtractDatamining(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
    }

    public void setAllBoolVars() {
        Set<Variable> resultat = new HashSet<>(); // ensemble des contraintes
        BWVariable variables = new BWVariable(this.nbBlocks, this.nbPiles); // On créé les variables
        Set<Variable> variablesOn = variables.getOnb();
        Set<Variable> variablesFixed = variables.getFixedb();
        Set<Variable> variablesFree = variables.getFreep();

        // Contrainte de type Onb,b1
        for(Variable var1 : variablesOn){ // On loop sur les variables de de blocks On
            int num1 = Integer.parseInt(var1.getName().substring(2)); // on récupère le numéro du bloc

            // Contrainte de type On (Un bloc ne peut pas être sur lui même)
            for(Variable var2 : variablesOn){
                int num2 = Integer.parseInt(var2.getName().substring(2)); // on récupère le numéro du bloc
                
                // create a new variable
                if(var1.equals(num2)){ //TODO: check if it's the right way to do it : (if value of var1 == num2)
                    Variable newVar = new Variable("On" + num1 + "," + num2, Set.of(true));
                    resultat.add(newVar);
                } else {
                    Variable newVar = new Variable("On" + num1 + "," + num2, Set.of(false));
                    resultat.add(newVar);
                }
            }

            for(Variable pil1 : variablesFree){
                int numPil1 = Integer.parseInt(pil1.getName().substring(2)); // on récupère le numéro de la pile

                // Variable on-tableb,p 
                if (var1.equals(-numPil1)){ //TODO: check if it's the right way to do it : (if value of var1 == numPil1)
                    Variable newVar = new Variable("on-table" + num1 + "," + numPil1, Set.of(true));
                } else {
                    Variable newVar = new Variable("on-table" + numPil1, Set.of(false));
                }
            }
            
        }
    }

    
}
