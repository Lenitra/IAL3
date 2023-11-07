package datamining;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import modelling.BooleanVariable;

//Cette classe permet d'extraire par brute force les règles d'association d'une base de données booléenne
public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner {

    /**
     * Constructeur
     * @param database base de données booléenne
     */
    public BruteForceAssociationRuleMiner(BooleanDatabase database) {
        super(database);
    }

    /**
     * calcule tous les sous-ensembles d'un itemset 
     * @param itemset un itemset
     * @return un ensemble contenant tous les sous ensembles de l'itemset
     */
    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> itemset){
        //on stocke tous les sous-ensembles de l'itemset dans un ensemble
        Set<Set<BooleanVariable>> candidatePremises = new HashSet<>();

        //on regarde le nombre de sous-ensembles possibles
        //math.pow correspond à la fonction puissance où on fait 2 puissance le nombre d'éléments de l'itemset
        int n = (int) Math.pow(2, itemset.size());

        //on parcourt tous les sous-ensembles possibles calculés au dessus
        for(int i = 1; i < n; i++){
            //on crée un ensemble vide pour stocker les sous-ensembles
            Set<BooleanVariable> s = new HashSet<>();
            //on crée un itérateur pour parcourir les éléments de l'itemset
            Iterator<BooleanVariable> it = itemset.iterator();

            //on parcourt les éléments de l'itemset
            for(int j = 0; j < itemset.size(); j++){
                //on récupère l'élément suivant
                BooleanVariable var = it.next();

                //on regarde si l'élément est dans le sous-ensemble
                //on regarde si le bit j de i est à 1
                if((i & (int) Math.pow(2, j)) != 0){
                    s.add(var);
                }
            }
            //on ajoute l'ensemble à l'ensemble des sous-ensembles
            candidatePremises.add(s);
        }
        //on enlève l'ensemble vide et l'itemset de l'ensemble des sous-ensembles
        candidatePremises.remove(new HashSet<>());
        candidatePremises.remove(itemset);
        System.out.println(candidatePremises);
        return candidatePremises;
    }

    /**
     * Extrait les règles d'association dont la fréquence est supérieure ou égale à frequency et la confanice est supérieure ou égale à confidence
     * @param frequency  fréquence minimum pour les ensembles d'éléments
     * @param confidence confiance minimum pour les règles d'association
     * @return un ensemble de règles d'association
     */
    @Override
    public Set<AssociationRule> extract(float frequency, float confidence) {
        //on crée un ensemble vide de règles d'association
        Set<AssociationRule> rules = new HashSet<>();

        //on crée un objet Apriori pour extraire les itemsets fréquents
        Apriori apriori = new Apriori(database);

        //on extrait les itemsets fréquents de la base de données
        Set<Itemset> itemsets = apriori.extract(frequency);

        //on parcourt les itemsets fréquents
        for (Itemset itemset : itemsets) {
            //on récupère les items de l'itemset et on calcule leur fréquence
            Set<BooleanVariable> items = itemset.getItems();
            float itemsFrequency = frequency(items, itemsets);

            //on calcule la confiance de l'itemset
            Set<Set<BooleanVariable>> subSets = allCandidatePremises(items);

            //on parcourt les sous-ensembles de l'itemset
            for (Set<BooleanVariable> subSet : subSets) {

                //on crée un ensemble d'éléments qui ne contient pas le sous-ensemble
                Set<BooleanVariable> itemsWithoutSubSet = new HashSet<>(items);
                itemsWithoutSubSet.removeAll(subSet);

                //on calcule la fréquence de l'ensemble initialisé au dessus sans le sous-ensemble
                float itemsWithoutSubSetFrequency = frequency(itemsWithoutSubSet, itemsets);

                //on calcule la confiance de la règle d'association
                float confidence2 = itemsFrequency / itemsWithoutSubSetFrequency;

                //si la confiance est supérieure à la confiance minimale on ajoute la règle d'association à l'ensemble des règles d'association
                if (confidence2 >= confidence) {
                    AssociationRule rule = new AssociationRule(itemsWithoutSubSet, subSet, itemsFrequency, confidence2);
                    rules.add(rule);
                }

            }
        }
        //on retourne l'ensemble des règles d'association qui respectent les critères spécifiés
        return rules;
    }  
}