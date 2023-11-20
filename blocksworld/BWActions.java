

import java.util.*;

import modelling.*;
import planning.*;

public class BWActions extends BlocksWorld {

    private int numBlocks;  
    private int numPiles;
    private Set<Action> actions = new HashSet<Action>();

    public BWActions(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
        this.numBlocks = nbBlocks;
        this.numPiles = nbPiles;
        GenerateActions();
    }

    private void GenerateActions() {
        
        for (int block = 0; block < numBlocks; block++) {
            for(int sourceblock = 0; sourceblock < numBlocks; sourceblock++) {
                // BLOCK VERS BLOCK
                for(int destiblock = 0; destiblock < numBlocks; destiblock++) {
                    if (block != sourceblock && block != destiblock && sourceblock != destiblock) {
                        BlockToBlock(block, sourceblock, destiblock);
                    }
                }
    
                // BLOCK VERS PILE
                for(int destiPile = -1; destiPile >= -numPiles; destiPile--) {
                    if (block != sourceblock) {
                        BlockToPile(block, sourceblock, destiPile);
                    }
                }
            }

            for(int sourcePile = -1; sourcePile >= -numPiles; sourcePile--) {
                // BLOC SUR PILE VERS BLOC
                for(int destiblock = 0; destiblock < numBlocks; destiblock++) {
                    if (block != destiblock) {
                        BlockPileToBlock(block, sourcePile, destiblock);
                    }
                }

                // BLOC SUR PILE VERS PILE
                for(int destiPile = -1; destiPile >= -numPiles; destiPile--) {
                    if (sourcePile != destiPile) {
                        BlockPileToPile(block, sourcePile, destiPile);
                    }
                }
            }
        }
    }
    
    public void BlockToBlock(int block, int sourceblock, int destiblock){
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

        //effet de l'action
        Map<Variable, Object> effect = new HashMap<>();
        //le bloc est sur le bloc d'arrivée
        effect.put(varon, destiblock);
        //le bloc n'est pas fixé
        effect.put(new BooleanVariable("Fi"+block), false);
        //le bloc d'arrivée est fixé
        effect.put(new BooleanVariable("Fi"+destiblock), true);
        //le bloc de base n'est plus occupé
        effect.put(new BooleanVariable("Fi"+sourceblock), false);

        // System.out.println("BlockToBlock("+block+","+sourceblock+","+destiblock+")");

        Action action = new BasicAction(precondition, effect, 1);
        actions.add(action);
    }

    public void BlockToPile(int block, int sourceblock, int destiPile){
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

        // System.err.println("BlockToPile("+block+","+sourceblock+","+destiPile+")");

        Action action = new BasicAction(precondition, effect, 1);
        actions.add(action);
    }

    public void BlockPileToBlock(int block, int sourcePile, int destiblock){
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
        
        // System.err.println("BlockPileToBlock("+block+","+sourcePile+","+ destiblock+")");

        Action action = new BasicAction(precondition, effect, 1);
        actions.add(action);
    }


    public void BlockPileToPile(int block, int sourcePile, int destiPile){
        Variable varon = new Variable("On" + block, Set.of(sourcePile));
        //précondition pour que l'action se produise
        Map<Variable, Object> precondition = new HashMap<>();
        //il faut que le bloc soit sur une pile
        precondition.put(varon,sourcePile);
        //il faut que le bloc ne soit pas fixé
        precondition.put(new BooleanVariable("Fi"+block), false);
        //il faut que la pile de base ne soit pas libre
        precondition.put(new BooleanVariable("Fr"+ -sourcePile), false);
        //il faut que la pile d'arrivée soit libre
        precondition.put(new BooleanVariable("Fr"+ -destiPile), true);

        //effet de l'action
        Map<Variable, Object> effect = new HashMap<>();
        //le bloc est sur la pile d'arrivée
        effect.put(varon, destiPile);
        //la pile d'arrivée n'est plus libre
        effect.put(new BooleanVariable("Fr"+ -sourcePile), true);
        //la pile de base est libre
        effect.put(new BooleanVariable("Fr"+ -destiPile), false);

        // System.err.println("BlockPileToPile("+block+","+sourcePile+","+ destiPile+")");

        Action action = new BasicAction(precondition, effect, 1);
        actions.add(action);
    }

    public Set<Action> getActions() {
        return actions;
    }


}
