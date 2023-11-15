package blocksworld;

import java.util.HashSet;
import java.util.Set;

import modelling.BooleanVariable;
import modelling.Variable;


public class BWVariable {
    protected int nbBlocks; // nombre de blocs
    protected int nbPiles; // nombre de piles
    protected Set<Variable> onb = new HashSet<>(); // ensemble des blocs
    protected Set<Variable> fixedb = new HashSet<>(); // ensemble des blocs
    protected Set<Variable> freep = new HashSet<>(); // ensemble des blocs


    public BWVariable(int nbBlocks, int pile) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = pile;
    }



    public Set<Variable> getOnb() {
        return onb;
    }

    public Set<Variable> getFixedb() {
        return fixedb;
    }

    public Set<Variable> getFreep() {
        return freep;
    }

    public Set<Variable> getAllVars() {
        Set<Variable> allVars = new HashSet<>();
        OnB();
        FixedB();
        FreeP(); 
        allVars.addAll(onb);
        allVars.addAll(fixedb);
        allVars.addAll(freep);
        return allVars;
    }

    public Set<Variable> OnB(){
        Set<Variable> onb = new HashSet<>();
        for (int i = 0; i < nbBlocks; i++) {
            Variable var = new Variable("On"+i, new HashSet<>());
            onb.add(var);
        }   
        return onb;
    }

    public Set<Variable> FixedB(){
        Set<Variable> fixedb = new HashSet<>();
        for (int i = 0; i < nbBlocks; i++) {
            Variable var = new BooleanVariable("Fi"+i);
            fixedb.add(var);
        }   
        return fixedb;
    }

    public Set<Variable> FreeP(){
        Set<Variable> freep = new HashSet<>();
        for (int i = 0; i < nbPiles; i++) {
            Variable var = new BooleanVariable("Fr"+i);
            freep.add(var);
        }   
        return freep;
    }

    // public void setVariables(){
    //     //#region Définition des domaines    
    
    //     Set<Object> onDomain = new HashSet<>(); // domaine des valeurs des blocs
    //     for (int i = 0; i < nbBlocks; i++) {
    //         onDomain.add(i);
    //     }
    //     for (int i = 0; i < nbPiles; i++) {
    //         onDomain.add(-i-1);
    //     }
    //     //#endregion


    //     //#region Définition des variables

    //     // On instancie les variables de type on_blocs
    //     for (int i = 0; i < nbBlocks; i++) {
    //         Variable var = new Variable("On"+i, onDomain);
    //         onb.add(var);
    //     }

    //     // On instancie les variables de type fixed_blocs
    //     for (int i = 0; i < nbBlocks; i++) {
    //         Variable var = new BooleanVariable("Fi"+i);
    //         fixedb.add(var);
    //     }

    //     // On instancie les variables de type free_piles
    //     for (int i = 0; i < nbPiles; i++) {
    //         Variable var = new BooleanVariable("Fr"+i);
    //         freep.add(var);
    //     }

    //     // #endregion


    // }
    
    //get index
    public int getIndex(Variable var) {
        String name = var.getName();
        int index = Integer.parseInt(name.substring(2));
        return index;
    }

        @Override
        public String toString() {
            return "BWVariable\nfixedb=\n" + fixedb + "\nfreep=" + freep + "\nnbBlocks=" + nbBlocks + "\nnbPiles="
                    + nbPiles + "\nonb=" + onb + "]";
        }

}