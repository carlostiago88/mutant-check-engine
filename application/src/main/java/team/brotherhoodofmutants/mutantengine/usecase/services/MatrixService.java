package team.brotherhoodofmutants.mutantengine.usecase.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Human;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Matrix;
import team.brotherhoodofmutants.mutantengine.usecase.enums.Nucleotide;

import java.sql.SQLOutput;
import java.util.Arrays;

@Service
public class MatrixService {

    private void isValidDnaChain(String[] dna){
        try {
            if (dna.length < 4)
                throw new StringIndexOutOfBoundsException("FALTA MAIS CHAIN DE DNA");
            if (!Arrays.stream(dna).allMatch(x -> StringUtils.containsOnly(x, Nucleotide.TYPES.getTypes())))
                throw new Exception("CARACTER ERRADO");
            //verificar se é necessário essa regra
            if (!Arrays.stream(dna).allMatch(x -> x.length() == dna.length))
                throw new StringIndexOutOfBoundsException("TAMANHO DISTINTO ENTRE LARGURA DA CHAIN x QTD DE CHAIN");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Array fora do índice: "+e.getMessage());
        }
        catch (Exception e){
            System.out.println("Erro de caracter");
        }
    }

    public Matrix mountMatrixFor(Human human) throws StringIndexOutOfBoundsException{
            String[] dna = human.getDna();

            isValidDnaChain(dna);

            Matrix matrix = new Matrix();

            matrix.setLines(dna[0].length());
            matrix.setColumns(dna.length);

            String[][] elements = new String[matrix.getLines()][matrix.getColumns()];
            for (int iL = 0; iL < matrix.getLines(); iL++) {
                for (int iC = 0; iC < matrix.getColumns(); iC++) {
                    elements[iL][iC] = dna[iL].substring(iC, iC + 1);
                }
            }
            matrix.setValues(elements);


        return matrix;
    }
}
