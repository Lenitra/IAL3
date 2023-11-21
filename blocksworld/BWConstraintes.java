package blocksworld;

import java.util.HashSet;
import java.util.Set;

import modelling.BooleanVariable;
import modelling.Constraint;
import modelling.DifferenceConstraint;
import modelling.Implication;
import modelling.Variable;

// Classe qui représente les contraintes du problème
public class BWConstraintes extends BlocksWorld{

    protected int nbBlocks; // nombre de blocs
    protected int nbPiles; // nombre de piles
    protected Set<Constraint> constraints = new HashSet<>(); // ensemble des contraintes
    protected Set<Variable> variables = new HashSet<>(); // ensemble des variables

    public BWConstraintes(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
        this.constraints = allConstraints();
    }

    /**
     * Méthode qui retourne l’ensemble des contraintes du problème
     * @return un set de contraintes
     */
    public Set<Constraint> allConstraints() {
        Set<Constraint> resultat = new HashSet<>(); // ensemble des contraintes
        BWVariable variables = new BWVariable(this.nbBlocks, this.nbPiles); // On créé les variables
        // On récupère les variables de type "On", "Fixed" et "Free"
        Set<Variable> variablesOn = variables.getOnb();
        Set<BooleanVariable> variablesFixed = variables.getFixedb();
        Set<BooleanVariable> variablesFree = variables.getFreep();

        // On parcourt les variables de type "On"
        for(Variable variable1 : variablesOn){ 
            // On récupère le numéro du bloc en retirant le "On" de la variable
            int num1 = Integer.parseInt(variable1.getName().substring(2)); 

            // Contrainte de type Difference
            // On parcourt les variables de type "On" et on ajoute une contrainte de type Difference
            for(Variable variable2 : variablesOn){
                int num2 = Integer.parseInt(variable2.getName().substring(2));
                if(num1 != num2){
                    resultat.add(new DifferenceConstraint(variable1, variable2));
                }
            }
            
            // Contrainte de type Fixed
            // On parcourt les variables de type "Fixed" et on ajoute une contrainte de type Implication
            for(Variable variable2 : variablesFixed){
                int num2 = Integer.parseInt(variable2.getName().substring(2));
                if(num1 != num2) {
                    resultat.add(new Implication(variable1, Set.of(num2), variable2, Set.of(true)));
                }
            }


            // Contrainte de type free
            // On parcourt les variables de type "Free" et on ajoute une contrainte de type Implication
            for(Variable variable2 : variablesFree){
                int num2 = Integer.parseInt(variable2.getName().substring(2)); 
                resultat.add(new Implication(variable1, Set.of(num2), variable2, Set.of(false))); 
            }
        }

        // on retourne l’ensemble des contraintes
        return resultat;
    }

    /**
     * Méthode qui retourne l’ensemble des contraintes du problème
     * @return un set de contraintes
     */
    public Set<Constraint> getConstraints() {
        return constraints;
    }

    /**
     * Méthode qui retourne l’ensemble des variables
     * @return un set de variables
     */
    public Set<Variable> getVariables() {
        return variables;
    }
    
    /**
     * Méthode qui affiche toutes les contraintes
     * @return un string de contraintes
     */
    @Override
    public String toString() {
      return "{" +
          "constraints='" + getConstraints() + "'" +
          "}";
    }
}