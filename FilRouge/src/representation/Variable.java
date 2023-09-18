package representation;


public class Variable {

    protected String nom;
    protected Object domaine;

    public Variable(String nom, Object domaine) {
        
        this.nom = nom;
        this.domaine = domaine;
    }

    public String getName(){
        return nom;
    }

    public Object getDomain() {
        return this.domaine;
    }

    @Override
    public boolean equals(Object a){
        return a.equals(this.nom);
    }
    
    @Override
    public int hashCode() {
        return this.nom.hashCode();
    }

    public void setDomain(Object domain, int nbBlocks, int pile, int i) {

        for (int p = -pile; p < nbBlocks; p++) {
            domain = p;
        }
        domain = i;
        
    }

    public int getIndex() {
        int index = 0;
        String [] tab = this.nom.split("_");
        index = Integer.parseInt(tab[1]);
        return index;
    }

    public void setName(String name) {
        this.nom = name;
    }
    
}