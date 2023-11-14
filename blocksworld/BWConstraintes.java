package blocksworld;

import java.util.*;

import modelling.*;



public class BWConstraintes extends BlocksWorld{

    

    protected int block; // nombre de blocs
    protected int pile; // pile
    // ensemble des contraintes
    protected Set<Constraint> constraints = new HashSet<>();
    // ensemble des variables
    protected Set<Variable> variables = new HashSet<>();

    public BWConstraintes(int block, int pile) {
        super(block, pile);
        this.constraints = new HashSet<>();
        this.constraints = allConstraints();
    }

    public Set<Constraint> allConstraints() {
        Set<Constraint> resultat = new HashSet<>(); // ensemble des contraintes
        BWVariable variables = new BWVariable(this.nbBlocks, this.nbPiles); // On créé les variables
        Set<Variable> variablesOn = variables.getOnb();
        Set<Variable> variablesFixed = variables.getFixedb();
        Set<Variable> variablesFree = variables.getFreep();

        for(Variable variable1 : variablesOn){ // On loop sur les variables de de blocks On


            // Contrainte de type On (Un bloc ne peut pas être sur lui même)
            for(Variable variable2 : variablesOn){
                if(!variable1.equals(variable2)){
                    resultat.add(new DifferenceConstraint(variable1, variable2));
                }
            }


            // Contrainte de type fixed 
            for(Variable variable3 : variablesFixed){
                int i = Integer.parseInt(variable3.getName().substring(2)); // numéro du bloc
                // si variable1 est sur variable3 alors variable3 est fixé
                resultat.add(new Implication(variable1, Set.of(i), variable3, Set.of(true)));
            }

            // Contrainte de type free
            for(Variable variable4 : variablesFree){
                int i = Integer.parseInt(variable4.getName().substring(2)); // numéro du bloc
                resultat.add(new Implication(variable1, Set.of(i), variable4, Set.of(false)));
            }
        }
        
        return resultat; // on retourne l’ensemble des contraintes
    }

    // public void setDifferent() {
    //     Set<Variable> var = bwv.getOnb();
    //     for (Variable variable1 : var) {
    //         for (Variable variable2 : var) {
    //             if (!variable1.equals(variable2)) {
    //                 constraints.add(new DifferenceConstraint(variable1, variable2));
    //             }
    //         }
    //     }
    // }


    // public void setImplication() {
    //     Set<Variable> var = bwv.getOnb();
    //     Set<Variable> var2 = bwv.getFixedb();
    //     Set<Variable> var3 = bwv.getFreep();
    //     for (Variable variable1 : var) {
    //         for (Variable variable2 : var2) {
    //             // On recupere le i dans le nom de la variable de la forme Variable("fixed_" + i);
    //             int i = Integer.parseInt(variable2.getName().substring(6));
    //             constraints.add(new Implication(variable1, Set.of(i), variable2, Set.of(true)));
    //         }
    //         for (Variable variable3 : var3) {
    //             // On recupere le i dans le nom de la variable de la forme Variable("free_" + i);
    //             int i = Integer.parseInt(variable3.getName().substring(5));
    //             constraints.add(new Implication(variable1, Set.of(i), variable3, Set.of(false)));
    //         }
    //     }
    // }

    public Set<Constraint> getConstraints() {
        return constraints;
    }

    @Override
    public String toString() {
        return "{" + "nbBlocks=" + nbBlocks + ", pile=" + nbPiles + ", constraints=" + getConstraints() +"}";
    }

    
    
    
}