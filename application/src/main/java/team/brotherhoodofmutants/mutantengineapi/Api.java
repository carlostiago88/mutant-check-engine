package team.brotherhoodofmutants.mutantengineapi;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Api {

    public static void main(String[] args){

        String[] dna =  {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTA"};
        Api api = new Api();
        boolean response  = api.isMutant(dna);
        System.out.println(response);

    }

    private boolean isMutant(String[] dna){
        return true;
    }

}
