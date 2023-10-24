package datamining;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import modelling.BooleanVariable;

public class Apriori extends AbstractItemsetMiner {
    
        /**
         * Constructeur
         * @param database base de données booléenne
         */
        public Apriori(BooleanDatabase database) {
            super(database);
        }

        @Override
        public BooleanDatabase getDatabase() {
            return database;
        }

        /**
         * calcule les singletons fréquents
         * @param frequency  fréquence minimale
         * @return un ensemble d’itemsets contenant tous les singletons fréquents
         */
        public Set<Itemset> frequentSingletons(float frequency) {
            Set<Itemset> frequentSingletons = new HashSet<>();

            //on parcourt la base de données et on regarde pour chaque item sa fréquence
            for (BooleanVariable item : this.database.getItems()) {
                
                //on crée un set contenant l'item
                Set<BooleanVariable> items = new HashSet<>();
                items.add(item);

                //on calcule la fréquence de l'item
                float singletonFrequency = frequency(items);

                //si la fréquence de l'item est supérieure ou égale à la fréquence donnée en argument
                if (singletonFrequency >= frequency) {

                    //on crée un itemset contenant l'item et sa fréquence
                    Itemset itemset = new Itemset(items, singletonFrequency);

                    //on ajoute l'itemset à l'ensemble des itemsets fréquents
                    frequentSingletons.add(itemset);
                }
            }
            return frequentSingletons;
        }

        /**
         * combine deux ensembles triés d'items
         * @param set1  premier ensemble trié d'items
         * @param set2  second ensemble trié d'items
         * @return un ensemble trié d'items obtenu en les combinant, à condition que les deux ensembles aient la même taille k, les deux ensembles aient les mêmes k − 1 premiers items, les deux ensembles aient des k^es items différents. Dans tous les autres cas, la méthode devra renvoyer null.
         */
        public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable> set1, SortedSet<BooleanVariable> set2) {
            
            //test si les deux ensembles ont la même taille
            if (set1.size() != set2.size()) {
                return null;
            }

            //test si les tailles des deux ensembles sont non null
            if (set1.size() == 0 || set2.size() == 0) {
                return null;
            }

            //test si les deux ensembles ont les mêmes k − 1 premiers items
            if (set1.last() == set2.last()) {
                return null;
            }

            //test si les deux ensembles ont des k^es items différents
            if (!set1.headSet(set1.last()).equals(set2.headSet(set2.last()))) {
                return null;
            }

            //si toutes les conditions passent
            //on crée un nouvel ensemble trié d'items
            SortedSet<BooleanVariable> combineSet = new TreeSet<>(COMPARATOR);

            //on combine les deux ensembles
            combineSet.addAll(set1);
            combineSet.add(set2.last());
            return combineSet;
        }

        /**
         * regarde si tous les sous-ensembles d'un ensemble sont fréquents
         * @param ensItems  ensemble d’items
         * @param collEnsItems  collection d’ensembles d’items
         * @return true si tous les sous-ensembles de l’ensemble sont fréquents, false sinon
         */
        public static boolean allSubsetsFrequent(Set<BooleanVariable> ensItems, Collection<SortedSet<BooleanVariable>> collEnsItems) {
            
            //on parcourt tous les sous-ensembles de l'ensemble
            for (BooleanVariable item : ensItems) {

                //on crée un nouvel ensemble contenant tous les sous-ensembles de l'ensemble 
                Set<BooleanVariable> subset = new HashSet<>(ensItems);
                subset.remove(item);
                
                //si l'ensemble ne contient pas tous les sous-ensembles de l'ensemble on retourne false
                if (!collEnsItems.contains(subset)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public Set<Itemset> extract(float frequency ) {
            return null;
        }
    
}
