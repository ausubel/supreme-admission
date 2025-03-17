USE supreme_admision;

-- Sample questions for BIOLOGÍA (subject_id = 1)
INSERT INTO question(description, alternative_a, alternative_b, alternative_c, alternative_d, correct_alternative, subject_id) VALUES
('¿Cuál es la unidad básica de la vida?', 'Célula', 'Átomo', 'Molécula', 'Tejido', 'A', 1),
('¿Qué organelo celular es responsable de la producción de energía?', 'Mitocondria', 'Núcleo', 'Ribosoma', 'Aparato de Golgi', 'A', 1),
('¿Qué proceso utilizan las plantas para producir su propio alimento?', 'Fotosíntesis', 'Respiración', 'Digestión', 'Excreción', 'A', 1),
('¿Cuál es la molécula que almacena la información genética?', 'ADN', 'ARN', 'Proteína', 'Lípido', 'A', 1),
('¿Qué tipo de células no tienen núcleo definido?', 'Procariotas', 'Eucariotas', 'Animales', 'Vegetales', 'A', 1),
('¿Cuál es el proceso de división celular para reproducción sexual?', 'Meiosis', 'Mitosis', 'Citocinesis', 'Cariocinesis', 'A', 1),
('¿Qué estructura celular controla qué sustancias entran y salen de la célula?', 'Membrana celular', 'Pared celular', 'Núcleo', 'Citoplasma', 'A', 1),
('¿Cuál es la teoría que explica el origen de las especies?', 'Evolución', 'Generación espontánea', 'Creacionismo', 'Lamarckismo', 'A', 1),
('¿Qué científico propuso la teoría de la evolución por selección natural?', 'Charles Darwin', 'Gregor Mendel', 'Louis Pasteur', 'Alexander Fleming', 'A', 1),
('¿Qué estructura celular contiene clorofila en las plantas?', 'Cloroplasto', 'Mitocondria', 'Vacuola', 'Ribosoma', 'A', 1);

-- Sample questions for QUÍMICA (subject_id = 2)
INSERT INTO question(description, alternative_a, alternative_b, alternative_c, alternative_d, correct_alternative, subject_id) VALUES
('¿Cuál es el elemento químico más abundante en el universo?', 'Hidrógeno', 'Oxígeno', 'Carbono', 'Nitrógeno', 'A', 2),
('¿Qué tipo de enlace se forma cuando se comparten electrones?', 'Covalente', 'Iónico', 'Metálico', 'Puente de hidrógeno', 'A', 2),
('¿Cuál es la fórmula química del agua?', 'H2O', 'CO2', 'NaCl', 'CH4', 'A', 2),
('¿Qué es un ácido según la teoría de Arrhenius?', 'Donador de protones', 'Aceptor de protones', 'Donador de electrones', 'Aceptor de electrones', 'A', 2),
('¿Cuál es el número atómico del carbono?', '6', '12', '14', '8', 'A', 2),
('¿Qué gas es responsable del efecto invernadero?', 'Dióxido de carbono', 'Oxígeno', 'Nitrógeno', 'Hidrógeno', 'A', 2),
('¿Qué tipo de reacción ocurre cuando se forma un precipitado?', 'Precipitación', 'Oxidación', 'Reducción', 'Neutralización', 'A', 2),
('¿Cuál es la unidad básica de la tabla periódica?', 'Elemento', 'Compuesto', 'Molécula', 'Átomo', 'A', 2);

-- Sample questions for FÍSICA (subject_id = 3)
INSERT INTO question(description, alternative_a, alternative_b, alternative_c, alternative_d, correct_alternative, subject_id) VALUES
('¿Cuál es la unidad de fuerza en el Sistema Internacional?', 'Newton', 'Joule', 'Watt', 'Pascal', 'A', 3),
('¿Qué ley establece que la energía no se crea ni se destruye, solo se transforma?', 'Primera ley de la termodinámica', 'Segunda ley de la termodinámica', 'Ley de Ohm', 'Ley de Coulomb', 'A', 3),
('¿Cuál es la fórmula de la segunda ley de Newton?', 'F = m·a', 'E = m·c²', 'V = I·R', 'P = m·g', 'A', 3),
('¿Qué científico formuló la ley de la gravitación universal?', 'Isaac Newton', 'Albert Einstein', 'Galileo Galilei', 'Niels Bohr', 'A', 3),
('¿Cuál es la unidad de potencia en el Sistema Internacional?', 'Watt', 'Joule', 'Newton', 'Voltio', 'A', 3),
('¿Qué tipo de ondas necesitan un medio material para propagarse?', 'Mecánicas', 'Electromagnéticas', 'De radio', 'Luminosas', 'A', 3),
('¿Cuál es la velocidad de la luz en el vacío?', '3 × 10⁸ m/s', '3 × 10⁶ m/s', '3 × 10⁴ m/s', '3 × 10² m/s', 'A', 3);

