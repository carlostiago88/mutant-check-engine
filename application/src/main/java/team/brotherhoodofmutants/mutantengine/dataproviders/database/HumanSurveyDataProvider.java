package team.brotherhoodofmutants.mutantengine.dataproviders.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import team.brotherhoodofmutants.mutantengine.core.entity.HumanType;

import java.util.List;
import java.util.Map;

@Service
public class HumanSurveyDataProvider {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(HumanType isMutant){
        jdbcTemplate.execute("INSERT INTO human_survey (is_mutant) VALUES ('"+isMutant.getStatus()+"')");
    }

    public List<Map<String,Object>> getDataSurvey(){
        return jdbcTemplate.queryForList("SELECT is_mutant,COUNT(1) as qtd FROM human_survey GROUP BY is_mutant");
    }



}