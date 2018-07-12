package team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect.gateways;

import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.core.entity.Human;
import team.brotherhoodofmutants.mutantengine.core.entity.HumanType;
import team.brotherhoodofmutants.mutantengine.core.entity.Matrix;
import team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect.ChainProcessorService;
import team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect.MatrixService;
import team.brotherhoodofmutants.mutantengine.dataproviders.database.HumanSurveyDataProvider;

import java.util.List;
import java.util.Map;

@Component
public class MutantGateway {

    private MatrixService matrixService;
    private ChainProcessorService chainProcessorService;
    private HumanSurveyDataProvider humanSurveyDataProvider;

    public MutantGateway(MatrixService matrixService, ChainProcessorService chainProcessorService, HumanSurveyDataProvider humanSurveyDataProvider) {
        this.matrixService = matrixService;
        this.chainProcessorService = chainProcessorService;
        this.humanSurveyDataProvider = humanSurveyDataProvider;
    }

    public List<Map<String,Object>> getStats(){
        return humanSurveyDataProvider.getDataSurvey();
    }

    public HumanType checkIsMutantFor(Human human){
        Matrix matrix = matrixService.mountMatrixFor(human);
        HumanType humanType = chainProcessorService.getSequentialCount(matrix) > 1 ? HumanType.IS_MUTANT : HumanType.IS_NOT_MUTANT;
        humanSurveyDataProvider.save(humanType);
        return humanType;
    }

    public HumanType parallelCheckIsMutantFor(Human human) throws Exception{
        Matrix matrix = matrixService.mountMatrixFor(human);
        HumanType humanType =  chainProcessorService.getParallelCount(matrix) > 1 ? HumanType.IS_MUTANT : HumanType.IS_NOT_MUTANT;
        humanSurveyDataProvider.save(humanType);
        return humanType;
    }

}
