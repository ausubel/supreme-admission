package com.api.supremeAdmision.repository;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.jdbc.core.JdbcTemplate;

public abstract class JdbcRepository {
    @Autowired
    protected JdbcTemplate jdbcTemplate;
}
