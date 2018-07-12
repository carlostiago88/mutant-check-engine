package team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.core.entity.Human;
import team.brotherhoodofmutants.mutantengine.core.entity.Matrix;
import team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect.ChainProcessorService;
import team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect.MatrixService;

@Component
public class MutantGateway {

    @Autowired
    MatrixService matrixService;

    @Autowired
    ChainProcessorService chainProcessorService;

    public void getStats(){
    }

    public boolean checkIsMutantFor(Human human) throws Exception{
        Matrix matrix = matrixService.mountMatrixFor(human);
        return chainProcessorService.getSequentialCount(matrix) > 1;
    }

    public boolean parallelCheckIsMutantFor(Human human) throws Exception{
        Matrix matrix = matrixService.mountMatrixFor(human);
        return chainProcessorService.getParallelCount(matrix) > 1;
    }

}
