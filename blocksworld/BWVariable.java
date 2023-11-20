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
    protected Set<BooleanVariable> fixedbV = new HashSet<>(); // ensemble des blocs
    protected Set<BooleanVariable> freepV = new HashSet<>(); // ensemble des blocs

    public BWVariable(int nbBlocks, int pile) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = pile;
        setAllVars();
    }

    // SET ALL VARIABLES - appelé dans le constructeur
    public void setAllVars() {
        setOnbV();
        setFixedbV();
        setFreepV(); 
        var.addAll(onbV);
        var.addAll(fixedbV);
        var.addAll(freepV);
    }



    // GETTERS
    public Set<Variable> getOnb() {
        return onbV;
    }

    public Set<BooleanVariable> getFixedb() {
        return fixedbV;
    }

    public Set<BooleanVariable> getFreep() {
        return freepV;
    }
        
    public Set<Variable> getAllVars() {
        return var;
    }


    // SETTERS
    public void setOnbV() {
        Set<Object> onDomain = new HashSet<>();
        for (int i = 0; i < nbBlocks; i++) {
            onDomain.add(i);
        }
        for (int i = -nbPiles ; i <= -1; i++) {
            onDomain.add(i);
        }

        for (int i = 0; i < nbBlocks; i++) {
            Variable var = new Variable("On"+i, onDomain);
            onbV.add(var);
        }
    }

    public void setFixedbV() {
        for (int i = 0; i < nbBlocks; i++) {
            BooleanVariable fixedBool = new BooleanVariable("Fi" + i);
            fixedbV.add(fixedBool);
        }
    }

    public void setFreepV() {
        for (int i = -nbPiles; i < 0; i++) { 
            BooleanVariable freeBool = new BooleanVariable("Fr" + i);
            freepV.add(freeBool);
        }
    }



    // Récupère l'index d'une variable
    public int getIndex(Variable var) {
        String name = var.getName();
        int index = Integer.parseInt(name.substring(2));
        return index;
    }


    @Override
    public String toString() {
        return "{ nbBlocks=" + nbBlocks + ", pile=" + nbBlocks + ", variables=" + var + '}';
    }


}