package blocksworld;

import java.util.*;

import modelling.*;
import planning.*;

public class BWActions2 extends BlocksWorld {

    private int numBlocks;
    private int numPiles;
    private Set<Action> actions = new HashSet<Action>();

    public BWActions2(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
        this.numBlocks = nbBlocks;
        this.numPiles = nbPiles;
        GenerateActions();
    }

    private void GenerateActions() {
        
        for (int block = 0; block < numBlocks; block++) {
            // BLOCK VERS BLOCK
            for(int sourceblock = 0; sourceblock < numBlocks; sourceblock++) {
                for(int destiblock = 0; destiblock < numBlocks; destiblock++) {
                    if (block != sourceblock && block != destiblock && sourceblock != destiblock) {
                        BlockOnBlock(block, sourceblock, destiblock);
                    }
                }
            }
    
            // BLOCK VERS PILE
            for(int sourceblock = 0; sourceblock < numBlocks; sourceblock++) {
                for(int destipile = -1; destipile >= -numPiles; destipile--) {
                    if (block != sourceblock) {
                        BlockOnPile(block, sourceblock, destipile);
                    }
                }
            }

            // BLOC SUR PILE VERS BLOC
            for(int sourcePile = -1; sourcePile >= -numPiles; sourcePile--) {
                for(int destiblock = 0; destiblock < numBlocks; destiblock++) {
                    if (block != destiblock) {
                        BlockPileOnBlock(block, sourcePile, destiblock);
                    }
                }
            }

            // BLOC SUR PILE VERS PILE
        //     for(int sourcePile = 0; sourcePile < numBlocks; sourcePile++) {
        //         for(int destiblock = 0; destiblock < numPiles; destiblock++) {
        //             if (block != sourcePile || block != destiblock || sourcePile != destiblock) {
        //                 BlockPileOnPile(block, sourcePile, destiblock);
        //             }
        //         }
        //     }
        }
    }
    
    public void BlockOnBlock(int block, int sourceblock, int destiblock){
        Map<Variable, Object> precondition = new HashMap<>();
        Variable varon = new Variable("On" + block, Set.of(sourceblock));
        
        //il faut que le bloc soit sur un autre bloc
        precondition.put(varon, sourceblock);
        //il faut que le bloc ne soit pas fixé
        precondition.put(new BooleanVariable("Fi"+block), false);
        //il faut que le bloc de base soit fixé
        precondition.put(new BooleanVariable("Fi"+sourceblock), true);
        //il faut que le bloc d'arrivée soit pas fixé
        precondition.put(new BooleanVariable("Fi"+destiblock), false);

        // System.out.println("precon("+precondition.toString()+")");

        Map<Variable, Object> effect = new HashMap<>();
        //le bloc est sur le bloc d'arrivée
        effect.put(varon, destiblock);
        //le bloc n'est pas fixé
        effect.put(new BooleanVariable("Fi"+block), false);
        //le bloc d'arrivée est fixé
        effect.put(new BooleanVariable("Fi"+destiblock), true);
        //le bloc de base n'est plus occupé
        effect.put(new BooleanVariable("Fi"+sourceblock), false);

        System.out.println("BlockOnBlock("+block+","+sourceblock+","+destiblock+")");

        Action action = new BasicAction(precondition, effect, 1);
        actions.add(action);
    }

    public void BlockOnPile(int block, int sourceblock, int destiPile){
        Variable varon = new Variable("On" + block, Set.of(sourceblock));
        //précondition pour que l'action se produise 
        Map<Variable, Object> precondition = new HashMap<>();
        //il faut que le bloc soit sur un autre bloc
        precondition.put(varon,sourceblock);
        //il faut que le bloc ne soit pas fixé
        precondition.put(new BooleanVariable("Fi"+block), false);
        //il faut que le bloc de base soit fixe
        precondition.put(new BooleanVariable("Fi"+sourceblock), true);
        //il faut que la pile d'arrivée soit libre
        precondition.put(new BooleanVariable("Fr"+ -destiPile), true);

        //effet de l'action
        Map<Variable, Object> effect = new HashMap<>();

        //le bloc est sur la pile d'arrivée
        effect.put(varon, destiPile);
        //le bloc n'est pas fixé
        effect.put(new BooleanVariable("Fi"+block), false);
        //la pile d'arrivée n'est plus libre
        effect.put(new BooleanVariable("Fr"+ -destiPile), false);
        //le bloc de base n'est plus occupé
        effect.put(new BooleanVariable("Fi"+sourceblock), false);

        System.err.println("BlockOnPile("+block+","+sourceblock+","+destiPile+")");

        Action action = new BasicAction(precondition, effect, 1);
        actions.add(action);
    }

    public void BlockPileOnBlock(int block, int sourcePile, int destiblock){
        Variable varon = new Variable("On" + block, Set.of(sourcePile));
        //précondition pour que l'action se produise
        Map<Variable, Object> precondition = new HashMap<>();

        //il faut que le bloc soit sur une pile
        precondition.put(varon,sourcePile);

        //il faut que le bloc ne soit pas fixé
        precondition.put(new BooleanVariable("Fi"+block), false);
        //il faut que le bloc d'arrive ne soit pas fixée
        precondition.put(new BooleanVariable("Fi"+destiblock), false);
        //il faut que la pile de base ne soit pas libre
        precondition.put(new BooleanVariable("Fr"+ -sourcePile), false);

        //effet de l'action
        Map<Variable, Object> effect = new HashMap<>();
        //le bloc est sur le bloc d'arrivée
        effect.put(varon, destiblock);
        //le bloc n'est pas fixé
        effect.put(new BooleanVariable("Fi"+block), false);
        //le bloc d'arrivée est fixé
        effect.put(new BooleanVariable("Fi"+destiblock), true);
        //la pile de base est libre
        effect.put(new BooleanVariable("Fr"+ -sourcePile), true);
        
        // System.err.println("BlockPileOnBlock("+block+","+sourcePile+","+ destiblock+")");

        Action action = new BasicAction(precondition, effect, 1);
        actions.add(action);
    }


    public void BlockPileOnPile(int block, int sourceblock, int destiblock){
        Map<Variable, Object> precondition = new HashMap<>();
        Variable varon = new Variable("On" + block, Set.of(sourceblock));

        precondition.put(varon,sourceblock);
        precondition.put(new BooleanVariable("Fi"+block), false);
        precondition.put(new BooleanVariable("Fr"+sourceblock), true);
        precondition.put(new BooleanVariable("Fr"+destiblock), false);

        Map<Variable, Object> effect = new HashMap<>();
        effect.put(varon, destiblock);
        effect.put(new BooleanVariable("Fi"+block), false);
        effect.put(new BooleanVariable("Fr"+sourceblock), false);

        Action action = new BasicAction(precondition, effect, 1);
        actions.add(action);
    }

    public Set<Action> getActions() {
        return actions;
    }


}