-- Sample questions for other subjects (4-18)
-- FILOSOFÍA (subject_id = 4)
INSERT INTO question(description, alternative_a, alternative_b, alternative_c, alternative_d, correct_alternative, subject_id) VALUES
('¿Quién es considerado el padre de la filosofía occidental?', 'Sócrates', 'Platón', 'Aristóteles', 'Tales de Mileto', 'A', 4),
('¿Qué filósofo propuso la teoría de las Ideas o Formas?', 'Platón', 'Aristóteles', 'Sócrates', 'Descartes', 'A', 4),
('¿Cuál es la frase célebre de Descartes?', 'Pienso, luego existo', 'Conócete a ti mismo', 'El hombre es la medida de todas las cosas', 'Solo sé que no sé nada', 'A', 4);

-- ECONOMÍA (subject_id = 5)
INSERT INTO question(description, alternative_a, alternative_b, alternative_c, alternative_d, correct_alternative, subject_id) VALUES
('¿Qué es la inflación?', 'Aumento generalizado de precios', 'Disminución del valor de la moneda', 'Aumento del desempleo', 'Disminución de la producción', 'A', 5),
('¿Qué estudia la microeconomía?', 'Comportamiento individual de agentes económicos', 'Economía global', 'Política monetaria', 'Balanza comercial', 'A', 5),
('¿Qué es el PBI?', 'Producto Bruto Interno', 'Producto Básico Internacional', 'Proceso Bancario Integral', 'Programa Básico de Inversión', 'A', 5);

-- Add sample questions for remaining subjects
INSERT INTO question(description, alternative_a, alternative_b, alternative_c, alternative_d, correct_alternative, subject_id) VALUES
-- GEOGRAFÍA (subject_id = 6)
('¿Cuál es el río más largo del mundo?', 'Nilo', 'Amazonas', 'Misisipi', 'Yangtsé', 'A', 6),
('¿Cuál es el país más grande del mundo por territorio?', 'Rusia', 'China', 'Estados Unidos', 'Canadá', 'A', 6),
('¿Cuál es la capital de Perú?', 'Lima', 'Cusco', 'Arequipa', 'Trujillo', 'A', 6),

-- HISTORIA UNIVERSAL (subject_id = 7)
('¿En qué año comenzó la Primera Guerra Mundial?', '1914', '1918', '1939', '1945', 'A', 7),
('¿Quién fue el primer emperador romano?', 'Augusto', 'Julio César', 'Nerón', 'Calígula', 'A', 7),
('¿Qué evento marcó el fin de la Edad Media?', 'Caída de Constantinopla', 'Descubrimiento de América', 'Revolución Francesa', 'Revolución Industrial', 'A', 7),

-- Add more subjects with at least the required number of questions for each
-- HISTORIA DEL PERÚ (subject_id = 8)
('¿Quién fue el último emperador inca?', 'Atahualpa', 'Huayna Cápac', 'Túpac Amaru', 'Manco Inca', 'A', 8),
('¿En qué año se proclamó la independencia del Perú?', '1821', '1824', '1811', '1830', 'A', 8),
('¿Quién fue el primer presidente del Perú?', 'José de San Martín', 'Simón Bolívar', 'Ramón Castilla', 'Andrés de Santa Cruz', 'A', 8);

-- Add questions for remaining subjects (9-18)
INSERT INTO question(description, alternative_a, alternative_b, alternative_c, alternative_d, correct_alternative, subject_id) VALUES
-- EDUCACIÓN CÍVICA (subject_id = 9)
('¿Cuántos poderes del Estado existen en Perú?', 'Tres', 'Cuatro', 'Dos', 'Cinco', 'A', 9),
('¿Qué documento establece los derechos fundamentales de los ciudadanos?', 'Constitución', 'Código Civil', 'Código Penal', 'Ley Orgánica', 'A', 9),
('¿Quién es el jefe de Estado en Perú?', 'Presidente de la República', 'Primer Ministro', 'Presidente del Congreso', 'Presidente del Poder Judicial', 'A', 9),

