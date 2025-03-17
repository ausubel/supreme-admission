package com.api.supremeAdmision.repository.question;

import com.api.supremeAdmision.model.Question;
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
public class QuestionRepository extends JdbcRepository {

    public QuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Question> questionRowMapper = (rs, rowNum) -> {
        Question question = new Question();
        question.setId(rs.getInt("id"));
        question.setDescription(rs.getString("description"));
        question.setAlternativeA(rs.getString("alternative_a"));
        question.setAlternativeB(rs.getString("alternative_b"));
        question.setAlternativeC(rs.getString("alternative_c"));
        question.setAlternativeD(rs.getString("alternative_d"));
        question.setCorrectAlternative(rs.getString("correct_alternative"));
        question.setSubjectId(rs.getInt("subject_id"));
        return question;
    };

    public List<Question> findBySubjectId(int subjectId) {
        String sql = "SELECT * FROM question WHERE subject_id = ?";
        return jdbcTemplate.query(sql, questionRowMapper, subjectId);
    }

    public List<Question> findAll() {
        String sql = "SELECT * FROM question";
        return jdbcTemplate.query(sql, questionRowMapper);
    }

    public Question findById(int id) {
        String sql = "SELECT * FROM question WHERE id = ?";
        List<Question> questions = jdbcTemplate.query(sql, questionRowMapper, id);
        return questions.isEmpty() ? null : questions.get(0);
    }

    public int save(Question question) {
        String sql = "INSERT INTO question (description, alternative_a, alternative_b, alternative_c, " +
                    "alternative_d, correct_alternative, subject_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, question.getDescription());
            ps.setString(2, question.getAlternativeA());
            ps.setString(3, question.getAlternativeB());
            ps.setString(4, question.getAlternativeC());
            ps.setString(5, question.getAlternativeD());
            ps.setString(6, question.getCorrectAlternative());
            ps.setInt(7, question.getSubjectId());
            return ps;
        }, keyHolder);
        
        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
    }

    public int update(Question question) {
        String sql = "UPDATE question SET description = ?, alternative_a = ?, alternative_b = ?, " +
                    "alternative_c = ?, alternative_d = ?, correct_alternative = ?, subject_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, question.getDescription(), question.getAlternativeA(), 
                                 question.getAlternativeB(), question.getAlternativeC(), 
                                 question.getAlternativeD(), question.getCorrectAlternative(), 
                                 question.getSubjectId(), question.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM question WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
