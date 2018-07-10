package team.brotherhoodofmutants.mutantengine.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.restapi.Endpoints;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static spark.Spark.port;
import static spark.Spark.threadPool;

@Component
public class Spark {

    private final Properties defaultProperties = new Properties();
    private final Endpoints endpoints;
    private final ApiConfig apiConfig;
    private int port;
    private int threadPoolMax;
    private int threadPoolMin;
    private int threadPoolIdleTimeout;

    @Autowired
    public Spark(Endpoints endpoints, ApiConfig apiConfig){
        this.endpoints = endpoints;
        this.apiConfig = apiConfig;
    };

    public void init() throws IOException{
        registerParams();

        port(apiConfig.getPort());
        threadPool(apiConfig.getThreadPoolMax(), apiConfig.getThreadPoolMin(), apiConfig.getThreadPoolIdleTimeout());

        endpoints.setupEndpoints();
    }

    private void registerParams() throws IOException{
        try(InputStream inputStream = ApiConfig.class.getResourceAsStream("/spark.properties")){
            defaultProperties.load(inputStream);
        }

        this.port = Integer.parseInt(defaultProperties.getProperty("server.port"));
        this.threadPoolMax = Integer.parseInt(defaultProperties.getProperty("server.threadPool.max"));
        this.threadPoolMin = Integer.parseInt(defaultProperties.getProperty("server.threadPool.min"));
        this.threadPoolIdleTimeout = Integer.parseInt(defaultProperties.getProperty("server.threadPool.idleTimeout"));
    }

}
