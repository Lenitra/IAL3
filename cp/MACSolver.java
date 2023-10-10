package cp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import modelling.Variable;
import modelling.Constraint;

public class MACSolver extends AbstractSolver {


    public MACSolver(Set<Variable> variables, Set<Constraint> constraints) {
        super(variables, constraints);
    }

    @Override
    public Map<Variable, Object> solve() {
        List<Variable> variables = new ArrayList<>(this.variables);
        Map<Variable, Set<Object>> domains = new HashMap<Variable, Set<Object>>();
        for (Variable var : this.variables) {
            domains.put(var, var.getDomain());
        }
        return MAC(new HashMap<Variable, Object>(), variables, domains);
    }


    /**
     * La méthode MAC consiste à appliquer l'algorithme de backtrack avec l'arc consistency,
     * en reduisant le nombre de noeuds à explorer avant de obtenir une solution.
     * 
     * @param assignment une affectation partielle
     * @param varList une liste de variables non instanciées
     * @param domains un ensemble de domaines
     * @return une solution etandant la solution partielle si elle existe, null s'il y en a pas (à modifier)
     * 
     */
    public Map<Variable, Object> MAC(Map<Variable, Object> assignment, List<Variable> varList, Map<Variable, Set<Object>> domains) {
        List<Variable> variables = new ArrayList<>(varList);
        ArcConsistency ac = new ArcConsistency(this.constraints);
        if (variables.isEmpty()) {
            return assignment;
        }
        else {
            if(!ac.ac1(domains)){
                return null;
            }
            Variable var = variables.get(0);
            variables.remove(0);
            for(Object value : var.getDomain()){
                Map<Variable, Object> N = assignment;
                N.put(var, value);
                if(isConsistent(N)){
                    Map<Variable, Object> R = MAC(N, variables, domains);
                    if(R != null){
                        return R;
                    }
                }
            }
            variables.add(var);
            return null;

        }
    }
    

    

}