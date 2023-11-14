// package blocksworld;

// import java.util.*;

// import modelling.*;



// public class BWContraintes extends BlocksWorld{

//     protected int nbBlocks; // nombre de blocs
//     protected int pile; // pile
//     // ensemble des contraintes
//     protected Set<Constraint> constraints = new HashSet<>();
//     // ensemble des variables
//     protected Set<Variable> variables = new HashSet<>();

//     public BWContraintes(int block, int pile) {
//         super(block, pile);
//         this.constraints = new HashSet<>();
        
//     }

//     public Set<Constraint> Onb() {
//         Set<Constraint> resultat = new HashSet<>(); // ensemble des contraintes
//         BWVariable variables = new BWVariable(this.nbBlocks, this.pile);
//         variables.setVariables();
//         Set<Variable> variablesOn = variables.getOnb();
//         Set<Variable> variablesFixed = variables.getFixedb();
//         Set<Variable> variablesFree = variables.getFreep();

//         for(Variable variable1 : variablesOn){
//             for(Variable variable2 : variablesOn){
//                 if(!variable1.equals(variable2)){
//                     resultat.add(new DifferenceConstraint(variable1, variable2));
//                 }
//             }
//             for(Variable variable3 : variablesFixed){
//                 // On recupere le i dans le nom de la variable de la forme Variable("fixed_" + i);
//                 int i = Integer.parseInt(variable3.getName().substring(6));
//                 resultat.add(new Implication(variable1, Set.of(i), variable3, Set.of(true)));
//             }
//             for(Variable variable4 : variablesFree){
//                 // On recupere le i dans le nom de la variable de la forme Variable("free_" + i);
//                 int i = Integer.parseInt(variable4.getName().substring(5));
//                 resultat.add(new Implication(variable1, Set.of(i), variable4, Set.of(false)));
//             }
//         }
        
//         return resultat; // on retourne l’ensemble des contraintes
//     }


//     public Set<Constraint> getConstraints() {
//         return constraints;
//     }

//     // @Override
//     // public String toString() {
//     //     return "{" + "nbBlocks=" + getNbBlocks() + ", pile=" + getPile() + ", constraints=" + getConstraints() +"}";
//     // }

    
    
    
// }