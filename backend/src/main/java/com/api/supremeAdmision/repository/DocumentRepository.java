package com.api.supremeAdmision.repository;

import org.springframework.stereotype.Repository;
import java.sql.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class DocumentRepository extends JdbcRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(DocumentRepository.class);

    public String insertDocument(String uuid, String base64Content) {
        return jdbcTemplate.queryForObject(
                "CALL insert_document(?, ?)",
                String.class,
                uuid,
                base64Content);
    }

    public int save(String uuid, String base64Content, Date registrationDate) {
        logger.info("Guardando documento con UUID: {}", uuid);
        String sql = "INSERT INTO document (uuid, base64_content, registration_date) VALUES (?, ?, ?)"; 
        return jdbcTemplate.update(sql, uuid, base64Content, registrationDate);
    }

    public String findContentById(String uuid) {
        logger.info("Buscando contenido del documento con UUID: {}", uuid);
        String sql = "SELECT base64_content FROM document WHERE uuid = ?"; 
        try {
            return jdbcTemplate.queryForObject(sql, String.class, uuid);
        } catch (Exception e) {
            logger.error("Error al buscar documento con UUID: {}", uuid, e);
            return null;
        }
    }
}
