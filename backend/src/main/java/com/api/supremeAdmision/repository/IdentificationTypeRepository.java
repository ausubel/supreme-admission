package com.api.supremeAdmision.repository;

import com.api.supremeAdmision.model.IdentificationType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IdentificationTypeRepository extends JdbcRepository {

    public List<IdentificationType> getIdentificationTypes() {
        return jdbcTemplate.query(
                "CALL get_identification_types()",
                new BeanPropertyRowMapper<>(IdentificationType.class));
    }
}
