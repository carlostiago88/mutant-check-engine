package team.brotherhoodofmutants.mutantengine.usecase.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Human;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Matrix;
import team.brotherhoodofmutants.mutantengine.usecase.services.ChainProcessorService;
import team.brotherhoodofmutants.mutantengine.usecase.services.MatrixService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MutantGateway {

    @Autowired
    MatrixService matrixService;

    @Autowired
    ChainProcessorService chainProcessorService;

    public void getStats(){
        //ObjectMapper objectMapper = new ObjectMapper();
        //Mutant car = new Car("yellow", "renault");
        //objectMapper.writeValue(, car);
        //return objectMapper;
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
