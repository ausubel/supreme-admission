package com.api.supremeAdmision.repository.exam;

import com.api.supremeAdmision.model.Exam;
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
public class ExamRepository extends JdbcRepository {

    @Autowired
    public ExamRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Exam> examRowMapper = (rs, rowNum) -> {
        Exam exam = new Exam();
        exam.setId(rs.getInt("id"));
        exam.setType(rs.getString("type"));
        exam.setDocumentId(rs.getString("document_id"));
        exam.setAreaId(rs.getString("area_id"));
        exam.setAdmisionProcessId(rs.getInt("admision_process_id"));
        return exam;
    };

    public List<Exam> findByAdmisionProcessId(int admisionProcessId) {
        String sql = "SELECT * FROM exam WHERE admision_process_id = ?";
        return jdbcTemplate.query(sql, examRowMapper, admisionProcessId);
    }

    public Exam findByTypeAndAreaIdAndAdmisionProcessId(String type, String areaId, int admisionProcessId) {
        String sql = "SELECT * FROM exam WHERE type = ? AND area_id = ? AND admision_process_id = ?";
        List<Exam> exams = jdbcTemplate.query(sql, examRowMapper, type, areaId, admisionProcessId);
        return exams.isEmpty() ? null : exams.get(0);
    }

    public List<Exam> findAll() {
        String sql = "SELECT * FROM exam";
        return jdbcTemplate.query(sql, examRowMapper);
    }

    public Exam findById(int id) {
        String sql = "SELECT * FROM exam WHERE id = ?";
        List<Exam> exams = jdbcTemplate.query(sql, examRowMapper, id);
        return exams.isEmpty() ? null : exams.get(0);
    }

    public int save(Exam exam) {
        String sql = "INSERT INTO exam (type, document_id, area_id, admision_process_id) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, exam.getType());
            ps.setString(2, exam.getDocumentId());
            ps.setString(3, exam.getAreaId());
            ps.setInt(4, exam.getAdmisionProcessId());
            return ps;
        }, keyHolder);
        
        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
    }

    public int update(Exam exam) {
        String sql = "UPDATE exam SET type = ?, document_id = ?, area_id = ?, admision_process_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, exam.getType(), exam.getDocumentId(), exam.getAreaId(), 
                                 exam.getAdmisionProcessId(), exam.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM exam WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Exam> getAllExams() {
        return jdbcTemplate.query("CALL get_exams()", examRowMapper);
    }
}
