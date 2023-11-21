package blocksworld;

import java.util.List;
import java.util.Set;

import bwgeneratordemo.Demo;
import datamining.AssociationRule;
import datamining.BooleanDatabase;
import datamining.BruteForceAssociationRuleMiner;
import modelling.BooleanVariable;

import java.util.Random;

public class MainDatamining {
    public static void main(String[] args) {

        BWDataBoolVars bw = new BWDataBoolVars(5, 5);
        BooleanDatabase db = new BooleanDatabase(bw.getBoolVariables());
        for (int i = 0; i < 100000; i++) {
        // Drawing a state at random
            List<List<Integer>> state = Demo.getState(new Random());
            // Converting state to instance
            Set<BooleanVariable> instance = bw.takeTransaction(state);
            // Adding state to database
            db.add(instance);
        }

        BruteForceAssociationRuleMiner bruteForceAssociationRuleMiner = new BruteForceAssociationRuleMiner(db);
        Set<AssociationRule> associationRules = bruteForceAssociationRuleMiner.extract(2/3f, 95/100f);

        System.out.println("Association rules : " + associationRules.toString());
        System.out.println("Number of association rules : " + associationRules.size());

        
    }
}