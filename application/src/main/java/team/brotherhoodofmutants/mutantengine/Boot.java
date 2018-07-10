package team.brotherhoodofmutants.mutantengine;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import team.brotherhoodofmutants.mutantengine.settings.Spark;

import java.io.IOException;

public class Boot {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        context.getBean(Spark.class).init();
    }
}
