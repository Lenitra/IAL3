

import java.util.*;

import modelling.*;

public class MainConstraintes {
    
    public static void main(String[] args) {
            
    
        boolean allSatisfied = true;

        System.out.println(" ################# TEST Contraintes #################");
        allSatisfied = true;
        
        Map<Variable, Object> map1 = new HashMap<>();
        BWConstraintes bwc1 = new BWConstraintes(4, 3);
        
        for (Variable variable : bwc1.getBWVariable().getAllVars()) {
            String name1 = variable.getName();
            if (name1.equals("On0")) {
                map1.put(variable, 1);
            }
            
            if (name1.equals("On1")) {
                map1.put(variable, 2);
            }
            
            if (name1.equals("On2")) {
                map1.put(variable, -1);
            }

            if (name1.equals("On3")) {
                map1.put(variable, -2);
            }
            if(name1.equals("On4")) {
                map1.put(variable, -3);
            }
            
            if (name1.equals("Fi0")) {
                map1.put(variable, false);
            }
            
            if (name1.equals("Fi1")) {
                map1.put(variable, true);
            }

            if(name1.equals("Fi2")) {
                map1.put(variable, true);
            }
            if(name1.equals("Fi3")) {
                map1.put(variable, false);
            }
            if(name1.equals("Fi4")) {
                map1.put(variable, false);
            }

            if(name1.equals("Fr-1")) {
                map1.put(variable, false);
            }
            if(name1.equals("Fr-2")) {
                map1.put(variable, false);
            }
            if(name1.equals("Fr-3")) {
                map1.put(variable, false);
            }
        }

        System.out.println(bwc1.getConstraints().size());
        for(Constraint cons : bwc1.getConstraints()) {
            if(!cons.isSatisfiedBy(map1)) {
                System.out.println(cons.toString() + " n'est pas satisfait");
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "valide" : "NOPE");        
        
        
        
        System.out.println(" ################# TEST Contraintes #################");
        allSatisfied = true;
        
        Map<Variable, Object> map2 = new HashMap<>();
        BWConstraintes bwc2 = new BWConstraintes(3, 1);
        
        for (Variable variable : bwc2.getBWVariable().getAllVars()) {
            String name2 = variable.getName();
            if (name2.equals("On0")) {
                map2.put(variable, 1);
            }
            
            if (name2.equals("On1")) {
                map2.put(variable, 2);
            }
            
            if (name2.equals("On2")) {
                map2.put(variable, -1);
            }
            
            if (name2.equals("Fi0")) {
                map2.put(variable, false);
            }
            
            if (name2.equals("Fi1")) {
                map2.put(variable, true);
            }

            if(name2.equals("Fi2")) {
                map2.put(variable, true);
            }

            if(name2.equals("Fr-1")) {
                map2.put(variable, false);
            }
        }

        System.out.println(bwc2.getConstraints().size());
        for(Constraint cons : bwc2.getConstraints()) {
            if(!cons.isSatisfiedBy(map2)) {
                System.out.println(cons.toString() + " n'est pas satisfait");
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "valide" : "NOPE");


        System.out.println(" ################# TEST Contraintes Croissantes #################");
        allSatisfied = true;
    
        // test de la contrainte croissante
        Map<Variable, Object> map3 = new HashMap<>();
        BWConstraintesCroissant bwc3 = new BWConstraintesCroissant(3, 1);

        for (Variable variable : bwc3.getBWVariable().getAllVars()) {
            String name3 = variable.getName();
            if (name3.equals("On0")) {
                map3.put(variable, 1);
            }
            
            if (name3.equals("On1")) {
                map3.put(variable, 2);
            }
            
            if (name3.equals("On2")) {
                map3.put(variable, -1);
            }
            
            if (name3.equals("Fi0")) {
                map3.put(variable, false);
            }
            
            if (name3.equals("Fi1")) {
                map3.put(variable, true);
            }

            if(name3.equals("Fi2")) {
                map3.put(variable, true);
            }

            if(name3.equals("Fr-1")) {
                map3.put(variable, false);
            }
        }

        System.out.println(bwc3.getConstraints().size());
        for(Constraint cons : bwc3.getConstraints()) {
            if(!cons.isSatisfiedBy(map3)) {
                System.out.println(cons.toString() + " n'est pas satisfait");
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "valide" : "NOPE");


        System.out.println(" ################# TEST Regulary Constraint #################");
        allSatisfied = true;

        //test de la regulary constraint
        Map<Variable, Object> map4 = new HashMap<>();
        BWRegularyConstraintes bwc4 = new BWRegularyConstraintes(10, 4);

        for (Variable variable : bwc4.getBWVariable().getAllVars()) {
            String name4 = variable.getName();
            if (name4.equals("On0")) {
                map4.put(variable, -2);
            }
        
            if (name4.equals("On1")) {
                map4.put(variable, 3);
            }
        
            if (name4.equals("On2")) {
                map4.put(variable, 9);
            }
            
            if (name4.equals("On3")) {
                map4.put(variable, 5);
            }

            if (name4.equals("On4")) {
                map4.put(variable, 0);
            }

            if (name4.equals("On5")) {
                map4.put(variable, 7);
            }

            if (name4.equals("On6")) {
                map4.put(variable, -4);
            }

            if (name4.equals("On7")) {
                map4.put(variable, -1);
            }

            if (name4.equals("On8")) {
                map4.put(variable, 4);
            }

            if (name4.equals("On9")) {
                map4.put(variable, -3);
            }



            if (name4.equals("Fi0")) {
                map4.put(variable, true);
            }
            
            if (name4.equals("Fi1")) {
                map4.put(variable, false);
            }

            if(name4.equals("Fi2")) {
                map4.put(variable, false);
            }

            if(name4.equals("Fi3")) {
                map4.put(variable, true);
            }

            if(name4.equals("Fi4")) {
                map4.put(variable, true);
            }

            if(name4.equals("Fi5")) {
                map4.put(variable, true);
            }

            if(name4.equals("Fi6")) {
                map4.put(variable, false);
            }

            if(name4.equals("Fi7")) {
                map4.put(variable, true);
            }

            if(name4.equals("Fi8")) {
                map4.put(variable, false);
            }

            if(name4.equals("Fi9")) {
                map4.put(variable, true);
            }




            // Free (piles)
            if(name4.equals("Fr-1")) {
                map4.put(variable, false);
            }
            if(name4.equals("Fr-2")) {
                map4.put(variable, false);
            }
            if(name4.equals("Fr-3")) {
                map4.put(variable, false);
            }
            if(name4.equals("Fr-4")) {
                map4.put(variable, false);
            }
        }

        for(Constraint cons : bwc4.getConstraints()) {

            if(!cons.isSatisfiedBy(map4)) {
                System.out.println(cons.toString() + " n'est pas satisfait");
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "valide" : "NOPE");


    }

}
