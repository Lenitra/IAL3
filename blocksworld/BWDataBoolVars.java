package blocksworld;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelling.BooleanVariable;
import modelling.Variable;

public class BWDataBoolVars extends BWVariable {
    
    protected int nbBlocks;
    protected int nbPiles;
    protected Set<BooleanVariable> allBoolVars;
    protected Set<BooleanVariable> onbb;
    protected Set<BooleanVariable> ontableb;
    protected Set<BooleanVariable> fixedb;
    protected Set<BooleanVariable> freep;

    public BWDataBoolVars(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
        this.allBoolVars = new HashSet<>();
        this.onbb = new HashSet<>();
        this.ontableb = new HashSet<>();
        this.fixedb = new HashSet<>();
        this.freep = new HashSet<>();
        setAllBoolVars();
    }


    public Set<BooleanVariable> getOnbb() {
        return onbb;
    }



    public void setAllBoolVars() {
        Set<BooleanVariable> resultat = new HashSet<>(); // ensemble des contraintes
        BWVariable variables = new BWVariable(this.nbBlocks, this.nbPiles); // On créé les variables
        Set<Variable> variablesOn = variables.getOnb();

        fixedb = variables.getFixedb();
        freep = variables.getFreep();

        // Contrainte de type Onb,b1
        for(Variable var1 : variablesOn){ // On loop sur les variables de de blocks On
            int num1 = Integer.parseInt(var1.getName().substring(2)); // on récupère le numéro du bloc
            resultat.add(new BooleanVariable("Fi" + num1));
            // Contrainte de type On (Un bloc ne peut pas être sur lui même)
            for(Variable var2 : variablesOn){
                int num2 = Integer.parseInt(var2.getName().substring(2)); // on récupère le numéro du bloc
                
                // Onb,b1
                onbb.add(new BooleanVariable("On" + num1 + "," + num2));
            }



            for(Variable pil1 : freep){
                int numPil1 = Integer.parseInt(pil1.getName().substring(2)); // on récupère le numéro de la pile
                // Variable on-tableb,p 
                ontableb.add(new BooleanVariable("on-table" + num1 + "," + numPil1));
            }
        }



        // set allBoolVars
        allBoolVars.addAll(onbb);
        allBoolVars.addAll(ontableb);
        allBoolVars.addAll(fixedb);
        allBoolVars.addAll(freep);
    }



    public Set<BooleanVariable> getBoolFromState(List<List<Integer>> state) {
        
        // On récupère le nombre de piles
        int nbPiles = state.size();
        // On récupère le nombre de blocs
        int nbBlocks = 0;
        for(List<Integer> pile : state){
            nbBlocks += pile.size();
        }

        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;

        setAllBoolVars();

        return allBoolVars;

    }




}
