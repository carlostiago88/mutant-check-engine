package team.brotherhoodofmutants.mutantengine.usecase.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Human;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Matrix;
import team.brotherhoodofmutants.mutantengine.usecase.enums.Nucleotide;
import team.brotherhoodofmutants.mutantengine.usecase.exceptions.MatrixException;
import team.brotherhoodofmutants.mutantengine.usecase.exceptions.NucleotideException;

import java.util.Arrays;

@Service
public class MatrixService {

    private void isValidDnaChain(String[] dna) {
        if (dna.length < 4)
            throw new MatrixException("Matrix length is wrong");
        if (!Arrays.stream(dna).allMatch(x -> StringUtils.containsOnly(x, Nucleotide.TYPES.getTypes())))
            throw new NucleotideException("Nucleotide Types: A|T|C|G");
        if (!Arrays.stream(dna).allMatch(x -> x.length() == dna.length))
            throw new MatrixException("Size of columns and lines must be same");
    }

    public Matrix mountMatrixFor(Human human) throws NucleotideException, MatrixException{
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
