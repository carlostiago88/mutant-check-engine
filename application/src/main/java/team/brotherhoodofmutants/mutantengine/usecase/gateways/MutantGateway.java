package team.brotherhoodofmutants.mutantengine.usecase.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Human;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Matrix;
import team.brotherhoodofmutants.mutantengine.usecase.services.ChainProcessorService;
import team.brotherhoodofmutants.mutantengine.usecase.services.MatrixService;

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
        int countSequence = 0;

        Matrix matrix = matrixService.mountMatrixFor(human);

        //Todo: More Efficiency with change to Assync Execution
        countSequence = countSequence+ chainProcessorService.processorByLine(matrix);
        System.out.println("processorByLine:"+countSequence);
        countSequence = countSequence+ chainProcessorService.processorByColumn(matrix);
        System.out.println("processorByColumn:"+countSequence);
        countSequence = countSequence+ chainProcessorService.processorByObliqueLeft(matrix);
        System.out.println("processorByObliqueLeft:"+countSequence);
        countSequence = countSequence+ chainProcessorService.processorByObliqueRight(matrix);
        System.out.println("processorByObliqueRight:"+countSequence);

        return countSequence > 1;
    }

}
