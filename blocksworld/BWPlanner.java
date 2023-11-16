package blocksworld;

import java.util.*;
import modelling.*;
import planning.Action;


/**
 * BWPlanner
 */
public class BWPlanner {

    private int nbBlocks;
    private int nbPiles;
    private 

    // constructor
    BWPlanner(int nbBlocks, int nbPiles) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
        
    }


    // get all actions
    public ArrayList<Action> allActions() {
        ArrayList<Action> actions = new ArrayList<Action>();
        BWVariable variables = new BWVariable(this.nbBlocks, this.nbPiles); // On créé les variables
        Set<Variable> variablesOn = variables.getOnb();
        Set<Variable> variablesFixed = variables.getFixedb();
        Set<Variable> variablesFree = variables.getFreep();

    

        return actions;
    }

}