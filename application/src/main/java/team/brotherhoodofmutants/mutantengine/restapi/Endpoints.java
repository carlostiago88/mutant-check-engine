package team.brotherhoodofmutants.mutantengine.restapi;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.usecase.domains.Human;
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
            mutantGateway.getStats();
            return true;
        }, JsonUtils::toJson);

        /*post("/users", (request, response) -> {
            response.type("application/json");
            Human user = new Gson().fromJson(request.body(), Human.class);
            userService.addUser(user);
         
            //return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });*/

        post("/mutant", (request, response) -> {
            response.type("application/json");
            Human human = new Gson().fromJson(request.body(), Human.class);
            if(mutantGateway.isMutant(human)){
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
