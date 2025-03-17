package com.api.supremeAdmision.repository.career;

import com.api.supremeAdmision.model.Career;
import com.api.supremeAdmision.repository.JdbcRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CareerRepository extends JdbcRepository {

    public List<Career> getCareers() {
        return jdbcTemplate.query(
                "CALL get_careers()",
                new CareerRowMapper());
    }
}
