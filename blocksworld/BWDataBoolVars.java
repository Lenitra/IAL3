package blocksworld;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelling.BooleanVariable;

public class BWDataBoolVars extends BWVariable {

    private Map<String, Map<String, BooleanVariable>> booleanMap;
    
    public BWDataBoolVars(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
        this.booleanMap = new HashMap<>();
        initializeBooleanMap();
    }

    private void initializeBooleanMap() {
        this.booleanMap.put("On", new HashMap<>());
        this.booleanMap.put("OnTable", new HashMap<>());
        this.booleanMap.put("Fi", new HashMap<>());
        this.booleanMap.put("Fr", new HashMap<>());

        for (int b = 0; b < nbBlocks; b++) {
            //pour tous les blocks on vérifie qu'ils sont fixés
            this.booleanMap.get("Fi").put("Fi" + b, new BooleanVariable("Fi" + b));

            //on vérifie que le block est sur un autre block
            for (int bprime = 0; bprime < nbBlocks; bprime++) {
                //on vérifie que les deux blocks sont différents
                if (b != bprime) {
                    this.booleanMap.get("On").put("On" + b + "_" + bprime, new BooleanVariable("On" + b + "_" + bprime));
                }
            }
            //on vérifie que le block est sur une pile
            for (int p = -nbPiles; p < nbPiles; p++) {
                this.booleanMap.get("OnTable").put("OnTable" + b + "_" + p, new BooleanVariable("OnTable" + b + "_" + p));
            }
        }

        for (int p = -nbPiles; p < nbPiles; p++) {
                this.booleanMap.get("Fr").put("Fr"+ p, new BooleanVariable("Fr"+ p));
        }
    }

    public Set<BooleanVariable> takeTransaction(List<List<Integer>> transactions) {
        Set<BooleanVariable> items = new HashSet<>();
        for (int i = 0; i < transactions.size(); i++) {
            List<Integer> transaction = transactions.get(i);
            if (transaction.isEmpty()){
                items.add(this.booleanMap.get("Fr").get("Fr"+i));
            }
            else {
                int b = transaction.get(0);
                transaction.remove(0);
                items.add(this.booleanMap.get("OnTable").get("OnTable" + b + "_" + i));
                while (!transaction.isEmpty()) {
                    int bprime = transaction.get(0);
                    transaction.remove(0);
                    items.add(this.booleanMap.get("On").get("On" + b + "_" + bprime));
                    items.add(this.booleanMap.get("Fi").get("Fi" + b));
                    b = bprime;
                }
            }
        }
        return items;
    }

    public Map<String, Map<String, BooleanVariable>> getBooleanMap() {
        return booleanMap;
    }

    public Set<BooleanVariable> getBoolVariables() {
        Set<BooleanVariable> onVariables = new HashSet<>();
        onVariables.addAll(this.booleanMap.get("On").values());
        onVariables.addAll(this.booleanMap.get("OnTable").values());
        onVariables.addAll(this.booleanMap.get("Fi").values());
        onVariables.addAll(this.booleanMap.get("Fr").values());
        return onVariables;
    }



}
