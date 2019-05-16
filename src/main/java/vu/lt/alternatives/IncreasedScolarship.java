package vu.lt.alternatives;

import javax.enterprise.inject.Alternative;

@Alternative
public class IncreasedScolarship implements Scolarship {

    @Override
    public String ScolarshipType() {
        return "97";
    }
}
