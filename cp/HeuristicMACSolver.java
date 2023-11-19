package cp;

import java.util.*;
import modelling.*;

/**
 * Classe qui resout le probleme CSP au sens heuristique
 */
public class HeuristicMACSolver extends AbstractSolver {

    /**
    * Heuristique de variable
    *
    */
    protected VariableHeuristic varHeuristic;
    
    /**
     * Heuristique de valeur
     *
     */
    protected ValueHeuristic valHeuristic;

    /**
     * Constructeur de la classe HeuristicMACSolver
     * @param variables ensemble des variables
     * @param constraints ensemble des contraintes
     * @param varHeuristic heuristique de variable
     * @param valHeuristic heuristique de valeur
     *
     */
    public HeuristicMACSolver(Set<Variable> variables, Set<Constraint> constraints, VariableHeuristic varHeuristic, ValueHeuristic valHeuristic) {
        super(variables, constraints);
        this.varHeuristic = varHeuristic;
        this.valHeuristic = valHeuristic;
    }

    @Override
    public Map<Variable, Object> solve() {
        Set<Variable> variables = new HashSet<>(this.variables); // on cree une liste de variables
        Map<Variable, Set<Object>> domains = new HashMap<Variable, Set<Object>>(); // on cree un dictionnaire de variables et de domaines
        for (Variable var : this.variables) { // pour chaque variable
            domains.put(var, var.getDomain()); // on ajoute la variable et son domaine au dictionnaire
        }
        return MAC(new HashMap<Variable, Object>(), variables, domains); // on retourne le resultat de MAC
    }

    /**
     * La methode retranche la recherche de solution pour les problèmes CSP
     * @param assignment affectation
     * @param varList liste des variables
     * @param domains ensemble des domaines
     * @return une solution au probleme CSP
     */
    public Map<Variable, Object> MAC(Map<Variable, Object> assignment, Set<Variable> varList, Map<Variable, Set<Object>> domains) {
        Set<Variable> variables = new HashSet<>(varList); // copie de la liste des variables
        ArcConsistency ac = new ArcConsistency(this.constraints); // création d'un objet de type ArcConsistency
        if (variables.isEmpty()) { // si la liste des variables est vide
            return assignment; // on retourne l'assignation
        }
        else {
            if(!ac.ac1(domains)){ // si l'arc-consistance n'est pas respectée
                return null; // on retourne null
            }
            
            Variable var = varHeuristic.best(variables, domains); // on récupère la meilleure variable selon l'heuristique
            variables.remove(var); // on enlève la variable de la liste des variables à traiter parce qu'on la traite maintenant
            
            for(Object value :domains.get(var)){ // pour chaque valeur de la variable
                Map<Variable, Object> N = assignment; // on crée une nouvelle assignation
                N.put(var, value); // on ajoute la valeur à l'assignation
                
                if(isConsistent(N)){ // si l'assignation est consistante
                    Map<Variable, Object> R = MAC(N, variables, domains); // on applique MAC sur l'assignation
                    if(R != null){
                        return R;
                    }
                }
            }
            variables.add(var); // on ajoute la variable à la liste des variables à traiter parce qu'on a fini de la traiter 
            return null; // on retourne null si on a pas trouvé de solution 

        }
    }

}