package blocksworld;

public class main {


    public static void main(String[] args) {
    
        
        System.out.println("Etat du monde initial :");
        BlocksWorld bw = new BlocksWorld(6, 4);
        System.out.println(bw.toString());


        System.out.println("Contraintes :");
        BWConstraintes bwcc = new BWConstraintes(6,4);
        bwcc.allConstraints();
        System.out.println(bwcc.toString());
    
    
    }
}
