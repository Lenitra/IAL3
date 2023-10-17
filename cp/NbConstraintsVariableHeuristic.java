package cp;

import java.util.Map;
import java.util.Set;

import modelling.Constraint;
import modelling.Variable;

public class NbConstraintsVariableHeuristic implements VariableHeuristic{
    // constructeur prend en argument : un ensemble de contraintes, un booléen

    /**
     * ensemble des variables
     */
    protected Set<Constraint> constraints;

    /**
     * un booleen indique si on préfère les variables apparaissant dans le plus de contraintes
     * ( true) ou dans le moins de contraintes ( false)
     */
    protected boolean bool;

    // constructeur
    public NbConstraintsVariableHeuristic(Set<Constraint> constraints, boolean bool) {
        this.constraints = constraints;
        this.bool = bool;
    }

    @Override
    public Variable best(Set<Variable> variables, Map<Variable, Set<Object>> domaines) {
        Variable best = null; // variable à retourner
        int nb = 0; // nombre de contraintes de la variable à retourner
        for (Variable var : variables) { // pour chaque variable
            int nbVar = 0; // nombre de contraintes de la variable var
            for (Constraint constraint : constraints) { // pour chaque contrainte
                if (constraint.getScope().contains(var)) { // si la contrainte contient la variable var
                    nbVar++; // on incrémente le nombre de contraintes de la variable var
                }
            }
            if (best == null) { // si la variable à retourner n'a pas encore été définie
                best = var; // on définit la variable à retourner
                nb = nbVar; // on définit le nombre de contraintes de la variable à retourner
            } else { // si la variable à retourner a déjà été définie
                if (bool) { // si bool est vrai
                    if (nbVar > nb) {   // si le nombre de contraintes de la variable var est supérieur au nombre de contraintes de la variable à retourner
                        best = var; // on définit la variable à retourner
                        nb = nbVar; // on définit le nombre de contraintes de la variable à retourner
                    }
                } else { // si bool est faux
                    if (nbVar < nb) {  // si le nombre de contraintes de la variable var est inférieur au nombre de contraintes de la variable à retourner
                        best = var; // on définit la variable à retourner
                        nb = nbVar; // on définit le nombre de contraintes de la variable à retourner
                    }
                }
            }
        }
        return best; // on retourne la variable à retourner.
    }

}
