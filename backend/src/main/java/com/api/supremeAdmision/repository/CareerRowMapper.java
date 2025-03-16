package com.api.supremeAdmision.repository;

import com.api.supremeAdmision.model.Career;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CareerRowMapper implements RowMapper<Career> {

    @Override
    public Career mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        Career career = new Career();
        
        // Usar los métodos setter con los tipos correctos
        career.setId(rs.getInt("id"));
        career.setName(rs.getString("name"));
        career.setFacultyName(rs.getString("facultyName"));
        career.setAreaId(rs.getString("areaId"));
        
        return career;
    }
}
