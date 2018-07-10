package team.brotherhoodofmutants.mutantengine.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.usecase.enums.Chains;
import team.brotherhoodofmutants.mutantengine.usecase.gateways.MutantGateway;
import team.brotherhoodofmutants.mutantengine.utils.JsonUtils;

import static spark.Spark.get;

@Component
public class Endpoints {

    private final MutantGateway mutantGateway;

    @Autowired
    public Endpoints(MutantGateway mutantGateway){
        this.mutantGateway = mutantGateway;
    }

    public void setupEndpoints(){
        get("/selectUsers/:target", (request, response) -> {
            String dna = Chains.parse(request.params(""));
            return mutantGateway.isMutant(dna);
        }, JsonUtils::toJson);
    }
}
