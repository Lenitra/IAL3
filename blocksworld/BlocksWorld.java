package blocksworld;

public class BlocksWorld {

    protected int nbBlocks; // nombre de blocs
    protected int nbPiles; // nombre de piles

    // constructeur
    public BlocksWorld(int nbBlocks, int nbPiles) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
    }


    public static void main(String[] args) {
        //  On va avoir 4 blocks et 6 piles
        int nbBlocks = 2;
        int nbPiles = 1;
        BWVariable bw = new BWVariable(nbBlocks, nbPiles);
        System.out.println(bw.toString());




    }
    
}
