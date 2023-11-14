package blocksworld;

import java.util.*;

import modelling.*;



public class BWConstraintes extends BlocksWorld{

    

    protected int nbBlocks; // nombre de blocs
    protected int pile; // pile
    // ensemble des contraintes
    protected Set<Constraint> constraints = new HashSet<>();
    // ensemble des variables
    protected Set<Variable> variables = new HashSet<>();

    public BWConstraintes(int block, int pile) {
        super(block, pile);
        this.constraints = new HashSet<>();
        setImplication();
        setDifferent();
        
    }

    public Set<Constraint> trouverUnNomDeMéthode() {
        Set<Constraint> resultat = new HashSet<>(); // ensemble des contraintes
        BWVariable variables = new BWVariable(this.nbBlocks, this.pile);
        Set<Variable> variablesOn = variables.getOnb();
        Set<Variable> variablesFixed = variables.getFixedb();
        Set<Variable> variablesFree = variables.getFreep();

        // Contrainte de type On
        for(Variable variable1 : variablesOn){
            for(Variable variable2 : variablesOn){
                if(!variable1.equals(variable2)){
                    resultat.add(new DifferenceConstraint(variable1, variable2));
                }
            }

            // Contrainte de type fixed
            for(Variable variable3 : variablesFixed){
                // On recupere le i dans le nom de la variable de la forme Variable("fixed_" + i);
                int i = Integer.parseInt(variable3.getName().substring(6));
                resultat.add(new Implication(variable1, Set.of(i), variable3, Set.of(true)));
            }
            // 
            // Contrainte de type free
            for(Variable variable4 : variablesFree){
                // On recupere le i dans le nom de la variable de la forme Variable("free_" + i);
                int i = Integer.parseInt(variable4.getName().substring(5));
                resultat.add(new Implication(variable1, Set.of(i), variable4, Set.of(false)));
            }
        }
        
        return resultat; // on retourne l’ensemble des contraintes
    }

    public void setDifferent() {
        Set<Variable> var = bwv.getOnb();
        for (Variable variable1 : var) {
            for (Variable variable2 : var) {
                if (!variable1.equals(variable2)) {
                    constraints.add(new DifferenceConstraint(variable1, variable2));
                }
            }
        }
    }

    /**
     * Methode instanciating all the constraints of the type Implication
     */
    public void setImplication() {
        Set<Variable> var = bwv.getOnb();
        Set<Variable> var2 = bwv.getFixedb();
        Set<Variable> var3 = bwv.getFreep();
        for (Variable variable1 : var) {
            for (Variable variable2 : var2) {
                // On recupere le i dans le nom de la variable de la forme Variable("fixed_" + i);
                int i = Integer.parseInt(variable2.getName().substring(6));
                constraints.add(new Implication(variable1, Set.of(i), variable2, Set.of(true)));
            }
            for (Variable variable3 : var3) {
                // On recupere le i dans le nom de la variable de la forme Variable("free_" + i);
                int i = Integer.parseInt(variable3.getName().substring(5));
                constraints.add(new Implication(variable1, Set.of(i), variable3, Set.of(false)));
            }
        }
    }

    public Set<Constraint> getConstraints() {
        return constraints;
    }

    @Override
    public String toString() {
        return "{" + "nbBlocks=" + nbBlocks + ", pile=" + nbPiles + ", constraints=" + getConstraints() +"}";
    }

    
    
    
}