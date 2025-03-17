USE supreme_admision;

-- First, let's insert an active admission process if one doesn't exist
INSERT INTO admision_process (name, start_date, end_date, status)
SELECT 'Proceso de Admisión 2025-I', '2025-01-01', '2025-12-31', 1
WHERE NOT EXISTS (SELECT 1 FROM admision_process WHERE status = 1);

-- Get the ID of the active admission process
SET @active_process_id = (SELECT id FROM admision_process WHERE status = 1 LIMIT 1);

-- Insert exams for all areas and types
INSERT INTO exam (type, area_id, admision_process_id)
SELECT type, area_id, @active_process_id
FROM (
  SELECT 'H' AS type, 'A' AS area_id UNION
  SELECT 'I' AS type, 'A' AS area_id UNION
  SELECT 'J' AS type, 'A' AS area_id UNION
  SELECT 'K' AS type, 'B' AS area_id UNION
  SELECT 'L' AS type, 'B' AS area_id UNION
  SELECT 'M' AS type, 'B' AS area_id UNION
  SELECT 'N' AS type, 'C' AS area_id UNION
  SELECT 'O' AS type, 'C' AS area_id UNION
  SELECT 'P' AS type, 'C' AS area_id
) AS all_exams
WHERE NOT EXISTS (
  SELECT 1
  FROM exam
  WHERE type = all_exams.type AND area_id = all_exams.area_id AND admision_process_id = @active_process_id
);
