package team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect;

import org.junit.Assert;
import org.junit.Test;
import team.brotherhoodofmutants.mutantengine.core.entity.Human;
import team.brotherhoodofmutants.mutantengine.core.entity.HumanType;

public class MatrixServiceTest  {

    @Test
    public void shouldReturnIsNotMutant(){

        //todo: create tests
        String[] dna = {"AAGAAA","TAGTAT","ATTAGT","CGCGCG", "GCGCGA", "GCGCGA"};
        Human human = new Human();
        human.setDna(dna);

        //HumanType actual= mutantGateway.checkIsMutantFor(human);
        HumanType expected = HumanType.IS_NOT_MUTANT;
        //Assert.assertEquals(actual,expected);
    }
}
