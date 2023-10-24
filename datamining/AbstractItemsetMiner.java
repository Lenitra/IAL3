package datamining;

import java.util.Comparator;
import java.util.Set;

import modelling.BooleanVariable;

public abstract class AbstractItemsetMiner implements ItemsetMiner{
    
//     constructeur prenant en argument une base (de type BooleanDatabase), d’un
// accesseur pour cette base, et d’une méthode float frequency(Set<BooleanVariable>) retournant la
// fréquence d’un ensemble donné d’items dans la base
    protected BooleanDatabase database;
    
    public AbstractItemsetMiner(BooleanDatabase database) {
        this.database = database;
    }
    
    public BooleanDatabase getDatabase() {
        return this.database;
    }
    
    public float frequency(Set<BooleanVariable> itemset){
        int count = 0;
        for (Set<BooleanVariable> transaction : this.database.getTransactions()) {
            if (transaction.containsAll(itemset)) {
                count++;
            }
        }
        return (float) count / this.database.getTransactions().size();
    }

    public static final Comparator<BooleanVariable> COMPARATOR =
        (var1, var2) -> var1.getName().compareTo(var2.getName());
}
