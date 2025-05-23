USE mysql;

DROP DATABASE IF EXISTS supreme_admision;

CREATE DATABASE supreme_admision;
 
USE supreme_admision;

CREATE TABLE exam_count(
    id INT AUTO_INCREMENT PRIMARY KEY,
    exam_id INT NOT NULL,
    count INT NOT NULL
);

CREATE TABLE admision_process(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status TINYINT(1) NOT NULL
);

-- INSCRIPCIÓN
CREATE TABLE faculty(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

CREATE TABLE department(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE province(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department_id INT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department(id)
);

CREATE TABLE district(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(70) NOT NULL,
    province_id INT NOT NULL,
    FOREIGN KEY (province_id) REFERENCES province(id)
);

CREATE TABLE identification_type(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE area(
    id VARCHAR(1) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE career(
    id INT AUTO_INCREMENT PRIMARY KEY,
    area_id VARCHAR(1) NOT NULL,
    professional_school VARCHAR(100) NOT NULL,
    faculty_id INT NOT NULL,
    FOREIGN KEY (faculty_id) REFERENCES faculty(id),
    FOREIGN KEY (area_id) REFERENCES area(id)
);

CREATE TABLE document(
    uuid VARCHAR(36) NOT NULL,
    base64_content LONGTEXT NOT NULL,
    registration_date DATE NOT NULL,
    PRIMARY KEY (uuid)
);

CREATE TABLE candidate(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    maternal_surname VARCHAR(50) NOT NULL,
    paternal_surname VARCHAR(50) NOT NULL,
    identification_type_id INT NOT NULL,
    identification VARCHAR(100) NOT NULL,
    gender VARCHAR(1) NOT NULL,
    birth_date DATE NOT NULL,
    civil_status VARCHAR(1) NOT NULL,
    children_count INT NOT NULL,
    provenance_country VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL,
    origin_district_id INT NOT NULL,
    origin_institution_type VARCHAR(1) NOT NULL,
    dni_attachment VARCHAR(36) NOT NULL,
    study_certificate_attachment VARCHAR(36) NOT NULL,
    compromise_study_certificate_attachment VARCHAR(36) NULL,
    criminal_record_attachment VARCHAR(36) NOT NULL,
    veracity_declaration_attachment VARCHAR(36) NOT NULL,
    career_id INT NOT NULL,
    admision_process_id INT NOT NULL,
    FOREIGN KEY (career_id) REFERENCES career(id),
    FOREIGN KEY (origin_district_id) REFERENCES district(id),
    FOREIGN KEY (identification_type_id) REFERENCES identification_type(id),
    FOREIGN KEY (admision_process_id) REFERENCES admision_process(id)
);

-- EXÁMENES

CREATE TABLE discipline(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE subject(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    discipline_id INT NOT NULL,
    FOREIGN KEY (discipline_id) REFERENCES discipline(id)
);


CREATE TABLE config_question_exam_by_subject(
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    subject_id INT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject(id)
);


CREATE TABLE question(
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(100) NOT NULL,
    alternative_a VARCHAR(100) NOT NULL,
    alternative_b VARCHAR(100) NOT NULL,
    alternative_c VARCHAR(100) NOT NULL,
    alternative_d VARCHAR(100) NOT NULL,
    correct_alternative VARCHAR(1) NOT NULL,
    subject_id INT NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subject(id)
);

CREATE TABLE exam(
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(1) NOT NULL,
    document_id VARCHAR(36) NULL,
    area_id VARCHAR(1) NOT NULL,
    admision_process_id INT NOT NULL,
    FOREIGN KEY (area_id) REFERENCES area(id),
    FOREIGN KEY (admision_process_id) REFERENCES admision_process(id)
);

CREATE TABLE question_exam(
    question_id INT NOT NULL,
    exam_id INT NOT NULL,
    order_number INT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(id),
    FOREIGN KEY (exam_id) REFERENCES exam(id)
);

CREATE TABLE results(
    id INT AUTO_INCREMENT PRIMARY KEY,
    candidate_id INT NOT NULL,
    exam_id INT NOT NULL,
    score DECIMAL(5,3) NOT NULL,
    FOREIGN KEY (candidate_id) REFERENCES candidate(id),
    FOREIGN KEY (exam_id) REFERENCES exam(id)
);