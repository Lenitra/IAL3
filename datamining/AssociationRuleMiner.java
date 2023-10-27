package datamining;

import java.util.Set;

public interface AssociationRuleMiner {
    
    //méthode pour extraire les règles d'association
    public BooleanDatabase getDatabase();

    //méthode abstraite pour extraire les règles d'association
    public Set<AssociationRule> extract(float frequency, float confidence);
}
