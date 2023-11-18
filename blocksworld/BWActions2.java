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
        
        // BLOCK VERS BLOCK
        for (int block = 0; block < numBlocks; block++) {
            for(int sourceblock = 0; sourceblock < numBlocks; sourceblock++) {
                for(int destiblock = 0; destiblock < numBlocks; destiblock++) {
                    if (block != sourceblock || block != destiblock || sourceblock != destiblock) {
                        BlockOnBlock(block, sourceblock, destiblock);
                    }
                }
            }
        }
    
        // BLOCK VERS PILE
        for (int block = 0; block < numBlocks; block++) {
            for(int sourceblock = 0; sourceblock < numBlocks; sourceblock++) {
                for(int destiblock = 0; destiblock < numPiles; destiblock++) {
                    if (block != sourceblock || block != destiblock || sourceblock != destiblock) {
                        BlockOnPile(block, sourceblock, destiblock);
                    }
                }
            }
        }

        // BLOC SUR PILE VERS BLOC
        for (int block = 0; block < numBlocks; block++) {
            for(int sourceblock = 0; sourceblock < numBlocks; sourceblock++) {
                for(int destiblock = 0; destiblock < numBlocks; destiblock++) {
                    if (block != sourceblock || block != destiblock || sourceblock != destiblock) {
                        BlockPileOnBlock(block, sourceblock, destiblock);
                    }
                }
            }
        }
        // BLOC SUR PILE VERS PILE
        for (int block = 0; block < numBlocks; block++) {
            for(int sourceblock = 0; sourceblock < numBlocks; sourceblock++) {
                for(int destiblock = 0; destiblock < numPiles; destiblock++) {
                    if (block != sourceblock || block != destiblock || sourceblock != destiblock) {
                        BlockPileOnPile(block, sourceblock, destiblock);
                    }
                }
            }
        }
    }
    

    public void BlockOnBlock(int block, int sourceblock, int destiblock){
        Map<Variable, Object> precondition = new HashMap<>();
        Variable varon = new Variable("On" + block, Set.of(sourceblock));

        precondition.put(varon, sourceblock);
        precondition.put(new BooleanVariable("Fi"+block), false);
        precondition.put(new BooleanVariable("Fr"+sourceblock), false);

        Map<Variable, Object> effect = new HashMap<>();
        effect.put(varon, destiblock);
        effect.put(new BooleanVariable("Fi"+block), false);
        effect.put(new BooleanVariable("Fi"+destiblock), true);
        effect.put(new BooleanVariable("Fr"+sourceblock), false);

        Action action = new BasicAction(precondition, effect, 1);
        actions.add(action);
    }

    public void BlockOnPile(int block, int sourceblock, int destiblock){
        Map<Variable, Object> precondition = new HashMap<>();
        Variable varon = new Variable("On" + block, Set.of(sourceblock));

        precondition.put(varon,sourceblock);
        precondition.put(new BooleanVariable("Fi"+block), false);
        precondition.put(new BooleanVariable("Fr"+sourceblock), false);

        Map<Variable, Object> effect = new HashMap<>();
        effect.put(varon, destiblock);
        effect.put(new BooleanVariable("Fi"+block), false);
        effect.put(new BooleanVariable("Fr"+sourceblock), true);

        Action action = new BasicAction(precondition, effect, 1);
        actions.add(action);
    }

    public void BlockPileOnBlock(int block, int sourceblock, int destiblock){
        Map<Variable, Object> precondition = new HashMap<>();  
        Variable varon = new Variable("On" + block, Set.of(sourceblock));

        precondition.put(varon,sourceblock);
        precondition.put(new BooleanVariable("Fi"+block), false);
        precondition.put(new BooleanVariable("Fr"+sourceblock), true);
        precondition.put(new BooleanVariable("Fr"+destiblock), false);

        Map<Variable, Object> effect = new HashMap<>();
        effect.put(varon, destiblock);
        effect.put(new BooleanVariable("Fi"+block), false);
        effect.put(new BooleanVariable("Fi"+destiblock), true);
        effect.put(new BooleanVariable("Fr"+sourceblock), false);

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
