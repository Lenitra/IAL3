package blocksworld;

public class MainSatisfaction {
//     Dans une classe exécutable, créer au moins une instance du monde des blocs (en spécifiant le
// nombre de blocs et le nombre de piles), en utilisant les contraintes demandant une configuration régulière,
// et lancer tous les solveurs de contraintes implémentés en affichant leur temps de calcul et la solution
// trouvée (s’il en existe une)
    public static void main(String[] args) {
        BlocksWorld bw = new BlocksWorld(3, 3);
        System.out.println(bw.getOnbV());
        System.out.println(bw.getFixedb());
        System.out.println(bw.getFreep());
        System.out.println(bw.getVariables());
        System.out.println(bw.getBWVariable());
        BWConstraintes bwc = new BWConstraintes(3, 3);
        System.out.println(bwc.getConstraints());
        System.out.println(bwc.getConstraints().size());
        
    }
}
