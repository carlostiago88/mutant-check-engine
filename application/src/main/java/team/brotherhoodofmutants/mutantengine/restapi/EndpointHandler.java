package team.brotherhoodofmutants.mutantengine.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Human;
import team.brotherhoodofmutants.mutantengine.usecase.enums.HumanType;
import team.brotherhoodofmutants.mutantengine.usecase.gateways.MutantGateway;
import team.brotherhoodofmutants.mutantengine.utils.JsonUtils;

import static java.util.Collections.singletonMap;
import static spark.Spark.get;
import static spark.Spark.post;
import static team.brotherhoodofmutants.mutantengine.utils.JsonUtils.toClass;
import static team.brotherhoodofmutants.mutantengine.utils.JsonUtils.toJson;

@Component
public class EndpointHandler {

    private final MutantGateway mutantGateway;

    @Autowired
    public EndpointHandler(MutantGateway mutantGateway){
        this.mutantGateway = mutantGateway;
    }

    public void setupEndpoints(){

        get("/stats", (request, response) -> {
            mutantGateway.getStats();
            return true;
        }, JsonUtils::toJson);
        post("/mutant", (request, response) -> {
            response.type("application/json");
            Human human = toClass(request.body(), Human.class);
            if(mutantGateway.parallelCheckIsMutantFor(human)){
                response.status(200);
                return toJson(singletonMap("isMutant",HumanType.IS_MUTANT.getStatus()));
            }else{
                response.status(403);
                return toJson(singletonMap("isMutant",HumanType.IS_NOT_MUTANT.getStatus()));
            }
        });
        post("/mutant-sequential/", (request, response) -> {
            response.type("application/json");
            Human human = toClass(request.body(), Human.class);
            if(mutantGateway.checkIsMutantFor(human)){
                response.status(200);
                return toJson(singletonMap("isMutant",HumanType.IS_MUTANT.getStatus()));
            }else{
                response.status(403);
                return toJson(singletonMap("isMutant",HumanType.IS_NOT_MUTANT.getStatus()));
            }
        });
    }
}
