USE supreme_admision;

-- Verificar el proceso de admisión activo
SELECT * FROM admision_process WHERE status = 1;

-- Verificar todos los exámenes
SELECT * FROM exam;

-- Verificar cuántos exámenes hay por proceso de admisión
SELECT admision_process_id, COUNT(*) as exam_count 
FROM exam 
GROUP BY admision_process_id;

-- Verificar cuántas preguntas hay en total
SELECT COUNT(*) as question_count FROM question;

-- Verificar la distribución actual de preguntas por examen
SELECT exam_id, COUNT(*) as question_count 
FROM question_exam 
GROUP BY exam_id;

-- Verificar si hay restricciones de clave foránea que puedan estar causando problemas
SHOW CREATE TABLE question_exam;

-- Verificar si hay algún examen que no tenga preguntas asignadas
SELECT e.id, e.type, e.area_id, e.admision_process_id, COUNT(qe.question_id) as question_count
FROM exam e
LEFT JOIN question_exam qe ON e.id = qe.exam_id
GROUP BY e.id, e.type, e.area_id, e.admision_process_id;

-- Verificar si hay alguna pregunta que esté siendo usada en múltiples exámenes
SELECT question_id, COUNT(DISTINCT exam_id) as exam_count
FROM question_exam
GROUP BY question_id
HAVING exam_count > 1;

-- Verificar si hay algún problema con los números de orden
SELECT exam_id, MIN(order_number) as min_order, MAX(order_number) as max_order, COUNT(*) as total_questions
FROM question_exam
GROUP BY exam_id;
