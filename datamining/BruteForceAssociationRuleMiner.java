package datamining;

import java.util.Set;

import modelling.BooleanVariable;

public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner {


    public BruteForceAssociationRuleMiner(BooleanDatabase database) {
        super(database);
    }
    
    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> items) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allCandidatePremises'");
    }

    @Override
    public Set<AssociationRule> extract(float frequency, float confidence) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'extract'");
    }  
}
