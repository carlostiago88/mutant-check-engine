package team.brotherhoodofmutants.mutantengine.dataproviders.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SearchCounterDataProvider {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(boolean isMutant){
        jdbcTemplate.execute("INSERT INTO humanSurvey (is_mutant) VALUES ("+isMutant+")");
    }



}