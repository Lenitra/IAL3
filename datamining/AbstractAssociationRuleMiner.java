package datamining;

import java.util.HashSet;
import java.util.Set;

import modelling.BooleanVariable;

public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {
    
    protected BooleanDatabase database;

    public AbstractAssociationRuleMiner(BooleanDatabase database) {
        this.database = database;
    }

    public BooleanDatabase getDatabase() {
        return this.database;
    }

    public abstract Set<AssociationRule> extract(float frequency, float confidence);

    public static float frequency(Set<BooleanVariable> itemset, Set<Itemset> itemsets) {
        for (Itemset itemset2 : itemsets) {
            if (itemset2.getItems().equals(itemset)) {
                return itemset2.getFrequency();
            }
        }
        throw new IllegalArgumentException("Itemset non trouvé dans les itemsets");
    }

public static float confidence(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, Set<Itemset> itemsets) {
    float supportPremise = frequency(premise, itemsets);
    
    Set<BooleanVariable> combinedItemset = new HashSet<>(premise);
    combinedItemset.addAll(conclusion);
    
    float supportCombined = frequency(combinedItemset, itemsets);
    
    if (supportPremise > 0) {
        return supportCombined / supportPremise;
    } else {
        return 0;
    }
}

}
