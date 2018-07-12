package team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import team.brotherhoodofmutants.mutantengine.core.entity.Human;
import team.brotherhoodofmutants.mutantengine.core.entity.Matrix;
import team.brotherhoodofmutants.mutantengine.core.entity.NucleotideType;
import team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect.exceptions.MatrixException;
import team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect.exceptions.NucleotideException;

import java.util.Arrays;

@Service
public class MatrixService {

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

    private void isValidDnaChain(String[] dna) {
        if(dna == null)
            throw new MatrixException("DNA Matrix is empty");
        if (dna.length < 4)
            throw new MatrixException("DNA Matrix length is wrong");
        if (!Arrays.stream(dna).allMatch(x -> StringUtils.containsOnly(x, NucleotideType.TYPES.getTypes())))
            throw new NucleotideException("NucleotideType Types: A|T|C|G");
        if (!Arrays.stream(dna).allMatch(x -> x.length() == dna.length))
            throw new MatrixException("Size of columns and lines from DNA MAtrix must be same");
    }
}