-- PERSONAL SOCIAL (PSICOLOGÍA) (subject_id = 10)
('¿Quién es considerado el padre del psicoanálisis?', 'Sigmund Freud', 'Carl Jung', 'Ivan Pavlov', 'B.F. Skinner', 'A', 10),
('¿Qué teoría propuso Jean Piaget?', 'Desarrollo cognitivo', 'Condicionamiento clásico', 'Condicionamiento operante', 'Psicoanálisis', 'A', 10),
('¿Qué estudia la psicología social?', 'Comportamiento en grupos', 'Desarrollo infantil', 'Trastornos mentales', 'Procesos cognitivos', 'A', 10),

-- LITERATURA (subject_id = 11)
('¿Quién escribió "Cien años de soledad"?', 'Gabriel García Márquez', 'Mario Vargas Llosa', 'Julio Cortázar', 'Pablo Neruda', 'A', 11),
('¿A qué movimiento literario pertenece César Vallejo?', 'Vanguardismo', 'Romanticismo', 'Realismo', 'Naturalismo', 'A', 11),
('¿Quién es el autor de "La ciudad y los perros"?', 'Mario Vargas Llosa', 'Gabriel García Márquez', 'Julio Cortázar', 'Jorge Luis Borges', 'A', 11),
('¿Qué obra escribió William Shakespeare?', 'Hamlet', 'Don Quijote', 'La Ilíada', 'La Divina Comedia', 'A', 11),

-- LENGUAJE (subject_id = 12)
('¿Qué es un sustantivo?', 'Palabra que nombra personas, animales o cosas', 'Palabra que indica acción', 'Palabra que califica al sustantivo', 'Palabra que reemplaza al sustantivo', 'A', 12),
('¿Qué es un adverbio?', 'Palabra que modifica al verbo', 'Palabra que nombra personas', 'Palabra que califica al sustantivo', 'Palabra que indica posesión', 'A', 12),
('¿Cuál es el plural de "lápiz"?', 'Lápices', 'Lapizes', 'Lápiz', 'Lapiz', 'A', 12),
('¿Qué es una oración?', 'Unidad de comunicación con sentido completo', 'Conjunto de palabras sin sentido', 'Palabra que indica acción', 'Signo de puntuación', 'A', 12);

-- Add questions for MATEMÁTICAS (13-16)
INSERT INTO question(description, alternative_a, alternative_b, alternative_c, alternative_d, correct_alternative, subject_id) VALUES
-- TRIGONOMETRÍA (subject_id = 13)
('¿Cuál es el valor de sen(30°)?', '0.5', '0', '1', '√3/2', 'A', 13),
('¿Cuál es la fórmula del teorema de Pitágoras?', 'a² + b² = c²', 'a + b = c', 'a × b = c', 'a/b = c', 'A', 13),
('¿Cuánto vale cos(0°)?', '1', '0', '0.5', '-1', 'A', 13),
('¿Qué relación trigonométrica es correcta?', 'sen²(θ) + cos²(θ) = 1', 'sen(θ) + cos(θ) = 1', 'tan(θ) = sen(θ) × cos(θ)', 'cot(θ) = sen(θ) / cos(θ)', 'A', 13),
('¿Cuál es el valor de tan(45°)?', '1', '0', '√3', '2', 'A', 13),
('¿Qué es un radián?', 'Unidad de medida de ángulos', 'Unidad de longitud', 'Unidad de área', 'Unidad de volumen', 'A', 13),

-- GEOMETRÍA (subject_id = 14)
('¿Cuál es la fórmula del área de un círculo?', 'πr²', '2πr', '4πr²', 'πr³', 'A', 14),
('¿Cuántos lados tiene un pentágono?', '5', '4', '6', '7', 'A', 14),
('¿Cuál es la suma de los ángulos internos de un triángulo?', '180°', '90°', '360°', '270°', 'A', 14),
('¿Qué es un poliedro?', 'Cuerpo geométrico con caras planas', 'Figura plana', 'Línea recta', 'Punto en el espacio', 'A', 14),
('¿Cuál es el volumen de un cubo de lado a?', 'a³', 'a²', '6a²', '4a³', 'A', 14),
('¿Qué figura tiene todos sus lados iguales y todos sus ángulos iguales?', 'Cuadrado', 'Rectángulo', 'Rombo', 'Trapecio', 'A', 14),

