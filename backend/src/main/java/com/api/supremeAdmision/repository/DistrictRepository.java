package com.api.supremeAdmision.repository;

import com.api.supremeAdmision.model.District;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DistrictRepository extends JdbcRepository {

    public List<District> getDistricts() {
        try{
            return jdbcTemplate.query(
                    "CALL get_districts()",
                    new BeanPropertyRowMapper<>(District.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
