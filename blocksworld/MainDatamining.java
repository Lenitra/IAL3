package blocksworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import datamining.AssociationRule;
import datamining.BooleanDatabase;
import modelling.BooleanVariable;

public class MainDatamining {
    public static void main(String[] args) {
        // Création de la base de données
        BooleanDatabase db = new BooleanDatabase(null);

        // Génération de 10 000 instances d'états du monde des blocs
        for (int i = 0; i < 10000; i++) {
            BWState state = new BWState(10, 3);
            Set<BooleanVariable> instance = state.getBoolVars();
            db.add(instance);
        }
    }

}