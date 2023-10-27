package datamining;

import java.util.Set;

import modelling.BooleanVariable;

//Classe pour représenter un itemset
public class Itemset {
        
        private Set<BooleanVariable> items;
        private float frequency;
        
        /**
         * Constructeur
         * @param items       ensemble d'items
         * @param frequency   fréquence de l'itemset
         */
        public Itemset(Set<BooleanVariable> items, float frequency) {
            this.items = items;
            this.frequency = frequency;
        }
        
        /**
         * Accesseur pour l'ensemble d'items
         * @return  l'ensemble d'items
         */
        public Set<BooleanVariable> getItems() {
            return this.items;
        }
        
        /**
         * Accesseur pour la fréquence de l'itemset
         * @return la fréquence de l'itemset
         */
        public float getFrequency() {
            return this.frequency;
        }
        
}
