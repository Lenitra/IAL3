package planning;

import java.util.Map;

import modelling.Variable;



//L'interface Action définit les méthodes nécessaires pour représenter une action dans la planification
public interface Action {
    /**
     * Verifie si l'action est applicable a l'etat donné
     * @param state L'etat sur lequel verifier l'applicabilite de l'action
     * @return true ou false en fonction de si l'action est applicable ou non
     */
    public boolean isApplicable(Map<Variable, Object> state);

    /**
     * Applique l'action a l'etat courant pour produire un nouvel etat
     * @param state L'etat courant sur lequel appliquer l'action
     * @return Le nouvel etat resultant de l'application de l'action
     */
    public Map<Variable, Object> successor(Map<Variable, Object> state);

    /**
     * Recupere le cout associe a l'action
     * @return Le coût de l'action
     */
    public int getCost();

}
