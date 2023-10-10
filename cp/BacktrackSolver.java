package cp;

import java.util.*;

import modelling.Constraint;
import modelling.Variable;


/**
 * Classe héritant de AbstractSolver qui analyse un solveur capable de revenir en arrière
 * en testant les ensembles des affectations partielles possibles.
 */
public class BacktrackSolver extends AbstractSolver {

    /**
     * Constructeur de la classe BacktrackSolver
     * 
     * @param variables ensemble de variables 
     * @param constraints ensemble de contraintes
     */
    public BacktrackSolver(Set<Variable> variables, Set<Constraint> constraints) {
        super(variables, constraints);
    }

    /**
     * Methode qui fait appel à la methode récursive backtrack et retourne une solution si elle existe
     */
    @Override
    public Map<Variable, Object> solve() {
        return backtrack(new HashMap<>(), new ArrayList<>(variables));
    }

    /**
     * Methode recursive qui satisfait des contraintes en fonction des variables et des contraintes du probleme
     * 
     * 
     * @param partialAssignment une affectation partielle
     * @param varList une liste de variables non instanciées
     * @return une solution etandant la solution partielle si elle existe, null s'il y en a pas
     * 
     * 
     */
    private Map<Variable, Object> backtrack(Map<Variable, Object> partialAssignment, List<Variable> varList) {
    	List<Variable>  variList = new ArrayList<>(varList);
        if (variList.isEmpty()) {
            return partialAssignment;
        }
        
        Variable var = variList.get(0);
        for (Object value : var.getDomain()) {
        	Map<Variable, Object> partialAssignment2 = new HashMap<Variable , Object>(partialAssignment);
            partialAssignment2.put(var, value);
            if (isConsistent(partialAssignment2)) {
                Map<Variable, Object> result = backtrack(partialAssignment2, variList.subList(1, variList.size()));
                if (result != null) {
                    return result;
                }
            }
           
        }
        variList.add(var);
        return null;
    }

}