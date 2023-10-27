package datamining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import modelling.BooleanVariable;

//classe pour représenter une base de données booléenne
public class BooleanDatabase {

    private Set<BooleanVariable> items;
    private List<Set<BooleanVariable>> transactions;
    
    /**
     * Constructeur
     * @param items  ensemble d'items
     */
    public BooleanDatabase(Set<BooleanVariable> items) {
        this.items = items;
        //on initialise la liste des transactions
        this.transactions = new ArrayList<Set<BooleanVariable>>();
    }
    
    /**
     * Ajoute une transaction à la base de données
     * @param transaction   une transaction
     */
    public void add(Set<BooleanVariable> transaction) {
        this.transactions.add(transaction);
    }
    
    /**
     * Accesseur pour l'ensemble d'items
     * @return l'ensemble d'items
     */
    public Set<BooleanVariable> getItems() {
        return this.items;
    }
    
    /**
     * Accesseur pour la liste des transactions
     * @return la liste des transactions
     */
    public List<Set<BooleanVariable>> getTransactions() {
        return this.transactions;
    }
    
    
}