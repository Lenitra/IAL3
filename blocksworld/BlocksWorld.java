package blocksworld;

import java.util.Set;

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

    public Set<Variable> getOnb() {
        return bw.getOnb();
    }

    public Set<Variable> getFixedb() {
        return bw.getFixedb();
    }

    public Set<Variable> getFreep() {
        return bw.getFreep();
    }

    public Set<Variable> getVariables() {
        return bw.getAllVars();
    }

    public BWVariable getBWVariable() {
        return this.bw;
    }
    
}
