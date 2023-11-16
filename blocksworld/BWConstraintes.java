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
        this.block = block;
        this.pile = pile;
        this.constraints = allConstraints();
    }

    public Set<Constraint> allConstraints() {
        Set<Constraint> resultat = new HashSet<>(); // ensemble des contraintes
        BWVariable variables = new BWVariable(this.nbBlocks, this.nbPiles); // On créé les variables
        Set<Variable> variablesOn = variables.getOnb();
        Set<Variable> variablesFixed = variables.getFixedb();
        Set<Variable> variablesFree = variables.getFreep();

        for(Variable variable1 : variablesOn){ // On loop sur les variables de de blocks On
            int num1 = Integer.parseInt(variable1.getName().substring(2)); // on récupère le numéro du bloc

            // Contrainte de type On (Un bloc ne peut pas être sur lui même)
            for(Variable variable2 : variablesOn){
                int num2 = Integer.parseInt(variable2.getName().substring(2)); // on récupère le numéro du bloc
                if(num1 == num2) continue; // si les deux blocs sont les mêmes on continue
                
                resultat.add(new DifferenceConstraint(variable1, variable2)); // Les deux variables ne peuvent pas avoir la même valeur
                
            }
            
            // Contrainte de type Fixed
            for(Variable variable2 : variablesFixed){
                int num2 = Integer.parseInt(variable2.getName().substring(2)); // on récupère le numéro du bloc
                if(num1 == num2) continue; // si les deux blocs sont les mêmes on continue
                // si on variable1 est sur variable2 alors variable2 est fixed
                resultat.add(new Implication(variable1, Set.of(num2), variable2, Set.of(true)));
            }


            // Contrainte de type free
            for(Variable variable2 : variablesFree){
                int num2 = Integer.parseInt(variable2.getName().substring(2)); // on récupère le numéro de la pile
                // si On variable1 == numpile alors Fr variable3 = false
                resultat.add(new Implication(variable1, Set.of(num2), variable2, Set.of(false))); 
            }
        }
        
        return resultat; // on retourne l’ensemble des contraintes
    }

    public Set<Constraint> getConstraints() {
        return constraints;
    }

    public Set<Variable> getVariables() {
        return variables;
    }    
    
    @Override
    public String toString() {
      return "{" +
          "constraints='" + getConstraints() + "'" +
          "}";
    }
}