package com.api.supremeAdmision.repository;

import com.api.supremeAdmision.model.AdmisionProcess;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdmisionProcessRepository extends JdbcRepository {

    public List<AdmisionProcess> getAdmisionProcesses() {
        return jdbcTemplate.query(
                "CALL get_admision_processes()",
                new BeanPropertyRowMapper<>(AdmisionProcess.class));
    }
}
