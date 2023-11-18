package blocksworld;

import java.util.*;
import modelling.*;
import planning.*;


/**
 * BWPlanner
 */
public class BWActions extends BlocksWorld {

    private int nbBlocks;
    private int nbPiles;


    // constructor
    public BWActions(int nbBlocks, int nbPiles) {
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
                    // On num1 = num2
                    // Fi num1 = false
                    // Fi num3 = false
                    Map <Variable, Object> precondition = new HashMap<>();
                    precondition.put(bk1, num2);
                    precondition.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num1], false);
                    precondition.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num3], false);
                    
                    
                    
                    // effects : 
                    // Fi num2 = false
                    // Fi num3 = true
                    // On num1.value = num3
                    Map <Variable, Object> effect = new HashMap<>();
                    effect.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num2], false);
                    effect.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num3], true);
                    effect.put(bk1, num3);

                    // On créé l'action
                    BasicAction action = new BasicAction(precondition, effect, 1);
                    actions.add(action);
                    
                    
                }
            }
        }

        // TYPE2
        for (Variable bk1: variablesOn){
            for (Variable bk2: variablesOn){
                for (Variable p1: variablesFree){
                    if (bk1.getName().equals(bk2.getName())) { 
                        continue;
                    }

                    int num1 = Integer.parseInt(bk1.getName().substring(2));
                    int num2 = Integer.parseInt(bk2.getName().substring(2));
                    int num3 = Integer.parseInt(p1.getName().substring(2));

                    // precondition :
                    // On num1 = num2
                    // Fi num1 = false
                    // Fr num3 = true
                    Map <Variable, Object> precondition = new HashMap<>();
                    precondition.put(bk1, num2);
                    precondition.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num1], false);
                    precondition.put(variablesFree.toArray(new Variable[variablesFree.size()])[num3], true);

                    // effects :
                    // Fi num2 = false
                    // Fr num3 = false
                    // On num1.value = num3
                    Map <Variable, Object> effect = new HashMap<>();
                    effect.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num2], false);
                    effect.put(variablesFree.toArray(new Variable[variablesFree.size()])[num3], false);
                    effect.put(bk1, num3);

                    // On créé l'action
                    BasicAction action = new BasicAction(precondition, effect, 1);
                    actions.add(action);
                }
            }
        }
        

        // TYPE3
        for (Variable bk1: variablesOn){
            for (Variable bk2: variablesOn){
                for (Variable p1: variablesFree){
                    if (bk1.getName().equals(bk2.getName())) { 
                        continue;
                    }

                    int num1 = Integer.parseInt(bk1.getName().substring(2));
                    int num2 = Integer.parseInt(bk2.getName().substring(2));
                    int num3 = Integer.parseInt(p1.getName().substring(2));

                    // precondition :
                    // On num1 = num3
                    // Fi num1 = false
                    // Fi num2 = false
                    Map <Variable, Object> precondition = new HashMap<>();
                    precondition.put(bk1, num3);
                    precondition.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num1], false);
                    precondition.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num2], false);

                    // effects :
                    // Fr num3 = true
                    // Fi num2 = false
                    // On num1.value = num2
                    Map <Variable, Object> effect = new HashMap<>();
                    effect.put(variablesFree.toArray(new Variable[variablesFree.size()])[num3], true);
                    effect.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num2], false);
                    effect.put(bk1, num2);

                    // On créé l'action
                    BasicAction action = new BasicAction(precondition, effect, 1);
                    actions.add(action);
                }
            }
        }

        // TYPE4
        for (Variable bk1: variablesOn){
            for (Variable p1: variablesFree){
                for (Variable p2: variablesFree){
                    if (p1.getName().equals(p2.getName())) { 
                        continue;
                    }

                    int num1 = Integer.parseInt(bk1.getName().substring(2));
                    int num2 = Integer.parseInt(p1.getName().substring(2));
                    int num3 = Integer.parseInt(p2.getName().substring(2));

                    // precondition :
                    // On num1 = num2
                    // Fi num1 = false
                    // Fr num3 = true
                    Map <Variable, Object> precondition = new HashMap<>();
                    precondition.put(bk1, num2);
                    precondition.put(variablesFixed.toArray(new Variable[variablesFixed.size()])[num1], false);
                    precondition.put(variablesFree.toArray(new Variable[variablesFree.size()])[num3], true);

                    // effects :
                    // Fr num2 = true
                    // Fr num3 = false
                    // On num1.value = num3
                    Map <Variable, Object> effect = new HashMap<>();
                    effect.put(variablesFree.toArray(new Variable[variablesFree.size()])[num2], true);
                    effect.put(variablesFree.toArray(new Variable[variablesFree.size()])[num3], false);
                    effect.put(bk1, num3);

                    // On créé l'action
                    BasicAction action = new BasicAction(precondition, effect, 1);
                    actions.add(action);

                }
            }
        }

    

        return actions;
    }

    // get allactions of actions
    public Set<Action> getActions() {
        return new HashSet<Action>(allActions());
    }
}