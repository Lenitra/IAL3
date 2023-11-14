package blocksworld;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import modelling.Variable;


public class BWVariable {
    protected int nbBlocks; // nombre de blocs
    protected int pile; // pile.
    protected Set<Variable> variables = new HashSet<>(); // ensemble des variables.
    protected Set<Variable> onb = new HashSet<>(); // ensemble des variables onb.
    protected Map<Variable, Integer> fixedb = new HashMap<>(); 
    protected Map<Variable, Integer> freep = new HashMap<>(); 

    public BWVariable(int nbBlocks, int pile) {
        this.nbBlocks = nbBlocks;
        this.pile = pile;
        }

    public Set<Variable> setOnb(){
        Set<Variable> resultat = new HashSet<>();
        for (int i = 0; i < this.nbBlocks; i++) {
            Variable var = new Variable("onb" + i, new HashSet<>());
            for (int j = 0; j < this.nbBlocks; j++) {
                if (i != j) {
                    var.getDomain().add(j);
                }
            }
            resultat.add(var);
        }
        return resultat;
    }

    public Map<Variable, Integer> setFixedb(){
        Map<Variable, Integer> resultat = new HashMap<>();
        for (int i = 0; i < this.nbBlocks; i++) {
            Variable var = new Variable("fixedb" + i, new HashSet<>());
            var.getDomain().add(true);
            var.getDomain().add(false);
            resultat.put(var, i);
        }
        return resultat;
    }

    public Map<Variable, Integer> setFreep(){
        Map<Variable, Integer> resultat = new HashMap<>();
        for (int i = 0; i < this.pile; i++) {
            Variable var = new Variable("freep" + i, new HashSet<>());
            for (int j = 0; j < this.nbBlocks; j++) {
                var.getDomain().add(j);
            }
            resultat.put(var, i);
        }
        return resultat;
    }
    public Set<Variable> getVariables() {
        return this.variables;
    }

    public Set<Variable> getOnb() {
        return this.onb;
    }



}
