package team.brotherhoodofmutants.mutantengine.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class ApiConfig {
    private final Properties defaultProperties = new Properties();
    private final int port;
    private final int threadPoolMax;
    private final int threadPoolMin;
    private final int threadPoolIdleTimeout;

    @Autowired
    public ApiConfig() throws IOException {
        try (InputStream inputStream = ApiConfig.class.getResourceAsStream("/spark.properties")) {
            defaultProperties.load(inputStream);
        }
        this.port = Integer.parseInt(defaultProperties.getProperty("server.port"));
        this.threadPoolMax = Integer.parseInt(defaultProperties.getProperty("server.threadPool.max"));
        this.threadPoolMin = Integer.parseInt(defaultProperties.getProperty("server.threadPool.min"));
        this.threadPoolIdleTimeout = Integer.parseInt(defaultProperties.getProperty("server.threadPool.idleTimeout"));
    }
    public int getPort() {
        return port;
    }

    public int getThreadPoolMax() {
        return threadPoolMax;
    }

    public int getThreadPoolMin() {
        return threadPoolMin;
    }

    public int getThreadPoolIdleTimeout() {
        return threadPoolIdleTimeout;
    }
}