-- ÁLGEBRA (subject_id = 15)
('¿Cuál es la solución de x² - 5x + 6 = 0?', 'x = 2, x = 3', 'x = -2, x = -3', 'x = 2, x = -3', 'x = -2, x = 3', 'A', 15),
('¿Qué es un monomio?', 'Expresión algebraica con un solo término', 'Expresión con dos términos', 'Ecuación de primer grado', 'Función trigonométrica', 'A', 15),
('¿Cuál es el resultado de (a + b)²?', 'a² + 2ab + b²', 'a² + b²', 'a² - b²', '(a + b)(a - b)', 'A', 15),
('¿Qué son los números complejos?', 'Números de la forma a + bi', 'Números enteros', 'Números racionales', 'Números irracionales', 'A', 15),
('¿Cuál es la definición de una función?', 'Relación que asigna a cada elemento de un conjunto un único elemento de otro conjunto', 'Ecuación de segundo grado', 'Expresión con radicales', 'Polinomio de grado n', 'A', 15),
('¿Qué es una matriz?', 'Arreglo rectangular de números', 'Conjunto de puntos', 'Línea recta', 'Función trigonométrica', 'A', 15),

-- ARITMÉTICA (subject_id = 16)
('¿Cuál es el M.C.D. de 12 y 18?', '6', '12', '18', '36', 'A', 16),
('¿Qué es un número primo?', 'Número natural mayor que 1 que solo es divisible por 1 y por sí mismo', 'Número divisible por 2', 'Número menor que 0', 'Número racional', 'A', 16),
('¿Cuál es el resultado de 2³?', '8', '6', '9', '16', 'A', 16),
('¿Qué es una fracción?', 'Cociente de dos números enteros', 'Número decimal', 'Número irracional', 'Número complejo', 'A', 16),
('¿Cuál es el resultado de 20% de 50?', '10', '5', '25', '100', 'A', 16),
('¿Qué es un número racional?', 'Número que puede expresarse como fracción', 'Número que no puede expresarse como fracción', 'Número imaginario', 'Número natural', 'A', 16);

-- Add questions for HABILIDADES (17-18)
INSERT INTO question(description, alternative_a, alternative_b, alternative_c, alternative_d, correct_alternative, subject_id) VALUES
-- HABILIDAD VERBAL (subject_id = 17)
('¿Cuál es el sinónimo de "efímero"?', 'Pasajero', 'Duradero', 'Intenso', 'Profundo', 'A', 17),
('¿Cuál es el antónimo de "prolijo"?', 'Desordenado', 'Detallado', 'Extenso', 'Minucioso', 'A', 17),
('Complete la analogía: Libro es a leer como película es a...', 'Ver', 'Escuchar', 'Tocar', 'Oler', 'A', 17),
('¿Qué es una metáfora?', 'Figura retórica que identifica algo real con algo imaginario', 'Repetición de sonidos', 'Exageración', 'Contradicción de términos', 'A', 17),
('¿Cuál es el significado de "ambiguo"?', 'Que puede entenderse de varios modos', 'Claro y preciso', 'Antiguo', 'Moderno', 'A', 17),
('¿Qué es un texto argumentativo?', 'Texto que defiende una idea', 'Texto que narra una historia', 'Texto que describe algo', 'Texto que explica un proceso', 'A', 17),
('¿Cuál es el plural de "régimen"?', 'Regímenes', 'Régimenes', 'Régimens', 'Régimenes', 'A', 17),
('¿Qué es una oración subordinada?', 'Oración que depende de otra', 'Oración independiente', 'Oración sin verbo', 'Oración exclamativa', 'A', 17),
('¿Cuál es el género literario de "Don Quijote de la Mancha"?', 'Narrativo', 'Lírico', 'Dramático', 'Didáctico', 'A', 17),
('¿Qué figura literaria consiste en atribuir cualidades humanas a objetos inanimados?', 'Personificación', 'Metáfora', 'Hipérbole', 'Símil', 'A', 17),
('¿Cuál es el significado de "inherente"?', 'Que por su naturaleza está inseparablemente unido a algo', 'Que se puede separar', 'Que es opcional', 'Que es temporal', 'A', 17),
('¿Qué es un hiato?', 'Encuentro de dos vocales que se pronuncian en sílabas distintas', 'Encuentro de dos consonantes', 'Unión de dos palabras', 'Separación de una palabra', 'A', 17),
('¿Cuál es la función de los signos de puntuación?', 'Organizar el texto y facilitar su comprensión', 'Decorar el texto', 'Alargar el texto', 'Complicar la lectura', 'A', 17),
('¿Qué es un texto expositivo?', 'Texto que presenta información de manera objetiva', 'Texto que narra una historia', 'Texto que defiende una idea', 'Texto que describe algo', 'A', 17),
('¿Cuál es el significado de "paradoja"?', 'Idea que parece contradictoria pero puede ser cierta', 'Idea falsa', 'Idea obvia', 'Idea compleja', 'A', 17),
('¿Qué es una palabra aguda?', 'Palabra con acento en la última sílaba', 'Palabra con acento en la penúltima sílaba', 'Palabra con acento en la antepenúltima sílaba', 'Palabra sin acento', 'A', 17),
('¿Cuál es el significado de "elocuente"?', 'Que habla o se expresa con facilidad y eficacia', 'Que habla poco', 'Que habla con dificultad', 'Que no sabe hablar', 'A', 17),
('¿Qué es un texto narrativo?', 'Texto que cuenta una historia', 'Texto que describe algo', 'Texto que explica un proceso', 'Texto que defiende una idea', 'A', 17),
('¿Cuál es el significado de "tácito"?', 'Que no se expresa o no se dice pero se supone', 'Que se expresa claramente', 'Que es obvio', 'Que es falso', 'A', 17),
('¿Qué es un adjetivo?', 'Palabra que califica o determina al sustantivo', 'Palabra que indica acción', 'Palabra que sustituye al nombre', 'Palabra que modifica al verbo', 'A', 17),

