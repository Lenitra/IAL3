package blocksworld;

import java.util.*;

import modelling.*;

public class Main {
    
    public static void main(String[] args) {
            
        boolean allSatisfied = true;
        
        System.out.println(" ################# Test Regulary World #################");
        
        BWConstraintes bwc = new BWRegularyConstraintes(4, 2);
        Map<Variable, Object> map = new HashMap<>();
        
        for (Variable var : bwc.getBWVariable().getAllVars()) {
            String name = var.getName();
            if(name.equals("On0")) {
                map.put(var, -1);
            }
            
            if(name.equals("On1")) {
                map.put(var, -2);
            }
            
            if(name.equals("On2")) {
                map.put(var, 0);
            }
            
            if(name.equals("On3")) {
                map.put(var, 1);
            }
            
            if(name.equals("Fi0")) {
                map.put(var, true);
            }
            
            if(name.equals("Fi1")) {
                map.put(var, true);
            }
            
            if(name.equals("Fi2")) {
                map.put(var, false);
            }
            
            if(name.equals("Fi3")) {
                map.put(var, false);
            }
            
            if(name.equals("Fr-1")) {
                map.put(var, false);
            }
            
            if(name.equals("Fr-2")) {
                map.put(var, false);
            }
        }
        
        for(Constraint cons : bwc.getConstraints()) {
            if(!cons.isSatisfiedBy(map)) {
                allSatisfied = false;
            }
        }
        
        System.out.println(allSatisfied ? "test 1 OK" : "Test 1 pété");
        
        Map<Variable, Object> map2 = new HashMap<>();
        BWConstraintes bwc2 = new BWConstraintes(3, 1);
        
        for (Variable var2 : bwc2.getBWVariable().getAllVars()) {
            String name2 = var2.getName();
            if (name2.equals("On0")) {
                map2.put(var2, 1);
            }
            
            if (name2.equals("On1")) {
                map2.put(var2, 2);
            }
            
            if (name2.equals("On2")) {
                map2.put(var2, -1);
            }
            
            if (name2.equals("Fi0")) {
                map2.put(var2, false);
            }
            
            if (name2.equals("Fi1")) {
                map2.put(var2, true);
            }

            if(name2.equals("Fi2")) {
                map2.put(var2, true);
            }

            if(name2.equals("Fr-1")) {
                map2.put(var2, true); // Valeur de Fr-1 doit être sur false
            }
        }




        for(Constraint cons : bwc2.getConstraints()) {
            System.out.println(cons.toString());
            if(!cons.isSatisfiedBy(map2)) {
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "test 2 OK" : "Test 2 pété");
        
        
        
        System.out.println(allSatisfied ? "Constraints satisfied" : "Constraints unsatisfied");
        
        
    }
}
