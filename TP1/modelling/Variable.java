package modelling;
import java.util.Set;

public class Variable {

    private String name;
    private Set<Object> domain;

    public Variable(String name, Set<Object> domain) {
        this.name = name;
        this.domain = domain;
    }

    public String getName() {
        return this.name;
    }

    public Set<Object> getDomain() {
        return this.domain;
    }

    @Override   
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Variable)) {
            return false;
        }
        Variable v = (Variable) o;
        return this.name.equals(v.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean isAssigned() {
        return false;
    }

    public Object getValue() {
        return null;
    }

}
