package blocksworld;

import java.util.*;

import modelling.*;

public class Main {
    
    public static void main(String[] args) {
            
    
        boolean allSatisfied = true;

        System.out.println(" ################# TEST 1 #################");
        allSatisfied = true;
        
        Map<Variable, Object> map1 = new HashMap<>();
        BWConstraintes bwc1 = new BWConstraintes(4, 2);
        
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

            if(name1.equals("Fr-1")) {
                map1.put(variable, false);
            }
            if(name1.equals("Fr-2")) {
                map1.put(variable, false);
            }
        }




        for(Constraint cons : bwc1.getConstraints()) {
            if(!cons.isSatisfiedBy(map1)) {
                System.out.println(cons.toString() + " n'est pas satisfait");
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "valide" : "NOPE");        
        
        
        
        System.out.println(" ################# TEST 2 #################");
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




        for(Constraint cons : bwc2.getConstraints()) {
            if(!cons.isSatisfiedBy(map2)) {
                System.out.println(cons.toString() + " n'est pas satisfait");
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "valide" : "NOPE");
    }
}
