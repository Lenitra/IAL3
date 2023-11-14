package blocksworld;

public class main {


    public static void main(String[] args) {
    
        
        // créer un petit exemple, quelques configurations, et afficher pour
        // chacune un message indiquant si elle satisfait toutes les contraintes d’une configuration régulière et/ou
        // croissante. Faire de même une démonstration des autres contraintes implémentées, s’il y en a.
        BlocksWorld bw = new BlocksWorld(5, 4);
        BWConstraintes bwc = new BWConstraintes(5, 4);
        System.out.println("Contraintes : " + bwc.constraints);
        System.out.println("Nombre de contraintes : " + bwc.constraints.size());
        System.out.println();
        System.out.println("On : " + bw.getOnbV());
        System.out.println();
        System.out.println("Fixed : " + bw.getFixedbV());
        System.out.println();
        System.out.println("Free : " + bw.getFreepV());
        System.out.println();
        System.out.println("All : " + bw.getVariables());
        System.out.println();

    
    }
}
