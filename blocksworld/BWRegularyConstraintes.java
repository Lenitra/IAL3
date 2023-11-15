package blocksworld;

import java.util.*;

import modelling.*;

public class BWRegularyConstraintes extends BWConstraintes{

    public BWRegularyConstraintes(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
        RegularyConstraint(); 
    }

    public void RegularyConstraint() {
        for (Variable Oni : bw.getOnb()) {

            for(Variable Onj : bw.getOnb()) {

                int i = bw.getIndex(Oni);
                int j = bw.getIndex(Onj);
                int k = j - (i - j);

                if (i != j) {
                    Set<Object> Domain_i = new HashSet<>();
                    Domain_i.add(j);
                    Set<Object> Domain_j = new HashSet<>(bw.getPile());

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