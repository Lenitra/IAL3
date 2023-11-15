package blocksworld;

import java.util.HashSet;
import java.util.Set;

import modelling.BooleanVariable;
import modelling.Variable;


public class BWVariable {
    protected int nbBlocks; // nombre de blocs
    protected int nbPiles; // nombre de piles
    protected Set<Variable> var = new HashSet<>(); // ensemble des variables.
    protected Set<Variable> onbV = new HashSet<>(); // ensemble des blocs
    protected Set<Variable> fixedbV = new HashSet<>(); // ensemble des blocs
    protected Set<Variable> freepV = new HashSet<>(); // ensemble des blocs

    public BWVariable(int nbBlocks, int pile) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = pile;
        this.var = new HashSet<>();
    }


    public Set<Variable> setAllVars() {
        setOnbV();
        setFixedbV();
        setFreepV(); 
        var.addAll(onbV);
        var.addAll(fixedbV);
        var.addAll(freepV);
        return var;
    }

    // public Set<Variable> OnB(){
    //     Set<Variable> onb = new HashSet<>();
    //     for (int i = 0; i < nbBlocks; i++) {
    //         Variable var = new Variable("On"+i, new HashSet<>());
    //         onb.add(var);
    //     }   
    //     return onb;
    // }

    // public Set<Variable> FixedB(){
    //     Set<Variable> fixedb = new HashSet<>();
    //     for (int i = 0; i < nbBlocks; i++) {
    //         Variable var = new BooleanVariable("Fi"+i);
    //         fixedb.add(var);
    //     }   
    //     return fixedb;
    // }

    // public Set<Variable> FreeP(){
    //     Set<Variable> freep = new HashSet<>();
    //     for (int i = 0; i < nbPiles; i++) {
    //         Variable var = new BooleanVariable("Fr"+i);
    //         freep.add(var);
    //     }   
    //     return freep;
    // }
    
    public Set<Variable> getOnb() {
        return onbV;
    }

    public Set<Variable> getFixedb() {
        return fixedbV;
    }

    public Set<Variable> getFreep() {
        return freepV;
    }

    public void setOnbV() {
        Set<Object> onDomain = new HashSet<>();
        for (int i = 0; i < nbBlocks; i++) {
            onDomain.add(i);
        }
        for (int i = 0; i < nbPiles; i++) {
            onDomain.add(-i-1);
        }

        for (int i = 0; i < nbBlocks; i++) {
            Variable var = new Variable("on_"+i, onDomain);
            onbV.add(var);
        }
    }

    public void setFixedbV() {
        for (int i = 0; i < nbBlocks; i++) {
            Variable fixedBool = new BooleanVariable("fixed_" + i);
            fixedbV.add(fixedBool);
        }
    }

    public void setFreepV() {
        for (int i = -nbPiles; i < 0; i++) { 
            Variable freeBool = new BooleanVariable("free_" + i);
            freepV.add(freeBool);
        }
    }
    //get index
    public int getIndex(Variable var) {
        String name = var.getName();
        int index = Integer.parseInt(name.substring(2));
        return index;
    }

    @Override
    public String toString() {
        return "{ nbBlocks=" + nbBlocks + ", pile=" + nbBlocks + ", variables=" + var + '}';
    }

    //get variable
    public Set<Variable> getVars() {
        return var;
    }

    public void setNbBlocks(int nbBlocks) {
        this.nbBlocks = nbBlocks;
    }

    public void setPile(int pile) {
        this.nbPiles = pile;
    }

    public int getPile() {
        return nbPiles;
    }
}