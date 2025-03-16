package com.api.supremeAdmision.repository;

import com.api.supremeAdmision.model.Province;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProvinceRepository extends JdbcRepository {

    public List<Province> getProvinces() {
        return jdbcTemplate.query(
                "CALL get_provinces()",
                new BeanPropertyRowMapper<>(Province.class));
    }
}
