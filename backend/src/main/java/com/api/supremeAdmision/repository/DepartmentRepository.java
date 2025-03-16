package com.api.supremeAdmision.repository;

import com.api.supremeAdmision.model.Department;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository extends JdbcRepository {

    public List<Department> getDepartments() {
        return jdbcTemplate.query(
                "CALL get_departments()",
                new BeanPropertyRowMapper<>(Department.class));
    }
}
