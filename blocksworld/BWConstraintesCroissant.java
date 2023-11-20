

import java.util.*;

import modelling.*;



public class BWConstraintesCroissant extends BWConstraintes{

    protected int nbBlocks; // nombre de blocs
    protected int pile; // pile

    public BWConstraintesCroissant(int block, int pile) {
        super(block, pile);
        this.constraints = generateConstraints();
        
    }
    
    //fonction qui permet de générer les contraintes croissantes
    public Set<Constraint> generateConstraints(){
        Set<Constraint> constraints = new HashSet<>();

        Set<Integer> pile = new HashSet<>();
        for(int i = -1; i > -nbPiles; i--){
            pile.add(i);
        }

        Collection<Variable> permutations = getOnbV();

        for(Variable v : permutations){
            for(Variable v2 : permutations){
                if (v != v2){
                    int v2S = bw.getIndex(v);

                    Set<Object> ens1 = new HashSet<>();
                    ens1.add(v2S);

                    Set<Integer> ens2 = new HashSet<>();
                    for (int j = 0; j < v2S; j++){
                        ens2.add(j);
                    }

                    ens1.addAll(ens2);

                    Constraint c1 = new Implication(v, Set.of(v2S), v2, ens1);
                    constraints.add(c1);
                }   
            }
        }
        return constraints;
    }
    
}