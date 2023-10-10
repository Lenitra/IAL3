package cp;

import modelling.Constraint;
import modelling.Variable;

import java.util.HashMap;
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








    public boolean revise(Variable v1, Set<Object> d1, Variable v2, Set<Object> d2) {
        boolean del = false;

        // Créez une copie de d1 pour itérer et supprimer les valeurs
        Set<Object> d1Tmp = new HashSet<>(d1);

        for (Object value1 : d1Tmp) {
            boolean viable = false;

            for (Object value2 : d2) {
                boolean toutSatisfait = true;

                // Vérifiez si les contraintes entre v1 et v2 sont satisfaites
                for (Constraint constraint : constraints) {
                    if (constraint.getScope().contains(v1) && constraint.getScope().contains(v2)) {
                        Map<Variable, Object> assignment = new HashMap<>();
                        assignment.put(v1, value1);
                        assignment.put(v2, value2);

                        if (!constraint.isSatisfiedBy(assignment)) {
                            toutSatisfait = false;
                            break;
                        }
                    }
                }

                if (toutSatisfait) {
                    viable = true;
                    break;
                }
            }

            if (!viable) {
                // Supprimer value1 de d1
                d1.remove(value1);
                del = true;
            }
        }

        return del;
    }


    

    public boolean ac1(Map<Variable, Set<Object>> domains) {
        if (!enforceNodeConsistency(domains)) {
            return false;
        }

        boolean change = false;

        do {
            change = false;
    
            // Pour chaque paire de variables (v1, v2) dans le domaine
            for (Variable v1 : domains.keySet()) {
                for (Variable v2 : domains.keySet()) {
                    if (v1 != v2) {
                        if (revise(v1, domains.get(v1), v2, domains.get(v2))) {
                            change = true;
                        }
                    }
                }
            }
        } while (change);
    

        for (Variable var : domains.keySet()) {
            if (domains.get(var).isEmpty()) {
                return false;
            }
        }

        return true;
    }
    


}