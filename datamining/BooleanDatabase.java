package datamining;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import modelling.BooleanVariable;

public class BooleanDatabase {
    
    private Set<BooleanVariable> items;
    private List<Set<BooleanVariable>> transactions;
    
    public BooleanDatabase(Set<BooleanVariable> items) {
        this.items = items;
        this.transactions = new ArrayList<Set<BooleanVariable>>();
    }
    
    public void add(Set<BooleanVariable> transaction) {
        this.transactions.add(transaction);
    }
    
    public Set<BooleanVariable> getItems() {
        return this.items;
    }
    
    public List<Set<BooleanVariable>> getTransactions() {
        return this.transactions;
    }
    
    
}