package team.brotherhoodofmutants.mutantengineapi;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import team.brotherhoodofmutants.mutantengineapi.config.Spark;

//import static spark.Spark.*;

import java.io.IOException;
import java.util.*;

public class Boot {

    public static void main(String[] args) throws IOException {
        //get("/hello", (req, res) -> "Hello World");
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        context.getBean(Spark.class).init();
        //context.getBean(Spark.class).bootApi();
        //String[] dna =  {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTA"};
        String[] dna =  {"ATTTTA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTA"}; // TESTE 2 CHAIN LINES
        //String[] dna =  {"AAAAAA","CCCCCC","CCCCCC","CCCCCC","CCCCCC","TTTTTT"}; // TESTE 3 CHAIN COLUMN

        Boot api = new Boot();
        boolean response  = api.isMutant(dna);
        System.out.println(response);

    }

    private boolean isMutant(String[] dna){
        //PRE-ANALISE DAS CADEIAS
//1. checagem da quantidade de cadeias: não podem ser menor que 4 ok
//2. verificar se existe algum outro caracter diferente de ATGC
//3. verificar se todas as cadeias possuem a mesma largura e todas são maior que 4

        try{
            if(dna.length < 4)
                throw new Exception("FALTA MAIS CHAIN DE DNA");
            if(!Arrays.stream(dna).allMatch(x -> StringUtils.containsOnly(x,"TAGC")))
                throw new Exception("CARACTER ERRADO");
            //verificar se é necessário essa regra
            if(!Arrays.stream(dna).allMatch(x -> x.length() == dna.length))
                throw new Exception("TAMANHO DISTINTO ENTRE LARGURA DA CHAIN x QTD DE CHAIN");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

//TRATAMENTO DAS CADEIAS
//0. criar matriz com num de colunas com a quantidade de caracteres em uma cadeia e
//num de linhas com a quantidade de cadeias.
//1. popular matriz

        int lines = dna[0].length();
        int columns = dna.length;
        String[][] matrixDNA = new String[lines][columns];


        for(int iL = 0; iL <lines ; iL++){
            for(int iC = 0; iC<columns; iC++){
                matrixDNA[iL][iC] = dna[iL].substring(iC,iC+1);
                //System.out.println("matrixDNA["+iL+"]["+iC+"] = "+dna[iL].substring(iC,iC+1));
            }
        }

        int sameSequence = 0;
        /*processByLine*/

        int countBySameNucleotide;
        //String nucleotide;

        for(int iL = 0; iL <lines ; iL++){
            countBySameNucleotide = 0;
            //nucleotide = matrixDNA[iL][0];

            for(int iC = 0; iC<columns; iC++){
                if(iC == 0) countBySameNucleotide++;
                if(iC !=0) {
                    if (matrixDNA[iL][iC].equals(matrixDNA[iL][iC - 1])) {
                        countBySameNucleotide++;
                    } else {
                        //if (countBySameNucleotide > 1) countBySameNucleotide--;
                        countBySameNucleotide = 1;
                    }
                    if (countBySameNucleotide == 4) {
                        sameSequence++;
                    }
                    //else if (countBySameNucleotide > 4){
                    //    sameSequence = sameSequence + (countBySameNucleotide % 4);
                    //}
                }
            }
        }

        //processByColumn
        for(int iC = 0; iC <lines ; iC++){
            countBySameNucleotide = 0;
            for(int iL = 0; iL<columns; iL++){
                if(iL == 0) countBySameNucleotide++;
                if(iL !=0) {
                    if (matrixDNA[iL][iC].equals(matrixDNA[iL - 1][iC])) {
                        countBySameNucleotide++;
                    } else {
                        countBySameNucleotide = 1;
                    }
                    if (countBySameNucleotide == 4) {
                        sameSequence++;
                    }
                    //else if (countBySameNucleotide > 4){
                    //    sameSequence = sameSequence + (countBySameNucleotide % 4);
                    //}
                }

            }
        }

        //Sequencia obliqua left-to-right
        List<ObliqueChain> obliqueChains = new ArrayList<>();
        Integer incrementByChain = 0;
        int indexesDifference;
        for(int iL = 0; iL<lines; iL++){
            for(int iC = 0; iC<columns; iC++){
                indexesDifference = iC-iL;
                if(indexesDifference >= -2 && indexesDifference <=2){
                    incrementByChain++;
                    obliqueChains.add(new ObliqueChain(incrementByChain,indexesDifference,matrixDNA[iL][iC]));
                }
            }
        }

        //Sequencia obliqua right-left
        int indexesSum;
        for(int iL = 0; iL<lines; iL++){
            for(int iC = 0; iC<columns; iC++){
                indexesSum = iC+iL;
                if(indexesSum >= 3 && indexesSum <=7){
                    incrementByChain++;
                    obliqueChains.add(new ObliqueChain(incrementByChain,indexesSum,matrixDNA[iL][iC]));
                }
            }
        }

//      Collections.sort(obliqueChains, Comparator.comparing(s -> s.getLine()));

        sameSequence = sameSequence + executeLoop(obliqueChains);

        //MOTOR DE ANALISE DE MUTANTE. CASO ENCONTRE UM SEQ, SOMA, ASSIM QUE CHEGAR EM 4, retorna.
        //Em paralelo, verifificar se ha sequencia horizontal
        //Em paralelo, verifificar se ha sequencia vertical

        //Em paralelo, verifificar se ha sequencia obliqua left-to-right
        //Em paralelo, verifificar se ha sequencia obliqua right-to-left

        if(sameSequence>1)
            return true;
        return false;
    }

    private static int executeLoop(List<ObliqueChain> obliqueChains){
        int sameSequence = 0;
        int countBySameNucleotide = 0;
            for (int iL = 0; iL < obliqueChains.size(); iL++) {
                for (int iC = 0; iC < obliqueChains.size(); iC++) {
                    if(obliqueChains.get(iL).getLine() == obliqueChains.get(iC).getLine() &&
                            obliqueChains.get(iL).getNucleotide() == obliqueChains.get(iC).getNucleotide()){
                        countBySameNucleotide++;
                }
                if (countBySameNucleotide == 4) {
                        sameSequence++;
                    }
            }
        }
        return sameSequence;
    }

}
