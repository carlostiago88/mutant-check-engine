package team.brotherhoodofmutants.mutantengine.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.core.exceptions.InternalServerErrorException;
import team.brotherhoodofmutants.mutantengine.core.exceptions.MatrixException;
import team.brotherhoodofmutants.mutantengine.core.exceptions.NucleotideException;

import static java.util.Collections.singletonMap;
import static spark.Spark.exception;
import static team.brotherhoodofmutants.mutantengine.configuration.utils.JsonUtils.toJson;

@Component
public class ExceptionHandler {

    private static ObjectMapper mapper = new ObjectMapper();

    public void setupExceptionHandlers(){
        exception(MatrixException.class, (exception, request, response) -> {
            response.status(400);
            response.type("application/json");
            response.body(toJson(singletonMap("error", exception.getMessage())));
        });
        exception(NucleotideException.class, (exception, request, response) -> {
            response.status(400);
            response.type("application/json");
            response.body(toJson(singletonMap("error", exception.getMessage())));
        });
        exception(InternalServerErrorException.class, (exception, request, response) -> {
            response.status(500);
            response.type("application/json");
            response.body(toJson(singletonMap("error", exception.getMessage())));
        });
    }

}
