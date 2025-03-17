package com.api.supremeAdmision.repository.configquestionexambysubject;

import com.api.supremeAdmision.model.ConfigQuestionExamBySubject;
import com.api.supremeAdmision.repository.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ConfigQuestionExamBySubjectRepository extends JdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ConfigQuestionExamBySubjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ConfigQuestionExamBySubject> configRowMapper = (rs, rowNum) -> {
        ConfigQuestionExamBySubject config = new ConfigQuestionExamBySubject();
        config.setId(rs.getInt("id"));
        config.setQuantity(rs.getInt("quantity"));
        config.setSubjectId(rs.getInt("subject_id"));
        return config;
    };

    public List<ConfigQuestionExamBySubject> findAll() {
        String sql = "SELECT * FROM config_question_exam_by_subject";
        return jdbcTemplate.query(sql, configRowMapper);
    }

    public ConfigQuestionExamBySubject findById(int id) {
        String sql = "SELECT * FROM config_question_exam_by_subject WHERE id = ?";
        List<ConfigQuestionExamBySubject> configs = jdbcTemplate.query(sql, configRowMapper, id);
        return configs.isEmpty() ? null : configs.get(0);
    }

    public ConfigQuestionExamBySubject findBySubjectId(int subjectId) {
        String sql = "SELECT * FROM config_question_exam_by_subject WHERE subject_id = ?";
        List<ConfigQuestionExamBySubject> configs = jdbcTemplate.query(sql, configRowMapper, subjectId);
        return configs.isEmpty() ? null : configs.get(0);
    }

    public int save(ConfigQuestionExamBySubject config) {
        String sql = "INSERT INTO config_question_exam_by_subject (quantity, subject_id) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, config.getQuantity());
            ps.setInt(2, config.getSubjectId());
            return ps;
        }, keyHolder);
        
        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
    }

    public int update(ConfigQuestionExamBySubject config) {
        String sql = "UPDATE config_question_exam_by_subject SET quantity = ?, subject_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, config.getQuantity(), config.getSubjectId(), config.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM config_question_exam_by_subject WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
