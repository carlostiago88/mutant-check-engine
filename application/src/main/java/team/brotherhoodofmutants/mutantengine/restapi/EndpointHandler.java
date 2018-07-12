package team.brotherhoodofmutants.mutantengine.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.Response;
import team.brotherhoodofmutants.mutantengine.core.entity.Human;
import team.brotherhoodofmutants.mutantengine.core.entity.HumanType;
import team.brotherhoodofmutants.mutantengine.core.usecase.mutantdetect.gateways.MutantGateway;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static team.brotherhoodofmutants.mutantengine.configuration.utils.JsonUtils.toClass;
import static team.brotherhoodofmutants.mutantengine.configuration.utils.JsonUtils.toJson;

@Component
public class EndpointHandler {

    private final MutantGateway mutantGateway;

    @Autowired
    public EndpointHandler(MutantGateway mutantGateway){
        this.mutantGateway = mutantGateway;
    }

    public void setupEndpoints(){

        path("/api/v1", () -> {
            post("/mutant", (request, response) -> {
                Human human = toClass(request.body(), Human.class);
                HumanType humanType = mutantGateway.checkIsMutantFor(human);
                return mountedResponse(humanType, response);
            });
        });
        path("/api/v2", () -> {
            get("/stats", (request, response) -> {
                List<Map<String,Object>> resp = mutantGateway.getStats();
                return toJson(resp);
            });
            post("/mutant", (request, response) -> {
                Human human = toClass(request.body(), Human.class);
                HumanType humanType = mutantGateway.parallelCheckIsMutantFor(human);
                return mountedResponse(humanType, response);
            });
        });
    }

    private String mountedResponse(HumanType humanType, Response response){
        response.type("application/json");
        if(humanType.equals(HumanType.IS_MUTANT)){
            response.status(200);
            return toJson(singletonMap("result",humanType.getStatus()));
        }else{
            response.status(403);
            return toJson(singletonMap("result",humanType.getStatus()));
        }
    }
}
