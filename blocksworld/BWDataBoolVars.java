import java.util.HashSet;
import java.util.Set;

import modelling.BooleanVariable;
import modelling.Variable;

public class BWDataBoolVars extends BWVariable {
    
    protected int nbBlocks;
    protected int nbPiles;

    public BWDataBoolVars(int nbBlocks, int nbPiles) {
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
            resultat.add(new BooleanVariable("Fi" + num1));
            // Contrainte de type On (Un bloc ne peut pas être sur lui même)
            for(Variable var2 : variablesOn){
                int num2 = Integer.parseInt(var2.getName().substring(2)); // on récupère le numéro du bloc
                
                // Onb,b1
                resultat.add(new BooleanVariable("On" + num1 + "," + num2));
            }



            for(Variable pil1 : variablesFree){
                int numPil1 = Integer.parseInt(pil1.getName().substring(2)); // on récupère le numéro de la pile
                // Variable on-tableb,p 
                resultat.add(new BooleanVariable("on-table" + num1 + "," + numPil1));
            }
        }

        // Variables de type Free
        for(Variable pil1 : variablesFree){
            int numPil1 = Integer.parseInt(pil1.getName().substring(2)); // on récupère le numéro de la pile
            resultat.add(new BooleanVariable("Fr" + numPil1));
        }
    }
}
