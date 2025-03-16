package com.api.supremeAdmision.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository extends JdbcRepository {

    public String insertDocument(String uuid, String base64Content) {
        return jdbcTemplate.queryForObject(
                "CALL insert_document(?, ?)",
                String.class,
                uuid,
                base64Content);
    }
}
