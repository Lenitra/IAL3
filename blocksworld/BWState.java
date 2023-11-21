package blocksworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BWState {
    private int nbBlocks;
    private int nbPiles;
    private List<List<Integer>> piles;

    public BWState(int nbBlocks, int nbPiles) {
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
        this.piles = generateInitialState();
    }

    private List<List<Integer>> generateInitialState() {
        // Initialisation de la liste qui représentera toutes les piles
        List<List<Integer>> initialState = new ArrayList<>();
        // On crée une pile vide pour chaque pile
        for (int i = 0; i < nbPiles; i++) {
            initialState.add(new ArrayList<>());
        }


        for (int i = 0; i < nbBlocks; i++) {
            // get a random pile
            Random random = new Random();
            int pileIndex = random.nextInt(nbPiles);
            //Ajout du bloc à la pile
            initialState.get(pileIndex).add(i);
            
        }

        // shuffle the piles
        for (List<Integer> pile : initialState) {
            // On mélange les blocs dans chaque pile
            for (int i = 0; i < pile.size(); i++) {
                int randomIndex = (int) (Math.random() * pile.size());
                int tmp = pile.get(i);
                pile.set(i, pile.get(randomIndex));
                pile.set(randomIndex, tmp);
            }
        }
        return initialState; // Retour de l'état initial complet
    }

    public List<List<Integer>> getPiles() {
        return piles;
    }

    // public Set<BooleanVariable> getBoolVars() {
    //     BWDataBoolVars data = new BWDataBoolVars(nbBlocks, nbPiles);
    //     Set<BooleanVariable> boolVars = data.getBoolFromState(piles);

    //     return boolVars;
    // }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> pile : piles) {
            sb.append(pile).append("\n");
        }
        return sb.toString();
    }


    
}
