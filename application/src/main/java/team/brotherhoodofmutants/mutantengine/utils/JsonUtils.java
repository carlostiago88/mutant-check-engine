package team.brotherhoodofmutants.mutantengine.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.StringWriter;

    public class JsonUtils {

        private static final ObjectMapper MAPPER = new ObjectMapper();

        static {
            MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        }

        public static String toJson(Object data) {
            try {
                StringWriter sw = new StringWriter();
                MAPPER.writeValue(sw, data);
                sw.close();
                return sw.toString();
            } catch (IOException e) {
                throw new RuntimeException("Error converting " + data.toString() + " to json", e);
            }
        }

    }