-- HABILIDAD LÓGICO MATEMÁTICO (subject_id = 18)
('Si 3x + 2 = 14, ¿cuál es el valor de x?', '4', '3', '5', '6', 'A', 18),
('¿Cuál es el siguiente número en la secuencia: 2, 4, 8, 16, ...?', '32', '24', '20', '64', 'A', 18),
('Un tren recorre 240 km en 3 horas. ¿Cuál es su velocidad?', '80 km/h', '60 km/h', '120 km/h', '40 km/h', 'A', 18),
('Si a + b = 10 y a - b = 4, ¿cuánto vale a?', '7', '5', '6', '8', 'A', 18),
('¿Cuál es la probabilidad de obtener un número par al lanzar un dado?', '1/2', '1/3', '1/6', '2/3', 'A', 18),
('¿Cuántos minutos hay en 2 horas y media?', '150', '120', '180', '90', 'A', 18),
('Si un artículo cuesta S/80 y tiene un descuento del 25%, ¿cuánto se paga?', 'S/60', 'S/55', 'S/65', 'S/70', 'A', 18),
('¿Cuál es el área de un rectángulo de 6 m de largo y 4 m de ancho?', '24 m²', '20 m²', '10 m²', '30 m²', 'A', 18),
('Si 5 obreros construyen un muro en 12 días, ¿cuántos días tardarán 3 obreros?', '20', '15', '18', '24', 'A', 18),
('¿Cuál es el 20% de 350?', '70', '35', '7', '700', 'A', 18),
('Si un auto recorre 42 km con 3 litros de gasolina, ¿cuántos litros necesita para recorrer 98 km?', '7', '6', '8', '9', 'A', 18),
('¿Cuál es el resultado de (3 + 2) × 4 - 6 ÷ 2?', '17', '14', '16', '20', 'A', 18),
('Si a:b = 2:3 y b:c = 4:5, ¿cuál es la relación a:c?', '8:15', '2:5', '3:5', '4:15', 'A', 18),
('¿Cuántos triángulos hay en la figura: △△△?', '3', '6', '4', '5', 'A', 18),
('Si un tren sale a las 10:45 y llega a las 13:20, ¿cuánto dura el viaje?', '2 horas y 35 minutos', '2 horas y 25 minutos', '3 horas y 15 minutos', '2 horas y 45 minutos', 'A', 18),
('¿Cuál es la media aritmética de 15, 20, 25, 30 y 35?', '25', '20', '30', '22.5', 'A', 18),
('Si tengo 3/4 de un pastel y como 1/3 de lo que tengo, ¿qué fracción del pastel original me queda?', '1/2', '1/4', '2/3', '3/8', 'A', 18),
('¿Cuál es el mínimo común múltiplo de 6, 8 y 12?', '24', '12', '48', '36', 'A', 18),
('Si un reloj se adelanta 2 minutos cada hora, ¿cuánto se habrá adelantado en un día?', '48 minutos', '24 minutos', '60 minutos', '36 minutos', 'A', 18),
('¿Cuál es el número que sigue en la secuencia: 1, 4, 9, 16, 25, ...?', '36', '30', '49', '64', 'A', 18);
