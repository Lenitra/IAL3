package cp;

import modelling.Constraint;
import modelling.Variable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ArcConsistency {

    private Set<Constraint> constraints;

    // le constructeur prend un ensemble de contraintes en argument et lance une IllegalArgumentException si les contraintes ne sont pas toutes unaires ou binaires.
    public ArcConsistency(Set<Constraint> constraints) {
        // check if all  constraints are unary or binary
        for (Constraint c : constraints) {
            if (c.getScope().size() > 2) {
                throw new IllegalArgumentException("All constraints must be unary or binary");
            }
        }
        this.constraints = constraints;
    }



    public boolean enforceNodeConsistency(Map<Variable, Set<Object>> domains) {
        
        Set<Object> tmp = new HashSet<Object>(); // on créé un set qui va contenir les valeurs qui ne satisfont pas les contraintes

        for (Variable var : domains.keySet()) {
            for (Object value : domains.get(var)) {
                for (Constraint constraint : constraints) {
                    if (constraint.getScope().size() == 1 && constraint.getScope().contains(var)) {
                        if (!constraint.isSatisfiedBy(Map.of(var, value))) {
                            tmp.add(value);
                        }
                    }
                }
            }
            
            for (Object obj : tmp) {
            	domains.get(var).remove(obj);
            }
            tmp = new HashSet<Object>();
        }
    
        for (Variable var : domains.keySet()) {
            if (domains.get(var).isEmpty()) {
                return false;
            }
        }

        return true;
    }








    // public Boolean revise(Variable x_i, Variable x_j) {
    //     // remove from the domain of x_i any value that does not satisfy the constraint between x_i and x_j
    //     // return true if the domain of x_i has changed, false otherwise
    //     boolean changed = false;
    //     for (Object value : x_i.getDomain()) {
    //         boolean satisfied = false;
    //         for (Object value2 : x_j.getDomain()) {
    //             satisfied = satisfied || isSatisfiedWith(x_i, value, x_j, value2);
    //         }
    //         if (!satisfied) {
    //             x_i.removeValueFromDomain(value);
    //             changed = true;
    //         }
    //     }
    //     return changed;
    // }

    // public Boolean AC1() {
    //     // for every binary constraint (x_i, x_j), remove from the domain of x_i any value that does not satisfy the constraint between x_i and x_j
    //     // return true if at least one domain has changed, false otherwise
    //     boolean changed = false;
    //     for (Constraint c : constraints) {
    //         if (c.getScope().size() == 2) {
    //             changed = changed || revise(c.getScope().get(0), c.getScope().get(1));
    //         }
    //     }
    //     return changed;
        
    // }

}
