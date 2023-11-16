package blocksworld;

import java.util.*;

import modelling.*;

public class Main {
    
    public static void main(String[] args) {
            
        boolean allSatisfied = true;
        BWConstraintes bwc = new BWRegularyConstraintes(4, 2);
        BWConstraintes bwc2 = new BWRegularyConstraintes(3, 1);

        System.out.println(" ################# Test Regulary World #################");
        
        Map<Variable, Object> map = new HashMap<>();
        Map<Variable, Object> map2 = new HashMap<>();

        for (Variable var : bwc.getBWVariable().getAllVars()) {
            String name = var.getName();
            if(name.equals("On_0")) {
                map.put(var, -1);
            }

            if(name.equals("On_1")) {
                map.put(var, -2);
            }

            if(name.equals("On_2")) {
                map.put(var, 0);
            }

            if(name.equals("On_3")) {
                map.put(var, 1);
            }

            if(name.equals("Fi_0")) {
                map.put(var, true);
            }

            if(name.equals("Fi_1")) {
                map.put(var, true);
            }

            if(name.equals("Fi_2")) {
                map.put(var, false);
            }

            if(name.equals("Fi_3")) {
                map.put(var, false);
            }

            if(name.equals("Fr_-1")) {
                map.put(var, false);
            }
            
            if(name.equals("Fr_-2")) {
                map.put(var, false);
            }
        }
        
        for(Constraint cons : bwc.getConstraints()) {
            if(!cons.isSatisfiedBy(map)) {
                allSatisfied = false;
            }
        }
        
        System.out.println(allSatisfied ? "test 1 OK" : "Test 1 pété");


        for (Variable var2 : bwc2.getBWVariable().getAllVars()) {
            String name2 = var2.getName();
            if (name2.equals("On_0")) {
                map2.put(var2, 1);
            }

            if (name2.equals("On_1")) {
                map2.put(var2, 2);
            }

            if (name2.equals("On_2")) {
                map2.put(var2, -1);
            }

            if (name2.equals("Fi_0")) {
                map2.put(var2, false);
            }

            if (name2.equals("Fi_1")) {
                map2.put(var2, true);
            }

            if(name2.equals("Fi_2")) {
                map2.put(var2, true);
            }

            if(name2.equals("Fr_-1")) {
                map2.put(var2, false);
            }
        }




        for(Constraint cons : bwc2.getConstraints()) {
            if(!cons.isSatisfiedBy(map2)) {
                allSatisfied = false;
            }
        }
        System.out.println(allSatisfied ? "test 2 OK" : "Test 2 pété");
        
        
        
        System.out.println(allSatisfied ? "Constraints satisfied" : "Constraints unsatisfied");
        
        
    }
}
