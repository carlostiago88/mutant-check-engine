package team.brotherhoodofmutants.mutantengine.core.entity;

import java.io.Serializable;

public class Human implements Serializable {

    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }


}
