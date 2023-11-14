package blocksworld;

import java.util.*;

import modelling.*;

/**
 * Classe Abstraite BlocksWorld
 */
public class BlocksWorld {

    protected int nbBlocks; // nombre de blocs
    protected int nbPiles; // pile.
    protected BWVariable bwv; // ensemble des variables.


    public BlocksWorld(int block, int pile) {
        this.nbBlocks = block;
        this.nbPiles = pile;
        this.bwv = new BWVariable(block, pile);
    }


    public String toString() {
        return "BlocksWorld{" +
                "nbBlocks=" + nbBlocks +
                ", nbPiles=" + nbPiles +
                ", bwv=" + bwv +
                '}';
    }
    
    public Set<Variable> getOnbV() {
        return bwv.getOnb();
    }

    public Set<Variable> getFixedbV() {
        return bwv.getFixedb();
    }

    public Set<Variable> getFreepV() {
        return bwv.getFreep();
    }

    public Set<Variable> getVariables() {
        return bwv.getAllVars();
    }



    public int getNbBlocks() {
        return this.nbBlocks;
    }

    public int getNbPiles() {
        return this.nbPiles;
    }




}