package com.api.supremeAdmision.repository;

import com.api.supremeAdmision.model.Career;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CareerRepository extends JdbcRepository {

    public List<Career> getCareers() {
        return jdbcTemplate.query(
                "CALL get_careers()",
                new BeanPropertyRowMapper<>(Career.class));
    }
}
