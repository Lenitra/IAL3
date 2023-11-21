package blocksworld;

import java.util.HashSet;
import java.util.Set;

import modelling.BooleanVariable;
import modelling.Variable;

// Classe qui permet de générer les variables du blocksworld
public class BWVariable {
    protected int nbBlocks; // nombre de blocs
    protected int nbPiles; // nombre de piles
    protected Set<Variable> var = new HashSet<>(); // ensemble des variables.
    protected Set<Variable> onbV = new HashSet<>(); // ensemble des blocs
    protected Set<BooleanVariable> fixedbV = new HashSet<>(); // ensemble des blocs
    protected Set<BooleanVariable> freepV = new HashSet<>(); // ensemble des blocs

    public BWVariable(int nbBlocks, int pile) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = pile;
        setAllVars();
    }

    /**
     * Méthode qui permet de créer toutes les variables
     */
    public void setAllVars() {
        setOnbV();
        setFixedbV();
        setFreepV(); 
        var.addAll(onbV);
        var.addAll(fixedbV);
        var.addAll(freepV);
    }

    // GETTERS
    public Set<Variable> getOnb() {
        return onbV;
    }

    public Set<BooleanVariable> getFixedb() {
        return fixedbV;
    }

    public Set<BooleanVariable> getFreep() {
        return freepV;
    }
        
    public Set<Variable> getAllVars() {
        return var;
    }

    /**
     * Méthode qui permet de créer les variables de type "On"
     */
    public void setOnbV() {
        // On crée un set qui contient tous les blocs et toutes les piles
        Set<Object> onDomain = new HashSet<>();
        for (int i = 0; i < nbBlocks; i++) {
            onDomain.add(i);
        }
        for (int i = -nbPiles ; i <= -1; i++) {
            onDomain.add(i);
        }

        // On parcourt tous les blocs et on ajoute une variable de type "On" pour chaque bloc
        for (int i = 0; i < nbBlocks; i++) {
            Set<Object> onDomainCopy = new HashSet<>(onDomain);
            onDomainCopy.remove(i);
            Variable var = new Variable("On"+i, onDomainCopy);
            onbV.add(var);
        }
    }

    /**
     * Méthode qui permet de créer les variables de type "Fixed"
     */
    public void setFixedbV() {
        // On parcourt tous les blocs et on ajoute une variable de type "Fixed" pour chaque bloc
        for (int i = 0; i < nbBlocks; i++) {
            BooleanVariable fixedBool = new BooleanVariable("Fi" + i);
            fixedbV.add(fixedBool);
        }
    }

    /**
     * Méthode qui permet de créer les variables de type "Free"
     */
    public void setFreepV() {
        // On parcourt toutes les piles et on ajoute une variable de type "Free" pour chaque pile
        for (int i = -nbPiles; i < 0; i++) { 
            BooleanVariable freeBool = new BooleanVariable("Fr" + i);
            freepV.add(freeBool);
        }
    }

    // Récupère l'index d'une variable
    public int getIndex(Variable var) {
        String name = var.getName();
        int index = Integer.parseInt(name.substring(2));
        return index;
    }

    public Object getPile() {
        return nbPiles;
    }

    @Override
    public String toString() {
        return "{ nbBlocks=" + nbBlocks + ", pile=" + nbBlocks + ", variables=" + var + '}';
    }

}