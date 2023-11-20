package blocksworld;

import java.util.Set;

import modelling.BooleanVariable;
import modelling.Variable;

public class BlocksWorld {

    protected int nbBlocks; // nombre de blocs
    protected int nbPiles; // nombre de piles
    protected BWVariable bw; // ensemble des variables.

    // constructeur
    public BlocksWorld(int nbBlocks, int nbPiles) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
        this.bw = new BWVariable(nbBlocks, nbPiles);
    }

    public int getNbBlocks() {
        return this.nbBlocks;
    }

    public int getNbPiles() {
        return this.nbPiles;
    }

    public Set<Variable> getOnbV() {
        return bw.getOnb();
    }

    public Set<BooleanVariable> getFixedb() {
        return bw.getFixedb();
    }

    public Set<BooleanVariable> getFreep() {
        return bw.getFreep();
    }

    public Set<Variable> getVariables() {
        return bw.getAllVars();
    }

    public BWVariable getBWVariable() {
        return this.bw;
    }
    
}
