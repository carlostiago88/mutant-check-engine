package team.brotherhoodofmutants.mutantengine.usecase.services;

import org.springframework.stereotype.Service;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Matrix;
import team.brotherhoodofmutants.mutantengine.usecase.domains.ObliqueChain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ChainProcessorService {

    public int processorByLine(Matrix matrix){
        int sameSequence = 0;
        int countBySameNucleotide;

        for(int iL = 0; iL <matrix.getLines() ; iL++){
            countBySameNucleotide = 0;

            for(int iC = 0; iC<matrix.getColumns(); iC++){
                if(iC == 0) countBySameNucleotide++;
                if(iC !=0) {
                    if (matrix.getValues()[iL][iC].equals(matrix.getValues()[iL][iC - 1])) {
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
        return sameSequence;
    }

    public int processorByColumn(Matrix matrix){
        int countBySameNucleotide;
        int sameSequence = 0;
        for(int iC = 0; iC <matrix.getLines() ; iC++){
            countBySameNucleotide = 0;
            for(int iL = 0; iL<matrix.getColumns(); iL++){
                if(iL == 0) countBySameNucleotide++;
                if(iL !=0) {
                    if (matrix.getValues()[iL][iC].equals(matrix.getValues()[iL - 1][iC])) {
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
        return sameSequence;
    }

    public int processorByObliqueLeft(Matrix matrix){
        List<ObliqueChain> obliqueChains = new ArrayList<>();
        Integer incrementByChain = 0;
        int indexesDifference;
        for(int iL = 0; iL<matrix.getLines(); iL++){
            for(int iC = 0; iC<matrix.getColumns(); iC++){
                indexesDifference = iC-iL;
                if(indexesDifference >= -2 && indexesDifference <=2){
                    incrementByChain++;
                    obliqueChains.add(new ObliqueChain(incrementByChain,indexesDifference,matrix.getValues()[iL][iC]));
                }
            }
        }
        return sequenceObliqueCounter(obliqueChains);
    }

    public int processorByObliqueRight(Matrix matrix){
        Integer incrementByChain = 0;
        int indexesSum;
        List<ObliqueChain> obliqueChains = new ArrayList<>();
        for(int iL = 0; iL<matrix.getLines(); iL++){
            for(int iC = 0; iC<matrix.getColumns(); iC++){
                indexesSum = iC+iL;
                if(indexesSum >= 3 && indexesSum <=7){
                    incrementByChain++;
                    obliqueChains.add(new ObliqueChain(incrementByChain,indexesSum,matrix.getValues()[iL][iC]));
                }
            }
        }
    return sequenceObliqueCounter(obliqueChains);
    }

    private int sequenceObliqueCounter(List<ObliqueChain> obliqueChains){
        int sameSequence = 0;
        int countBySameNucleotide = 1;

        Collections.sort(obliqueChains, Comparator.comparing(s -> s.getLine()));

        for (int i = 0; i < obliqueChains.size(); i++) {
            if(i != 0){
                if(obliqueChains.get(i).getLine() == obliqueChains.get(i-1).getLine()
                        && obliqueChains.get(i).getNucleotide().equals(obliqueChains.get(i-1).getNucleotide())){
                    countBySameNucleotide++;
                }
                else {
                    countBySameNucleotide = 1;
                }
                if(countBySameNucleotide == 4){
                    sameSequence++;
                }
            }
        }
        return sameSequence;
    }
}
