package blocksworld;

import java.util.*;

import modelling.*;

public class BWRegularyConstraintes extends BWConstraintes{

    // liste des contraintes
    protected Set<Constraint> constraints = new HashSet<>();
    protected int nbBlocks;
    protected int nbPiles;

    public BWRegularyConstraintes(int nbBlocks, int nbPiles) {
        super(nbBlocks, nbPiles);
        this.nbBlocks = nbBlocks;
        this.nbPiles = nbPiles;
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

    
    public Set<Constraint> getConstraints() {
        return this.constraints;
    }
    
}