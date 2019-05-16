package vu.lt.alternatives;
import javax.enterprise.inject.Alternative;


@Alternative
public class StandardScolarship implements Scolarship {

    @Override
    public String ScolarshipType(){
        return "60";
    }
}
