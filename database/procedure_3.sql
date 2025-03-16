USE supreme_admision;

DELIMITER //

DROP PROCEDURE IF EXISTS get_departments//
CREATE PROCEDURE get_departments()
BEGIN
    SELECT id, name FROM department;
END //

DROP PROCEDURE IF EXISTS get_provinces//
CREATE PROCEDURE get_provinces()
BEGIN
    SELECT id, name, department_id FROM province;
END //

DROP PROCEDURE IF EXISTS get_districts//
CREATE PROCEDURE get_districts()
BEGIN
    SELECT id, name, province_id FROM district;
END //

DROP PROCEDURE IF EXISTS get_careers//
CREATE PROCEDURE get_careers()
BEGIN
    SELECT c.id, professional_school AS name, f.name AS facultyName, a.id AS areaId
    FROM career c
    INNER JOIN faculty f ON c.faculty_id = f.id
    INNER JOIN area a ON c.area_id = a.id
    ORDER BY c.id;
END //

DROP PROCEDURE IF EXISTS get_identification_types;
CREATE PROCEDURE get_identification_types()
BEGIN
    SELECT id, name FROM identification_type;
END //

DROP PROCEDURE IF EXISTS get_admision_processes//
CREATE PROCEDURE get_admision_processes()
BEGIN
    SELECT id, name, start_date, end_date, status FROM admision_process;
END //

DROP PROCEDURE IF EXISTS insert_document//
CREATE PROCEDURE insert_document(
    p_uuid VARCHAR(36),
    p_base64_content LONGTEXT
)
BEGIN
    INSERT INTO document(uuid, base64_content, registration_date)
    VALUES(p_uuid, p_base64_content, NOW());

    SELECT "SUCCESS";
END //

DROP PROCEDURE IF EXISTS enroll_candidate//
CREATE PROCEDURE enroll_candidate(
    p_name VARCHAR(50),
    p_maternal_surname VARCHAR(50),
    p_paternal_surname VARCHAR(50),
    p_identification_type_id INT,
    p_identification VARCHAR(100),
    p_gender VARCHAR(1),
    p_birth_date DATE,
    p_civil_status VARCHAR(1),
    p_children_count INT,
    p_provenance_country VARCHAR(100),
    p_registration_date DATE,
    p_origin_district_id INT,
    p_origin_institution_type VARCHAR(1),
    p_dni_attachment VARCHAR(36),
    p_study_certificate_attachment VARCHAR(36),
    p_compromise_study_certificate_attachment VARCHAR(36),
    p_criminal_record_attachment VARCHAR(36),
    p_veracity_declaration_attachment VARCHAR(36),
    p_career_id INT
)
BEGIN
    DECLARE admision_process_id INT;

    -- Obtener el ID del proceso de admisión activo
    SELECT id INTO admision_process_id FROM admision_process WHERE status = 1 LIMIT 1;
    
    -- Insertar el candidato en la base de datos
    INSERT INTO candidate(
        name, maternal_surname, paternal_surname, identification_type_id, identification, gender, birth_date, 
        civil_status, children_count, provenance_country, registration_date, origin_district_id, 
        origin_institution_type, dni_attachment, study_certificate_attachment, 
        compromise_study_certificate_attachment, criminal_record_attachment, 
        veracity_declaration_attachment, career_id, admision_process_id
    ) VALUES (
        p_name, p_maternal_surname, p_paternal_surname, p_identification_type_id, p_identification, 
        p_gender, p_birth_date, p_civil_status, p_children_count, p_provenance_country, 
        p_registration_date, p_origin_district_id, p_origin_institution_type, 
        p_dni_attachment, p_study_certificate_attachment, p_compromise_study_certificate_attachment, 
        p_criminal_record_attachment, p_veracity_declaration_attachment, p_career_id, 
        admision_process_id
    );

    -- Retornar un mensaje de éxito
    SELECT "SUCCESS" AS message;
END //
