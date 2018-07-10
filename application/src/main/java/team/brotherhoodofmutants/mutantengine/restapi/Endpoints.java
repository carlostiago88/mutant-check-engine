package team.brotherhoodofmutants.mutantengine.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.usecase.enums.Chains;
import team.brotherhoodofmutants.mutantengine.usecase.gateways.MutantGateway;
import team.brotherhoodofmutants.mutantengine.utils.JsonUtils;

import static spark.Spark.get;
import static spark.Spark.post;

@Component
public class Endpoints {

    private final MutantGateway mutantGateway;

    @Autowired
    public Endpoints(MutantGateway mutantGateway){
        this.mutantGateway = mutantGateway;
    }

    public void setupEndpoints(){
        get("/stats", (request, response) -> {
            return mutantGateway.getStats();
        }, JsonUtils::toJson);

        post("/mutant/", (request, response) -> {
            String dna = Chains.parse(request.params(""));
            //return mutantGateway.isMutant(dna);
            return dna;
        }, JsonUtils::toJson);
    }
}
