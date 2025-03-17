USE supreme_admision;

DELIMITER //

-- call start_admision_process('PROCESO ADMISIÓN 2025', '2025-02-01', '2025-06-01');

DROP PROCEDURE IF EXISTS start_admision_process//

CREATE PROCEDURE start_admision_process(
    p_name VARCHAR(100),
    p_start_date DATE,
    p_end_date DATE
)
BEGIN

    UPDATE admision_process SET status = 0 WHERE status = 1;

    INSERT INTO admision_process(name, start_date, end_date, status)
    VALUES(p_name, p_start_date, p_end_date, 1);

    SET @admision_process_id = LAST_INSERT_ID();

    INSERT INTO exam(type, document_id, area_id, admision_process_id)
    VALUES('H', NULL, 'A', @admision_process_id),
    ('I', NULL, 'A', @admision_process_id),
    ('J', NULL, 'A', @admision_process_id),
    ('K', NULL, 'B', @admision_process_id),
    ('L', NULL, 'B', @admision_process_id),
    ('M', NULL, 'B', @admision_process_id),
    ('N', NULL, 'C', @admision_process_id),
    ('O', NULL, 'C', @admision_process_id),
    ('P', NULL, 'C', @admision_process_id);

    SELECT "SUCCESS";
END //


DROP PROCEDURE IF EXISTS distribute_questions_for_area//

CREATE PROCEDURE distribute_questions_for_area(
    IN p_area_id VARCHAR(1),
    IN p_exam_type VARCHAR(1),
    IN p_admision_process_id INT
)
BEGIN
    DECLARE exam_id1 INT;
    
    SELECT id INTO exam_id1 FROM exam 
    WHERE type = p_exam_type AND area_id = p_area_id AND admision_process_id = p_admision_process_id;
    
    BEGIN
        DECLARE done INT DEFAULT FALSE;
        DECLARE subject_id INT;
        DECLARE quantity INT;
        DECLARE cur CURSOR FOR 
            SELECT c.subject_id, c.quantity 
            FROM config_question_exam_by_subject c
            INNER JOIN subject s ON c.subject_id = s.id
            ORDER BY c.subject_id;
        DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
        
        OPEN cur;
        
        read_loop: LOOP
            FETCH cur INTO subject_id, quantity;
            IF done THEN
                LEAVE read_loop;
            END IF;
            
            BEGIN
                DECLARE question_count INT;
                
                SELECT COUNT(1) INTO question_count FROM question WHERE subject_id = subject_id;
                
                IF question_count >= quantity THEN
                    INSERT INTO question_exam (question_id, exam_id)
                    SELECT q.id, exam_id1
                    FROM question q
                    WHERE q.subject_id = subject_id
                    ORDER BY RAND()
                    LIMIT quantity;
                END IF;
            END;
        END LOOP;
        
        CLOSE cur;
    END;
END //


DROP PROCEDURE IF EXISTS distribute_questions//

-- call distribute_questions();

CREATE PROCEDURE distribute_questions()
distribute_questions_label: BEGIN
    DECLARE current_admision_process_id INT;
    
    SELECT id INTO current_admision_process_id FROM admision_process WHERE status = 1 LIMIT 1;
    
    IF current_admision_process_id IS NULL THEN
        SELECT 'ERROR: No active admission process found' AS message;
        LEAVE distribute_questions_label;
    END IF;
    
    DELETE FROM question_exam
    WHERE exam_id IN (
        SELECT id FROM exam
        WHERE admision_process_id = current_admision_process_id
    );
    
    -- Area A exams (types H, I, J)
    CALL distribute_questions_for_area('A', 'H', current_admision_process_id);
    CALL distribute_questions_for_area('A', 'I', current_admision_process_id);
    CALL distribute_questions_for_area('A', 'J', current_admision_process_id);
    
    -- Area B exams (types K, L, M)
    CALL distribute_questions_for_area('B', 'K', current_admision_process_id);
    CALL distribute_questions_for_area('B', 'L', current_admision_process_id);
    CALL distribute_questions_for_area('B', 'M', current_admision_process_id);
    
    -- Area C exams (types N, O, P)
    CALL distribute_questions_for_area('C', 'N', current_admision_process_id);
    CALL distribute_questions_for_area('C', 'O', current_admision_process_id);
    CALL distribute_questions_for_area('C', 'P', current_admision_process_id);
    
    SELECT 'SUCCESS: Questions distributed successfully' AS message;
END //
