package blocksworld;

import java.util.*;
import modelling.*;
import planning.Action;
import planning.BasicAction;


/**
 * BWPlanner
 */
public class BWPlanner extends BlocksWorld {

    private int nbBlocks;
    private int nbPiles;


    // constructor
    public BWPlanner(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
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

        // On créé les actions

        // TYPE1
        for (Variable bk1 : variablesOn) {
            for (Variable bk2 : variablesOn) {
                for (Variable bk3: variablesOn){
                    // On vériifie que les blocs sont différents
                    if (bk1.getName().equals(bk2.getName())) { 
                        continue;
                    }
                    if (bk1.getName().equals(bk3.getName())) {
                        continue;
                    }
                    if (bk2.getName().equals(bk3.getName())) { 
                        continue;
                    }

                    
                
                    
                    
                    // on récupère les numéros des blocs
                    int num1 = Integer.parseInt(bk1.getName().substring(2));
                    int num2 = Integer.parseInt(bk2.getName().substring(2));
                    int num3 = Integer.parseInt(bk3.getName().substring(2));

                    
                    
                    // precondition :
                    // On num1 = On num2.name
                    // Fi num1 = false
                    // Fi num3 = false
                    Map <Variable, Object> precondition = new HashMap<>();
                    precondition.put(bk1, bk2.getName());
                    precondition.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num1], false);
                    precondition.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num3], false);
                    
                    
                    
                    // effects : 
                    // Fi num2 = false
                    // Fi num3 = true
                    // On num1.value = On num3.name
                    Map <Variable, Object> effect = new HashMap<>();
                    effect.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num2], false);
                    effect.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num3], true);
                    effect.put(bk1, bk3.getName());

                    // On créé l'action
                    BasicAction action = new BasicAction(precondition, effect, 1);
                    actions.add(action);
                    
                    
                }
            }
        }

        

    

        return actions;
    }

}