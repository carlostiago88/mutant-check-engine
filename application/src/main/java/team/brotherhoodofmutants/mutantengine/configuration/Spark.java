package team.brotherhoodofmutants.mutantengine.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.restapi.EndpointHandler;
import team.brotherhoodofmutants.mutantengine.restapi.ExceptionHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static spark.Spark.port;
import static spark.Spark.threadPool;

@Component
public class Spark {

    private final Properties defaultProperties = new Properties();
    private final EndpointHandler endpointHandler;
    private final ExceptionHandler exceptionHandler;
    private int port;
    private int threadPoolMax;
    private int threadPoolMin;
    private int threadPoolIdleTimeout;

    @Autowired
    public Spark(EndpointHandler endpointHandler, ExceptionHandler exceptionHandler){
        this.endpointHandler = endpointHandler;
        this.exceptionHandler = exceptionHandler;
    };

    public void init() throws IOException{
        registerParams();

        port(this.getPort());
        threadPool(this.getThreadPoolMax(), this.getThreadPoolMin(), this.getThreadPoolIdleTimeout());

        endpointHandler.setupEndpoints();
        exceptionHandler.setupExceptionHandlers();
    }

    private void registerParams() throws IOException{
        try(InputStream inputStream = Spark.class.getResourceAsStream("/spark.properties")){
            defaultProperties.load(inputStream);
        }
        this.setPort(Integer.parseInt(defaultProperties.getProperty("server.port")));
        this.setThreadPoolMax(Integer.parseInt(defaultProperties.getProperty("server.threadPool.max")));
        this.setThreadPoolMin(Integer.parseInt(defaultProperties.getProperty("server.threadPool.min")));
        this.setThreadPoolIdleTimeout(Integer.parseInt(defaultProperties.getProperty("server.threadPool.idleTimeout")));
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getThreadPoolMax() {
        return threadPoolMax;
    }

    public void setThreadPoolMax(int threadPoolMax) {
        this.threadPoolMax = threadPoolMax;
    }

    public int getThreadPoolMin() {
        return threadPoolMin;
    }

    public void setThreadPoolMin(int threadPoolMin) {
        this.threadPoolMin = threadPoolMin;
    }

    public int getThreadPoolIdleTimeout() {
        return threadPoolIdleTimeout;
    }

    public void setThreadPoolIdleTimeout(int threadPoolIdleTimeout) {
        this.threadPoolIdleTimeout = threadPoolIdleTimeout;
    }
}
