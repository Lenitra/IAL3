package datamining;

import java.util.Set;

//Classe pour représenter un mineur d'itemsets
public interface ItemsetMiner {

    /**
     * Accesseur pour la base de données
     * @return la base de données
     */
    public BooleanDatabase getDatabase();

    /**
     * Extrait des ensembles d’items fréquents de la base de données
     * @param frequency  fréquence minimale
     * @return un ensembles d'items qui respectent la fréquence minimale
     */
    public Set<Itemset> extract(float frequency);
}
