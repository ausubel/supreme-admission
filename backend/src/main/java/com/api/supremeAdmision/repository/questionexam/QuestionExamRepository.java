package com.api.supremeAdmision.repository.questionexam;

import com.api.supremeAdmision.model.QuestionExam;
import com.api.supremeAdmision.repository.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionExamRepository extends JdbcRepository {

    @Autowired
    public QuestionExamRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<QuestionExam> questionExamRowMapper = (rs, rowNum) -> {
        QuestionExam questionExam = new QuestionExam();
        questionExam.setQuestionId(rs.getInt("question_id"));
        questionExam.setExamId(rs.getInt("exam_id"));
        questionExam.setOrderNumber(rs.getInt("order_number"));
        return questionExam;
    };

    public List<QuestionExam> findByExamId(int examId) {
        String sql = "SELECT * FROM question_exam WHERE exam_id = ? ORDER BY order_number";
        return jdbcTemplate.query(sql, questionExamRowMapper, examId);
    }

    public int deleteByExamId(int examId) {
        String sql = "DELETE FROM question_exam WHERE exam_id = ?";
        return jdbcTemplate.update(sql, examId);
    }

    public int deleteByAdmisionProcessId(int admisionProcessId) {
        String sql = "DELETE FROM question_exam WHERE exam_id IN (SELECT id FROM exam WHERE admision_process_id = ?)";
        return jdbcTemplate.update(sql, admisionProcessId);
    }

    public int save(QuestionExam questionExam) {
        String sql = "INSERT INTO question_exam (question_id, exam_id, order_number) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, questionExam.getQuestionId(), questionExam.getExamId(), questionExam.getOrderNumber());
    }

    public int update(QuestionExam questionExam) {
        String sql = "UPDATE question_exam SET order_number = ? WHERE question_id = ? AND exam_id = ?";
        return jdbcTemplate.update(sql, questionExam.getOrderNumber(), questionExam.getQuestionId(), questionExam.getExamId());
    }

    public int delete(int questionId, int examId) {
        String sql = "DELETE FROM question_exam WHERE question_id = ? AND exam_id = ?";
        return jdbcTemplate.update(sql, questionId, examId);
    }
}
