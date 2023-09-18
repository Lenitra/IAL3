package blocksworld;

import java.util.HashMap;
import java.util.Map;

public class Blockworld{
    private int nbBloc;
    private int nbPile;

    public Blockworld(int nbBloc, int nbPile){
        this.nbBloc = nbBloc;
        this.nbPile = nbPile;
    }

    public int getNbBloc(){
        return this.nbBloc;
    }

    public int getNbPile(){
        return this.nbPile;
    }



    public static void main(String[] args) {
        Blockworld worldVariables = new Blockworld(1, 5);
        System.out.println("Nombre de blocs : " + worldVariables.getNbBloc());
        System.out.println("Nombre de piles : " + worldVariables.getNbPile());
    }
}