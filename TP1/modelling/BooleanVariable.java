package modelling;
import java.util.HashSet;

// import planning.Variable;

// BooleanVariable hérite de Variable
public class BooleanVariable extends Variable {

    // constructeur
    public BooleanVariable(String name) {
        super(name, new HashSet<>() {{ add(true); add(false); }});
    }

}