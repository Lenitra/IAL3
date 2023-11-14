package blocksworld;

import java.util.*;

import modelling.*;

public class BWRegularyConstraintes extends BWConstraintes {

    public BWRegularyConstraintes(int nbBlocks, int pile) {
        super(nbBlocks, pile);
        setRegularyConstraints();

    }

    /**
     * Method instanciating constraints in order to achieve a regular configuration
     */
    public void setRegularyConstraints() {
        
        for (Variable Oni : bwv.getOnb()) {

            for(Variable Onj : bwv.getOnb()) {

                int i = bwv.getIndex(Oni);
                int j = bwv.getIndex(Onj);
                int k = j - (i - j);

                if (i != j) {
                    Set<Object> Domain_i = new HashSet<>();
                    Domain_i.add(j);
                    Set<Object> Domain_j = new HashSet<>(bwv.nbPiles);
                    
                    if(k >= 0 && k < nbBlocks) {
                        Domain_j.add(k);
                    }

                    Constraint c1 = new Implication(Oni, Domain_i, Onj, Domain_j);
                    constraints.add(c1);
                }
            }
        }
    }

    

}
    
