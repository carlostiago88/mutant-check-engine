package team.brotherhoodofmutants.mutantengine.configuration;

import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class Datasource {

    @Bean
    public DataSource Datasource() throws SQLException{
        String jdbcUrl = "jdbc:h2:mem:db";
        String username = "sa";
        String password = "sa";
        JdbcConnectionPool jdbcConnectionPool = JdbcConnectionPool.create(jdbcUrl,username,password);
        executeScripts(jdbcConnectionPool);
        return jdbcConnectionPool;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource) {
        return new JdbcTemplate(datasource);
    }

    private void executeScripts(JdbcConnectionPool jdbcConnectionPool) throws SQLException{
        Connection conn = jdbcConnectionPool.getConnection();
        conn.createStatement().execute("DROP TABLE human_survey IF EXISTS");
        conn.createStatement().execute("CREATE TABLE human_survey (" +
                "id IDENTITY NOT NULL," +
                "is_mutant VARCHAR NOT NULL)");
    }
}

