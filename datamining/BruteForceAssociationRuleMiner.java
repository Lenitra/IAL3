package datamining;

import java.util.HashSet;
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
        //on crée un ensemble vide de sous-ensembles
        Set<Set<BooleanVariable>> candidatePremises = new HashSet<>();
        candidatePremises.add(new HashSet<>());

        //on parcourt les items de l'itemset
        for(BooleanVariable item : itemset){
            //on crée un candidat sous-ensemble
            Set<Set<BooleanVariable>> newCandidatePremises = new HashSet<>();

            //on parcourt tous les candidats sous-ensembles
            for (Set<BooleanVariable> candidatePremise : candidatePremises){
                //on ajoute le candidat sous-ensemble à l'ensemble des sous-ensembles
                newCandidatePremises.add(candidatePremise);

                //on crée un nouveau sous-ensemble qui contient le candidat sous-ensemble et l'item
                Set<BooleanVariable> newCandidatePremise = new HashSet<>(candidatePremise);
                newCandidatePremise.add(item);
                newCandidatePremises.add(newCandidatePremise);
            } 
            //on met à jour l'ensemble des sous-ensembles
            candidatePremises = newCandidatePremises;
        }
        //on supprime l'ensemble vide et l'itemset de l'ensemble des sous-ensembles donné en paramètre
        candidatePremises.remove(new HashSet<>());
        candidatePremises.remove(itemset);

        //on retourne l'ensemble des sous-ensembles
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