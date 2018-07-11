package team.brotherhoodofmutants.mutantengine.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Human;
import team.brotherhoodofmutants.mutantengine.usecase.gateways.MutantGateway;
import team.brotherhoodofmutants.mutantengine.utils.JsonUtils;

import static spark.Spark.get;
import static spark.Spark.post;

@Component
public class EndpointHandler {

    private final MutantGateway mutantGateway;
    private static final JsonUtils json = new JsonUtils();


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
            Human human = json.toClass(request.body(), Human.class);
            if(mutantGateway.checkIsMutantFor(human)){
                response.status(200);
                response.body("YES");
            }else{
                response.status(403);
                response.body("NO");
            }
            return response;
        }, JsonUtils::toJson);
    }
}
