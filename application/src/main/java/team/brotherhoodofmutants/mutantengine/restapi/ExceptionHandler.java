package team.brotherhoodofmutants.mutantengine.restapi;

import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.usecase.exceptions.MatrixException;
import team.brotherhoodofmutants.mutantengine.usecase.exceptions.NucleotideException;

import static java.util.Collections.singletonMap;
import static spark.Spark.exception;
import static team.brotherhoodofmutants.mutantengine.utils.JsonUtils.toJson;

@Component
public class ExceptionHandler {
    public void setupExceptionHandlers() {
        exception(MatrixException.class, (e, request, response) -> {
            response.status(400);
            response.type("application/json");
            response.body(toJson(singletonMap("error", e.getMessage())));
        });
        exception(NucleotideException.class, (e, request, response) -> {
            response.status(400);
            response.type("application/json");
            response.body(toJson(singletonMap("error", e.getMessage())));
        });
        exception(RuntimeException.class, (e, request, response) -> {
            response.status(400);
            response.type("application/json");
            response.body(toJson(singletonMap("error", e.getMessage())));
        });
    }

}
