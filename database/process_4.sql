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

DROP PROCEDURE IF EXISTS get_exam_keys//

CREATE PROCEDURE get_exam_keys()
BEGIN
    DECLARE current_admision_process_id INT;
    
    SELECT id INTO current_admision_process_id FROM admision_process WHERE status = 1 LIMIT 1;
    
    IF current_admision_process_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No hay un proceso de admisión activo';
    END IF;
    
    SELECT 
        e.type AS TEMA,
        MAX(CASE WHEN qe.order_number = 1 THEN q.correct_alternative ELSE NULL END) AS PREG_001,
        MAX(CASE WHEN qe.order_number = 2 THEN q.correct_alternative ELSE NULL END) AS PREG_002,
        MAX(CASE WHEN qe.order_number = 3 THEN q.correct_alternative ELSE NULL END) AS PREG_003,
        MAX(CASE WHEN qe.order_number = 4 THEN q.correct_alternative ELSE NULL END) AS PREG_004,
        MAX(CASE WHEN qe.order_number = 5 THEN q.correct_alternative ELSE NULL END) AS PREG_005,
        MAX(CASE WHEN qe.order_number = 6 THEN q.correct_alternative ELSE NULL END) AS PREG_006,
        MAX(CASE WHEN qe.order_number = 7 THEN q.correct_alternative ELSE NULL END) AS PREG_007,
        MAX(CASE WHEN qe.order_number = 8 THEN q.correct_alternative ELSE NULL END) AS PREG_008,
        MAX(CASE WHEN qe.order_number = 9 THEN q.correct_alternative ELSE NULL END) AS PREG_009,
        MAX(CASE WHEN qe.order_number = 10 THEN q.correct_alternative ELSE NULL END) AS PREG_010,
        MAX(CASE WHEN qe.order_number = 11 THEN q.correct_alternative ELSE NULL END) AS PREG_011,
        MAX(CASE WHEN qe.order_number = 12 THEN q.correct_alternative ELSE NULL END) AS PREG_012,
        MAX(CASE WHEN qe.order_number = 13 THEN q.correct_alternative ELSE NULL END) AS PREG_013,
        MAX(CASE WHEN qe.order_number = 14 THEN q.correct_alternative ELSE NULL END) AS PREG_014,
        MAX(CASE WHEN qe.order_number = 15 THEN q.correct_alternative ELSE NULL END) AS PREG_015,
        MAX(CASE WHEN qe.order_number = 16 THEN q.correct_alternative ELSE NULL END) AS PREG_016,
        MAX(CASE WHEN qe.order_number = 17 THEN q.correct_alternative ELSE NULL END) AS PREG_017,
        MAX(CASE WHEN qe.order_number = 18 THEN q.correct_alternative ELSE NULL END) AS PREG_018,
        MAX(CASE WHEN qe.order_number = 19 THEN q.correct_alternative ELSE NULL END) AS PREG_019,
        MAX(CASE WHEN qe.order_number = 20 THEN q.correct_alternative ELSE NULL END) AS PREG_020,
        MAX(CASE WHEN qe.order_number = 21 THEN q.correct_alternative ELSE NULL END) AS PREG_021,
        MAX(CASE WHEN qe.order_number = 22 THEN q.correct_alternative ELSE NULL END) AS PREG_022,
        MAX(CASE WHEN qe.order_number = 23 THEN q.correct_alternative ELSE NULL END) AS PREG_023,
        MAX(CASE WHEN qe.order_number = 24 THEN q.correct_alternative ELSE NULL END) AS PREG_024,
        MAX(CASE WHEN qe.order_number = 25 THEN q.correct_alternative ELSE NULL END) AS PREG_025,
        MAX(CASE WHEN qe.order_number = 26 THEN q.correct_alternative ELSE NULL END) AS PREG_026,
        MAX(CASE WHEN qe.order_number = 27 THEN q.correct_alternative ELSE NULL END) AS PREG_027,
        MAX(CASE WHEN qe.order_number = 28 THEN q.correct_alternative ELSE NULL END) AS PREG_028,
        MAX(CASE WHEN qe.order_number = 29 THEN q.correct_alternative ELSE NULL END) AS PREG_029,
        MAX(CASE WHEN qe.order_number = 30 THEN q.correct_alternative ELSE NULL END) AS PREG_030,
        MAX(CASE WHEN qe.order_number = 31 THEN q.correct_alternative ELSE NULL END) AS PREG_031,
        MAX(CASE WHEN qe.order_number = 32 THEN q.correct_alternative ELSE NULL END) AS PREG_032,
        MAX(CASE WHEN qe.order_number = 33 THEN q.correct_alternative ELSE NULL END) AS PREG_033,
        MAX(CASE WHEN qe.order_number = 34 THEN q.correct_alternative ELSE NULL END) AS PREG_034,
        MAX(CASE WHEN qe.order_number = 35 THEN q.correct_alternative ELSE NULL END) AS PREG_035,
        MAX(CASE WHEN qe.order_number = 36 THEN q.correct_alternative ELSE NULL END) AS PREG_036,
        MAX(CASE WHEN qe.order_number = 37 THEN q.correct_alternative ELSE NULL END) AS PREG_037,
        MAX(CASE WHEN qe.order_number = 38 THEN q.correct_alternative ELSE NULL END) AS PREG_038,
        MAX(CASE WHEN qe.order_number = 39 THEN q.correct_alternative ELSE NULL END) AS PREG_039,
        MAX(CASE WHEN qe.order_number = 40 THEN q.correct_alternative ELSE NULL END) AS PREG_040,
        MAX(CASE WHEN qe.order_number = 41 THEN q.correct_alternative ELSE NULL END) AS PREG_041,
        MAX(CASE WHEN qe.order_number = 42 THEN q.correct_alternative ELSE NULL END) AS PREG_042,
        MAX(CASE WHEN qe.order_number = 43 THEN q.correct_alternative ELSE NULL END) AS PREG_043,
        MAX(CASE WHEN qe.order_number = 44 THEN q.correct_alternative ELSE NULL END) AS PREG_044,
        MAX(CASE WHEN qe.order_number = 45 THEN q.correct_alternative ELSE NULL END) AS PREG_045,
        MAX(CASE WHEN qe.order_number = 46 THEN q.correct_alternative ELSE NULL END) AS PREG_046,
        MAX(CASE WHEN qe.order_number = 47 THEN q.correct_alternative ELSE NULL END) AS PREG_047,
        MAX(CASE WHEN qe.order_number = 48 THEN q.correct_alternative ELSE NULL END) AS PREG_048,
        MAX(CASE WHEN qe.order_number = 49 THEN q.correct_alternative ELSE NULL END) AS PREG_049,
        MAX(CASE WHEN qe.order_number = 50 THEN q.correct_alternative ELSE NULL END) AS PREG_050,
        MAX(CASE WHEN qe.order_number = 51 THEN q.correct_alternative ELSE NULL END) AS PREG_051,
        MAX(CASE WHEN qe.order_number = 52 THEN q.correct_alternative ELSE NULL END) AS PREG_052,
        MAX(CASE WHEN qe.order_number = 53 THEN q.correct_alternative ELSE NULL END) AS PREG_053,
        MAX(CASE WHEN qe.order_number = 54 THEN q.correct_alternative ELSE NULL END) AS PREG_054,
        MAX(CASE WHEN qe.order_number = 55 THEN q.correct_alternative ELSE NULL END) AS PREG_055,
        MAX(CASE WHEN qe.order_number = 56 THEN q.correct_alternative ELSE NULL END) AS PREG_056,
        MAX(CASE WHEN qe.order_number = 57 THEN q.correct_alternative ELSE NULL END) AS PREG_057,
        MAX(CASE WHEN qe.order_number = 58 THEN q.correct_alternative ELSE NULL END) AS PREG_058,
        MAX(CASE WHEN qe.order_number = 59 THEN q.correct_alternative ELSE NULL END) AS PREG_059,
        MAX(CASE WHEN qe.order_number = 60 THEN q.correct_alternative ELSE NULL END) AS PREG_060,
        MAX(CASE WHEN qe.order_number = 61 THEN q.correct_alternative ELSE NULL END) AS PREG_061,
        MAX(CASE WHEN qe.order_number = 62 THEN q.correct_alternative ELSE NULL END) AS PREG_062,
        MAX(CASE WHEN qe.order_number = 63 THEN q.correct_alternative ELSE NULL END) AS PREG_063,
        MAX(CASE WHEN qe.order_number = 64 THEN q.correct_alternative ELSE NULL END) AS PREG_064,
        MAX(CASE WHEN qe.order_number = 65 THEN q.correct_alternative ELSE NULL END) AS PREG_065,
        MAX(CASE WHEN qe.order_number = 66 THEN q.correct_alternative ELSE NULL END) AS PREG_066,
        MAX(CASE WHEN qe.order_number = 67 THEN q.correct_alternative ELSE NULL END) AS PREG_067,
        MAX(CASE WHEN qe.order_number = 68 THEN q.correct_alternative ELSE NULL END) AS PREG_068,
        MAX(CASE WHEN qe.order_number = 69 THEN q.correct_alternative ELSE NULL END) AS PREG_069,
        MAX(CASE WHEN qe.order_number = 70 THEN q.correct_alternative ELSE NULL END) AS PREG_070,
        MAX(CASE WHEN qe.order_number = 71 THEN q.correct_alternative ELSE NULL END) AS PREG_071,
        MAX(CASE WHEN qe.order_number = 72 THEN q.correct_alternative ELSE NULL END) AS PREG_072,
        MAX(CASE WHEN qe.order_number = 73 THEN q.correct_alternative ELSE NULL END) AS PREG_073,
        MAX(CASE WHEN qe.order_number = 74 THEN q.correct_alternative ELSE NULL END) AS PREG_074,
        MAX(CASE WHEN qe.order_number = 75 THEN q.correct_alternative ELSE NULL END) AS PREG_075,
        MAX(CASE WHEN qe.order_number = 76 THEN q.correct_alternative ELSE NULL END) AS PREG_076,
        MAX(CASE WHEN qe.order_number = 77 THEN q.correct_alternative ELSE NULL END) AS PREG_077,
        MAX(CASE WHEN qe.order_number = 78 THEN q.correct_alternative ELSE NULL END) AS PREG_078,
        MAX(CASE WHEN qe.order_number = 79 THEN q.correct_alternative ELSE NULL END) AS PREG_079,
        MAX(CASE WHEN qe.order_number = 80 THEN q.correct_alternative ELSE NULL END) AS PREG_080,
        MAX(CASE WHEN qe.order_number = 81 THEN q.correct_alternative ELSE NULL END) AS PREG_081,
        MAX(CASE WHEN qe.order_number = 82 THEN q.correct_alternative ELSE NULL END) AS PREG_082,
        MAX(CASE WHEN qe.order_number = 83 THEN q.correct_alternative ELSE NULL END) AS PREG_083,
        MAX(CASE WHEN qe.order_number = 84 THEN q.correct_alternative ELSE NULL END) AS PREG_084,
        MAX(CASE WHEN qe.order_number = 85 THEN q.correct_alternative ELSE NULL END) AS PREG_085,
        MAX(CASE WHEN qe.order_number = 86 THEN q.correct_alternative ELSE NULL END) AS PREG_086,
        MAX(CASE WHEN qe.order_number = 87 THEN q.correct_alternative ELSE NULL END) AS PREG_087,
        MAX(CASE WHEN qe.order_number = 88 THEN q.correct_alternative ELSE NULL END) AS PREG_088,
        MAX(CASE WHEN qe.order_number = 89 THEN q.correct_alternative ELSE NULL END) AS PREG_089,
        MAX(CASE WHEN qe.order_number = 90 THEN q.correct_alternative ELSE NULL END) AS PREG_090,
        MAX(CASE WHEN qe.order_number = 91 THEN q.correct_alternative ELSE NULL END) AS PREG_091,
        MAX(CASE WHEN qe.order_number = 92 THEN q.correct_alternative ELSE NULL END) AS PREG_092,
        MAX(CASE WHEN qe.order_number = 93 THEN q.correct_alternative ELSE NULL END) AS PREG_093,
        MAX(CASE WHEN qe.order_number = 94 THEN q.correct_alternative ELSE NULL END) AS PREG_094,
        MAX(CASE WHEN qe.order_number = 95 THEN q.correct_alternative ELSE NULL END) AS PREG_095,
        MAX(CASE WHEN qe.order_number = 96 THEN q.correct_alternative ELSE NULL END) AS PREG_096,
        MAX(CASE WHEN qe.order_number = 97 THEN q.correct_alternative ELSE NULL END) AS PREG_097,
        MAX(CASE WHEN qe.order_number = 98 THEN q.correct_alternative ELSE NULL END) AS PREG_098,
        MAX(CASE WHEN qe.order_number = 99 THEN q.correct_alternative ELSE NULL END) AS PREG_099,
        MAX(CASE WHEN qe.order_number = 100 THEN q.correct_alternative ELSE NULL END) AS PREG_100
    FROM 
        exam e
    LEFT JOIN 
        question_exam qe ON e.id = qe.exam_id
    LEFT JOIN 
        question q ON qe.question_id = q.id
    WHERE 
        e.admision_process_id = current_admision_process_id
    GROUP BY 
        e.type
    ORDER BY 
        e.type;
    
END //

DELIMITER ;
