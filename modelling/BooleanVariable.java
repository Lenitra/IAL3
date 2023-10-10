package modelling;
import java.util.HashSet;

// Cette classe représente une variable booléenne dans un modèle
public class BooleanVariable extends Variable {

    /**
     * Constructeur de la classe BooleanVariable
     * @param name le nom de la variable booléenne
     */
    public BooleanVariable(String name) {
        //Appel du constructeur de la classe Variable avec un domaine de valeurs booléennes
        super(name, new HashSet<>() {{ add(true); add(false); }});
    }

}