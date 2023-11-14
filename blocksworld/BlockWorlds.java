package blocksworld;

public class BlockWorlds {

    public static void main(String[] args) {
        //  On va avoir 4 blocks et 6 piles
        int nbBlocks = 2;
        int nbPiles = 1;
        BWVariable bw = new BWVariable(nbBlocks, nbPiles);
        System.out.println(bw.toString());




    }
    
}
