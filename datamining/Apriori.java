package datamining;

import java.util.Set;

public class Apriori extends AbstractItemsetMiner {
    
        public Apriori(BooleanDatabase database) {
            super(database);
        }
    
        @Override
        public Set<Itemset> extract(float frequency) {
            return null;
        }
    
}
