package blocksworld;

import java.util.*;

import modelling.*;

public class Main {
    
        public static void main(String[] args) {
            
            BWConstraintes bwc = new BWRegularyConstraintes(4, 2);
            BWConstraintes bwc2 = new BWRegularyConstraintes(3, 1);

            System.out.println(" ################# Test Regulary World #################");

            Map<Variable, Object> map = new HashMap<>();
            Map<Variable, Object> map2 = new HashMap<>();

            for (Variable var : bwc.getBWVariable().getAllVars()) {
                String name = var.getName();
                if(name.equals("on_0")) {
                    map.put(var, -1);
                }

                if(name.equals("on_1")) {
                    map.put(var, -2);
                }

                if(name.equals("on_2")) {
                    map.put(var, 0);
                }

                if(name.equals("on_3")) {
                    map.put(var, 1);
                }

                if(name.equals("fixed_0")) {
                    map.put(var, true);
                }

                if(name.equals("fixed_1")) {
                    map.put(var, true);
                }

                if(name.equals("fixed_2")) {
                    map.put(var, false);
                }

                if(name.equals("fixed_3")) {
                    map.put(var, false);
                }

                if(name.equals("free_-1")) {
                    map.put(var, false);
                }

                if(name.equals("free_-2")) {
                    map.put(var, false);
                }
            }

            for (Variable var2 : bwc2.getBWVariable().getAllVars()) {
                String name2 = var2.getName();
                if (name2.equals("on_0")) {
                    map2.put(var2, 1);
                }

                if (name2.equals("on_1")) {
                    map2.put(var2, 2);
                }

                if (name2.equals("on_2")) {
                    map2.put(var2, -1);
                }

                if (name2.equals("fixed_0")) {
                    map2.put(var2, false);
                }

                if (name2.equals("fixed_1")) {
                    map2.put(var2, true);
                }

                if(name2.equals("fixed_2")) {
                    map2.put(var2, true);
                }

                if(name2.equals("free_-1")) {
                    map2.put(var2, false);
                }
            }

            boolean allSatisfied = true;

            for(Constraint cons : bwc.getConstraints()) {
                if(!cons.isSatisfiedBy(map)) {
                    allSatisfied = false;
                }
            }

            for(Constraint cons : bwc2.getConstraints()) {
                if(!cons.isSatisfiedBy(map2)) {
                    allSatisfied = false;
                }
            }

            System.out.println(allSatisfied ? "Constraints satisfied" : "Constraints unsatisfied");
        }     
}
