import random
import uuid
from datetime import datetime, timedelta

def generate_canditates_inserts(num_inserts: int):
    # generar inserciones de estudiantes con insert de mysql
    '''
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
    '''
    # Load names and surnames from files
    with open('apellidos.txt', 'r', encoding='utf-8') as f:
        apellidos = [line.strip() for line in f if line.strip()]
    
    with open('nompres.txt', 'r', encoding='utf-8') as f:
        nombres_raw = [line.strip().split(',') for line in f if line.strip()]
        nombres = [(nombre, gender) for nombre, gender in nombres_raw]
    
    # Configuration parameters
    admision_process_id = 1
    civil_status_options = ['S', 'C', 'D']  # Single, Casado, Divorciado
    provenance_country = 'PERU'
    origin_institution_type_list = ['P', 'E']  # Privado, Estatal
    
    # Generate inserts
    inserts = []
    current_date = datetime.now().strftime('%Y-%m-%d')

    # Generate identification_union
    identification_union = []
    
    # Generate responses
    responses = []

    for i in range(num_inserts):
        # Select random name and gender
        nombre, gender = random.choice(nombres)
        
        # Select random surnames
        paternal_surname = random.choice(apellidos)
        maternal_surname = random.choice(apellidos)
        
        # Generate random values
        identification = f'{70000000 + i}'
        identification_type_id = 1  # Assuming 1 is for DNI
        birth_date = (datetime.now() - timedelta(days=random.randint(6570, 10950))).strftime('%Y-%m-%d')  # 18-30 years old
        civil_status = random.choice(civil_status_options)
        children_count = random.randint(0, 3)
        origin_district_id = random.randint(1, 162)
        origin_institution_type = random.choice(origin_institution_type_list)
        
        # Generate UUIDs for attachments
        dni_attachment = str(uuid.uuid4())
        study_certificate_attachment = str(uuid.uuid4())
        compromise_study_certificate_attachment = str(uuid.uuid4())
        criminal_record_attachment = str(uuid.uuid4())
        veracity_declaration_attachment = str(uuid.uuid4())
        
        career_id = random.randint(1, 41)
        area_id: str = 'A' if career_id <= 12 else ('B' if career_id <= 24 else 'C')
        '''
        'H' 'A'
        'I' 'A'
        'J' 'A'
        'K' 'B'
        'L' 'B'
        'M' 'B'
        'N' 'C'
        'O' 'C'
        'P' 'C'
        '''
        # exam_id depends on career_id
        exam_id: str
        if career_id <= 12:
            exam_id = random.choice(['H', 'I', 'J'])
        elif career_id <= 24:
            exam_id = random.choice(['K', 'L', 'M'])
        else:
            exam_id = random.choice(['N', 'O', 'P'])
        
        registration_date = current_date
        
        # Create SQL insert statement
        insert = f'''INSERT INTO candidate(
            name, maternal_surname, paternal_surname, identification_type_id, identification, gender, birth_date, 
            civil_status, children_count, provenance_country, registration_date, origin_district_id, 
            origin_institution_type, dni_attachment, study_certificate_attachment, 
            compromise_study_certificate_attachment, criminal_record_attachment, 
            veracity_declaration_attachment, career_id, admision_process_id
        ) VALUES (
            '{nombre}', '{maternal_surname}', '{paternal_surname}', {identification_type_id}, '{identification}', 
            '{gender}', '{birth_date}', '{civil_status}', {children_count}, '{provenance_country}', 
            '{registration_date}', {origin_district_id}, '{origin_institution_type}', 
            '{dni_attachment}', '{study_certificate_attachment}', '{compromise_study_certificate_attachment}', 
            '{criminal_record_attachment}', '{veracity_declaration_attachment}', {career_id}, 
            {admision_process_id}
        );'''
        
        inserts.append(insert)
        
        # Create format id
        format_id = f"{i:06d}"

        # Create responses como un string de 100 caracteres cada uno de la A,B,C,D
        response_keys = ''.join(random.choice(['A', 'B', 'C', 'D']) for _ in range(100))
        
        # Create identification union
        identification_union.append(f"{format_id}{exam_id}{identification}")
        
        # Create response
        responses.append(f"{format_id}{exam_id}{response_keys}")
    
    # Save inserts to file
    output_file_inserts = 'candidate_inserts.txt'
    with open(output_file_inserts, 'w', encoding='utf-8') as f:
        for insert in inserts:
            f.write(insert + '\n')

    # Save identification union to file
    output_file_id_union = '../data/identification_union.txt'
    with open(output_file_id_union, 'w', encoding='utf-8') as f:
        for id_union in identification_union:
            f.write(id_union + '\n')

    # Save responses to file
    output_file_responses = '../data/responses.txt'
    with open(output_file_responses, 'w', encoding='utf-8') as f:
        for response in responses:
            f.write(response + '\n')
    
    print(f'Generated {num_inserts} random candidate inserts and saved to {output_file_inserts}')
    print(f'Generated {num_inserts} random identification unions and saved to {output_file_id_union}')
    print(f'Generated {num_inserts} random responses and saved to {output_file_responses}')

def main():
    generate_canditates_inserts(100)

if __name__ == "__main__":
    main()
