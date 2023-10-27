package datamining;

import java.util.HashSet;
import java.util.Set;

import modelling.BooleanVariable;

//Classe abstraite pour les mineurs de règles d'association
public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner {
    
    protected BooleanDatabase database;

    /**
     * Constructeur
     * @param database  base de données booléenne
     */
    public AbstractAssociationRuleMiner(BooleanDatabase database) {
        this.database = database;
    }

    public BooleanDatabase getDatabase() {
        return this.database;
    }

    //méthode abstraite pour extraire les règles d'association
    public abstract Set<AssociationRule> extract(float frequency, float confidence);

    /**
     * calcule la fréquence d'un itemset dans une base de données booléenne
     * @param itemset   un itemset
     * @param itemsets  un ensemble d'itemsets
     * @return la fréquence de l'ensemble d'éléments dans l'ensemble d'itemsets
     * @throws IllegalArgumentException si l'itemset n'est pas dans l'ensemble d'itemsets
     */
    public static float frequency(Set<BooleanVariable> itemset, Set<Itemset> itemsets) {
        //on parcourt l'ensemble d'itemsets
        for (Itemset itemset2 : itemsets) {
            //si l'itemset est dans l'ensemble d'itemsets on retourne sa fréquence
            if (itemset2.getItems().equals(itemset)) {
                return itemset2.getFrequency();
            }
        }
        throw new IllegalArgumentException("Itemset non trouvé dans les itemsets");
    }

    /**
     * calcule la confiance d'une règle d'association 
     * @param premise       prémisse de la règle d'association
     * @param conclusion    conclusion de la règle d'association
     * @param itemsets      un ensemble d'itemsets
     * @return la confiance de la règle d'association de premisse et conclusion
     */
    public static float confidence(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, Set<Itemset> itemsets) {
        //on calcule la fréquence du prémisse
        float supportPremise = frequency(premise, itemsets);
        
        //on créé un ensemble contenant le prémisse et la conclusion
        Set<BooleanVariable> combinedItemset = new HashSet<>(premise);
        combinedItemset.addAll(conclusion);
        
        //on calcule la fréquence de l'ensemble créé au dessus
        float supportCombined = frequency(combinedItemset, itemsets);
        
        //si la fréquence du prémisse est supérieure à 0
        if (supportPremise > 0) {
            //on retourne la confiance de la règle d'association
            return supportCombined / supportPremise;
        } else {
            //sinon on retourne 0
            return 0;
        }
    }

}
