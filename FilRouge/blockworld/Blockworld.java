package blockworld;

import java.util.HashMap;
import java.util.Map;

public class Blockworld{
    private int nbBloc;
    private int nbPile;
    private Map<Integer, Integer> map;

    public Blockworld(int nbBloc, int nbPile){
        this.nbBloc = nbBloc;
        this.nbPile = nbPile;
        this.map = createVar();
    }

    private Map<Integer, Integer> createVar() {
        Map<Integer, Integer> vars = new HashMap<>();
        for (int block = 0; block < nbBloc; block++) {
            vars.put(block, null);
        }
        for (int pile = 0; pile < nbPile; pile++) {
            vars.put(-(pile + 1), null);
        }
        return vars;
    }

    public Map<Integer, Integer> getVar(){
        return this.map;
    }


    public static void main(String[] args) {
        Blockworld worldVariables = new Blockworld(1, 3);
        Map<Integer, Integer> variables = worldVariables.getVar();
        System.out.println(variables);
    }
}