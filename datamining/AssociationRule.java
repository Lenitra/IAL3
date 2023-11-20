package datamining;

import java.util.Set;

import modelling.BooleanVariable;

//classe pour représenter une règle d'association
public class AssociationRule {
    
    private Set<BooleanVariable> premise;
    private Set<BooleanVariable> conclusion;
    private float frequency;
    private float confidence;
    
    /**
     * Constructeur
     * @param premise       ensemble de premisses
     * @param conclusion    ensemble de conclusions
     * @param frequency     fréquence de la règle
     * @param confidence    confiance de la règle
     */
    public AssociationRule(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, float frequency, float confidence) {
        this.premise = premise;
        this.conclusion = conclusion;
        this.frequency = frequency;
        this.confidence = confidence;
    }
    
    /**
     * Accesseur pour l'ensemble de premisses
     * @return  l'ensemble de premisses
     */
    public Set<BooleanVariable> getPremise() {
        return this.premise;
    }
    
    /**
     * Accesseur pour l'ensemble de conclusions
     * @return  l'ensemble de conclusions
     */
    public Set<BooleanVariable> getConclusion() {
        return this.conclusion;
    }
    
    /**
     * Accesseur pour la fréquence de la règle
     * @return  la fréquence de la règle
     */
    public float getFrequency() {
        return this.frequency;
    }
    
    /**
     * Accesseur pour la confiance de la règle
     * @return  la confiance de la règle
     */
    public float getConfidence() {
        return this.confidence;
    }

    @Override
    public String toString() {
        return "AssociationRule [premise=" + premise +System.lineSeparator()+ "conclusion=" + conclusion +System.lineSeparator()+ "frequency=" + frequency
                +System.lineSeparator()+ "confidence=" + confidence + "]" +System.lineSeparator()+ "----------------------" +System.lineSeparator();
    }
}
