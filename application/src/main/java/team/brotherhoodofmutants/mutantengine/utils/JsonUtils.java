package team.brotherhoodofmutants.mutantengine.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static <T> String toJson(T o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            throw new RuntimeException("Error converting " + o.toString() + " to json", e);
        }
    }

    public <T> T toClass(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }


}