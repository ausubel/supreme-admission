package com.api.supremeAdmision.repository.admisionprocess;

import com.api.supremeAdmision.model.AdmisionProcess;
import com.api.supremeAdmision.repository.JdbcRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class AdmisionProcessRepository extends JdbcRepository {

    public List<AdmisionProcess> getAdmisionProcesses() {
        return jdbcTemplate.query(
                "CALL get_admision_processes()",
                new BeanPropertyRowMapper<>(AdmisionProcess.class));
    }
    
    public AdmisionProcess findActiveProcess() {
        String sql = "SELECT * FROM admision_process WHERE status = 1 LIMIT 1";
        List<AdmisionProcess> processes = jdbcTemplate.query(sql, 
                new BeanPropertyRowMapper<>(AdmisionProcess.class));
        return processes.isEmpty() ? null : processes.get(0);
    }
    
    public String startAdmisionProcess(String name, LocalDate startDate, LocalDate endDate) {
        Map<String, Object> result = jdbcTemplate.queryForMap(
                "CALL start_admision_process(?, ?, ?)",
                name, startDate, endDate);
        return (String) result.values().iterator().next();
    }
}
