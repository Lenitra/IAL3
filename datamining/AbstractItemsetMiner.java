package datamining;

import java.util.Comparator;
import java.util.Set;

import modelling.BooleanVariable;

public abstract class AbstractItemsetMiner implements ItemsetMiner{
    
    protected BooleanDatabase database;
    
    /**
     * Constructeur
     * @param database base de données booléenne
     */
    public AbstractItemsetMiner(BooleanDatabase database) {
        this.database = database;
    }
    
    /**
     * Accesseur pour la base de données
     * @return  la base de données
     */
    public BooleanDatabase getDatabase() {
        return this.database;
    }
    
    /**
     * calcule la fréquence d'un itemset dans une base de données booléenne
     * @param itemset   un ensemble d'éléments
     * @return la fréquence de l'ensemble d'éléments dans la base de données
     */
    public float frequency(Set<BooleanVariable> itemset){
        float count = 0;
        //on parcourt la base de données
        for (Set<BooleanVariable> transaction : this.database.getTransactions()) {
            //si la transaction contient l'itemset on incrémente le compteur
            if (transaction.containsAll(itemset)) {
                count++;
            }
        }
        //on retourne la fréquence de l'itemset
        return count / this.database.getTransactions().size();
    }

    //attribut statique qui permet de comparer les items
    public static final Comparator<BooleanVariable> COMPARATOR =
        (var1, var2) -> var1.getName().compareTo(var2.getName());
}
