package modelling;
import java.util.Set;

// Cette classe représente une variable dans un modèle
public class Variable {

    private String name;
    private Set<Object> domain;

    /**
     * Constructeur de la classe Variable
     * @param i le nom de la variable
     * @param domain le domaine des valeurs de la variable
     */
    public Variable(String i, Set<Object> domain) {
        this.name = i;
        this.domain = domain;
    }

    public String getName() {
        return this.name;
    }

    public Set<Object> getDomain() {
        return this.domain;
    }

    /**
     * Redéfinition de la méthode equals pour comparer les variables
     * 
     * @param o l'objet à comparer avec la variable
     * @return true si les variables sont égales, false sinon
     */
    @Override   
    public boolean equals(Object o) {
        // Si l'objet est la variable elle-même, on renvoie true
        if (o == this) {
            return true;
        }
        // Si l'objet n'est pas une instance de Variable, on renvoie false
        if (!(o instanceof Variable)) {
            return false;
        }

        // Si aucune des deux conditions précédentes n'est vérifiée, on suppose que o est une Variable
        Variable v = (Variable) o;

        // On compare la variable actuelle (this) et v
        return this.name.equals(v.name);
    }

    /**
     * Redéfinition de la méthode hashCode pour générer un code de hachage basé sur le nom de la variable
     * 
     * @return le code de hachage de la variable
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public void removeValueFromDomain(Object value) {
        this.domain.remove(value);
    }

    // to string
    @Override
    public String toString() {

        return "Variable [domain=" + domain.toString() + ", name=" + name + "]";
    }

}
