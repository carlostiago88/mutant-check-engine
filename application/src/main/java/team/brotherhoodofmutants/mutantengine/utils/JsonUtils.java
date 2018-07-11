package team.brotherhoodofmutants.mutantengine.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.oracle.javafx.jmx.json.JSONException;
import org.eclipse.jetty.util.IO;

import java.io.IOException;
import java.io.StringWriter;

public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        //mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

    }

    public static <T> String toJson(T o) throws IOException {
        return mapper.writeValueAsString(o);
    }

/*
    public static String toJson(Object data) {
        try {
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, data);
            sw.close();
            return sw.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error converting " + data.toString() + " to json", e);
        }
    }*/

    public <T> T toClass(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }


}