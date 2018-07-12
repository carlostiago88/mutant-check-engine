package team.brotherhoodofmutants.mutantengine.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Boot {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        context.getBean(Spark.class).init();
    }
}
