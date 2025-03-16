import React, { useState, useEffect } from 'react';
import { FileCheck, AlertCircle } from 'lucide-react';
import { v4 as uuidv4 } from 'uuid';
import { Button } from '../components/Button';
import type { StudentForm, FileUpload, DocumentType } from '../types';
import Swal from 'sweetalert2';
import { Toaster } from 'react-hot-toast';

interface Department {
  id: number;
  name: string;
}

interface Province {
  id: number;
  name: string;
  departmentId: number;
}

interface District {
  id: number;
  name: string;
  provinceId: number;
}

interface Area {
  id: string;
  name: string;
}

interface Career {
  id: number;
  name: string;
  facultyName: string;
  areaId: string;
}

interface IdentificationType {
  id: number;
  name: string;
}

const initialFormState: StudentForm = {
  nombres: '',
  apellidoPaterno: '',
  apellidoMaterno: '',
  tipoDocumento: '',
  numeroDocumento: '',
  fechaNacimiento: '',
  edad: 0,
  estadoCivil: 'Soltero',
  numeroHijos: 0,
  sexo: 'M',
  procedencia: '',
  tipoInstitucionOrigen: 'P',
  areaAcademica: '',
  escuelaProfesional: '',
  lugarNacimientoId: 0
};

const documentTypes: DocumentType[] = [
  {
    id: 'dni',
    title: 'Documento Nacional de Identidad',
    description: '(obligatorio*)',
  },
  {
    id: 'certificado',
    title: 'Certificado de estudios del 1ro al 5to año de secundaria refrendado por UGEL',
    description: '(obligatorio*)',
  },
  {
    id: 'resolucion',
    title: 'Resolución o constancia de haber ocupado uno de los dos (2) primeros puestos en el orden de mérito en educación secundaria regular',
    description: '(obligatorio*)',
  },
  {
    id: 'declaracionPenales',
    title: 'Declaración Jurada de no tener antecedentes penales',
    description: '(obligatorio*)',
  },
  {
    id: 'declaracionVeracidad',
    title: 'Declaración Jurada de veracidad de la documentación',
    description: '(obligatorio*)',
  },
];

const paises = [
  'Perú',
  'Argentina',
  'Venezuela',
  'Colombia'
];

const Registration: React.FC = () => {
  const [form, setForm] = useState<StudentForm>(initialFormState);
  const [files, setFiles] = useState<FileUpload[]>([]);
  const [departments, setDepartments] = useState<Department[]>([]);
  const [provinces, setProvinces] = useState<Province[]>([]);
  const [districts, setDistricts] = useState<District[]>([]);
  const [areas, setAreas] = useState<Area[]>([]);
  const [careers, setCareers] = useState<Career[]>([]);
  const [filteredCareers, setFilteredCareers] = useState<Career[]>([]);
  const [selectedDepartment, setSelectedDepartment] = useState<number | null>(null);
  const [selectedProvince, setSelectedProvince] = useState<number | null>(null);
  const [selectedDistrict, setSelectedDistrict] = useState<number | null>(null);
  const [selectedArea, setSelectedArea] = useState<string>('');
  const [identificationTypes, setIdentificationTypes] = useState<IdentificationType[]>([]);
  const [isLoading, setIsLoading] = useState({
    departments: false,
    provinces: false,
    districts: false,
    areas: false,
    careers: false,
    identificationTypes: false
  });

  // Cargar departamentos al iniciar
  useEffect(() => {
    const fetchDepartments = async () => {
      setIsLoading(prev => ({ ...prev, departments: true }));
      try {
        const response = await fetch('http://localhost:8080/api/departments');
        if (!response.ok) {
          throw new Error('Error al cargar departamentos');
        }
        const data = await response.json();
        setDepartments(data);
      } catch (error) {
        console.error('Error:', error);
        Swal.fire({
          icon: 'error',
          title: 'Error al cargar departamentos',
          confirmButtonText: 'Aceptar'
        });
      } finally {
        setIsLoading(prev => ({ ...prev, departments: false }));
      }
    };

    fetchDepartments();
  }, []);

  // Cargar provincias cuando se selecciona un departamento
  useEffect(() => {
    if (!selectedDepartment) {
      setProvinces([]);
      return;
    }

    const fetchProvinces = async () => {
      setIsLoading(prev => ({ ...prev, provinces: true }));
      try {
        const response = await fetch('http://localhost:8080/api/provinces');
        if (!response.ok) {
          throw new Error('Error al cargar provincias');
        }
        const data = await response.json();
        // Filtrar provincias por departamento seleccionado
        const filteredProvinces = data.filter(
          (province: Province) => province.departmentId === selectedDepartment
        );
        setProvinces(filteredProvinces);
      } catch (error) {
        console.error('Error:', error);
        Swal.fire({
          icon: 'error',
          title: 'Error al cargar provincias',
          confirmButtonText: 'Aceptar'
        });
      } finally {
        setIsLoading(prev => ({ ...prev, provinces: false }));
      }
    };

    fetchProvinces();
  }, [selectedDepartment]);

  // Cargar distritos cuando se selecciona una provincia
  useEffect(() => {
    if (!selectedProvince) {
      setDistricts([]);
      return;
    }

    const fetchDistricts = async () => {
      setIsLoading(prev => ({ ...prev, districts: true }));
      try {
        const response = await fetch('http://localhost:8080/api/districts');
        if (!response.ok) {
          throw new Error('Error al cargar distritos');
        }
        const data = await response.json();
        // Filtrar distritos por provincia seleccionada
        const filteredDistricts = data.filter(
          (district: District) => district.provinceId === selectedProvince
        );
        setDistricts(filteredDistricts);
      } catch (error) {
        console.error('Error:', error);
        Swal.fire({
          icon: 'error',
          title: 'Error al cargar distritos',
          confirmButtonText: 'Aceptar'
        });
      } finally {
        setIsLoading(prev => ({ ...prev, districts: false }));
      }
    };

    fetchDistricts();
  }, [selectedProvince]);

  // Cargar áreas académicas al iniciar
  useEffect(() => {
    const fetchAreas = async () => {
      setIsLoading(prev => ({ ...prev, areas: true }));
      try {
        // Definimos las áreas manualmente ya que son pocas y fijas
        const areasData: Area[] = [
          { id: 'A', name: 'CIENCIAS DE LA SALUD' },
          { id: 'B', name: 'CIENCIAS SOCIALES Y HUMANIDADES' },
          { id: 'C', name: 'CIENCIAS E INGENIERÍA' }
        ];
        setAreas(areasData);
      } catch (error) {
        console.error('Error:', error);
        Swal.fire({
          icon: 'error',
          title: 'Error al cargar áreas académicas',
          confirmButtonText: 'Aceptar'
        });
      } finally {
        setIsLoading(prev => ({ ...prev, areas: false }));
      }
    };

    fetchAreas();
  }, []);

  // Cargar todas las carreras al iniciar
  useEffect(() => {
    const fetchCareers = async () => {
      setIsLoading(prev => ({ ...prev, careers: true }));
      try {
        const response = await fetch('http://localhost:8080/api/careers');
        if (!response.ok) {
          throw new Error('Error al cargar carreras');
        }
        const data = await response.json();
        setCareers(data);
      } catch (error) {
        console.error('Error:', error);
        Swal.fire({
          icon: 'error',
          title: 'Error al cargar carreras',
          confirmButtonText: 'Aceptar'
        });
      } finally {
        setIsLoading(prev => ({ ...prev, careers: false }));
      }
    };

    fetchCareers();
  }, []);

  // Cargar tipos de documento
  useEffect(() => {
    const fetchDocumentTypes = async () => {
      setIsLoading(prev => ({ ...prev, identificationTypes: true }));
      try {
        const response = await fetch('http://localhost:8080/api/identification-types');
        if (response.ok) {
          const data = await response.json();
          setIdentificationTypes(data);
        } else {
          console.error('Error al cargar tipos de documento');
        }
      } catch (error) {
        console.error('Error:', error);
      } finally {
        setIsLoading(prev => ({ ...prev, identificationTypes: false }));
      }
    };

    fetchDocumentTypes();
  }, []);

  // Filtrar carreras cuando se selecciona un área
  useEffect(() => {
    if (!selectedArea) {
      setFilteredCareers([]);
      return;
    }

    const filtered = careers.filter(career => career.areaId === selectedArea);
    setFilteredCareers(filtered);
    
    // Limpiar la selección de carrera si el área cambia
    setForm(prev => ({ ...prev, escuelaProfesional: '' }));
  }, [selectedArea, careers]);

  // Función para convertir un archivo a base64
  const convertFileToBase64 = (file: File): Promise<string> => {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        if (typeof reader.result === 'string') {
          // Eliminar el prefijo 'data:application/pdf;base64,' para obtener solo el contenido base64
          const base64Content = reader.result.split(',')[1];
          resolve(base64Content);
        } else {
          reject(new Error('Error al convertir el archivo a base64'));
        }
      };
      reader.onerror = error => reject(error);
    });
  };

  const handleDepartmentChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const departmentId = parseInt(e.target.value);
    setSelectedDepartment(departmentId || null);
    setSelectedProvince(null);
    setSelectedDistrict(null);
  };

  const handleProvinceChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const provinceId = parseInt(e.target.value);
    setSelectedProvince(provinceId || null);
    setSelectedDistrict(null);
  };

  const handleDistrictChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const districtId = parseInt(e.target.value);
    setSelectedDistrict(districtId || null);
    // Actualizar el formulario con el ID del distrito seleccionado
    setForm(prev => ({ ...prev, lugarNacimientoId: districtId || 0 }));
  };

  const handleAreaChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const areaId = e.target.value;
    setSelectedArea(areaId);
    setForm(prev => ({ ...prev, areaAcademica: areaId }));
  };

  const handleCareerChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const careerId = e.target.value;
    setForm(prev => ({ ...prev, escuelaProfesional: careerId }));
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
  };

  const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>, documentType: DocumentType) => {
    const file = e.target.files?.[0];
    if (!file) return;
    
    if (file.type !== 'application/pdf') {
      Swal.fire({
        icon: 'error',
        title: 'Solo se permiten archivos PDF',
        confirmButtonText: 'Aceptar'
      });
      return;
    }

    try {
      // Generar UUID para el documento
      const uuid = uuidv4();
      
      // Filtrar archivos existentes del mismo tipo
      setFiles(prev => prev.filter(f => f.type !== documentType.id));

      // Agregar el nuevo archivo a la lista sin enviarlo al backend
      const fileUpload: FileUpload = {
        id: uuid,
        name: file.name,
        file,
        type: documentType.id,
      };

      setFiles(prev => [...prev, fileUpload]);
      Swal.fire({
        icon: 'success',
        title: 'Archivo seleccionado correctamente',
        confirmButtonText: 'Aceptar'
      });
    } catch (error) {
      console.error('Error al procesar el archivo:', error);
      Swal.fire({
        icon: 'error',
        title: 'Error al procesar el archivo',
        confirmButtonText: 'Aceptar'
      });
    }
  };

  const getFileByType = (type: FileUpload['type']) => {
    return files.find(file => file.type === type);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    // Validar campos obligatorios del formulario
    const requiredFields = [
      { field: 'nombres', label: 'Nombres' },
      { field: 'apellidoPaterno', label: 'Apellido Paterno' },
      { field: 'apellidoMaterno', label: 'Apellido Materno' },
      { field: 'tipoDocumento', label: 'Tipo de Documento' },
      { field: 'numeroDocumento', label: 'Número de Documento' },
      { field: 'sexo', label: 'Sexo' },
      { field: 'fechaNacimiento', label: 'Fecha de Nacimiento' },
      { field: 'estadoCivil', label: 'Estado Civil' },
      { field: 'procedencia', label: 'País de Procedencia' },
      { field: 'lugarNacimientoId', label: 'Lugar de Nacimiento' },
      { field: 'tipoInstitucionOrigen', label: 'Tipo de Institución de Origen' },
      { field: 'areaAcademica', label: 'Área Académica' },
      { field: 'escuelaProfesional', label: 'Escuela Profesional' }
    ];
    
    const missingFields = requiredFields.filter(field => !form[field.field as keyof StudentForm]);
    
    if (missingFields.length > 0) {
      Swal.fire({
        icon: 'error',
        title: 'Campos obligatorios faltantes',
        text: `Por favor complete los siguientes campos: ${missingFields.map(f => f.label).join(', ')}`,
        confirmButtonText: 'Aceptar'
      });
      return;
    }
    
    // Validar documentos obligatorios
    const missingDocuments = documentTypes.filter(
      doc => !getFileByType(doc.id)
    );

    if (missingDocuments.length > 0) {
      Swal.fire({
        icon: 'error',
        title: 'Faltan documentos obligatorios',
        text: missingDocuments.map(d => d.title).join(', '),
        confirmButtonText: 'Aceptar'
      });
      return;
    }
    
    try {
      // Mostrar mensaje de carga
      Swal.fire({
        title: 'Procesando...',
        text: 'Subiendo documentos y enviando formulario',
        allowOutsideClick: false,
        didOpen: () => {
          Swal.showLoading();
        }
      });
      
      // Primero, subir todos los documentos al backend
      const documentPromises = files.map(async (fileUpload) => {
        // Convertir archivo a base64
        const base64Content = await convertFileToBase64(fileUpload.file);
        
        // Enviar el documento al backend
        const response = await fetch('http://localhost:8080/api/documents', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            uuid: fileUpload.id,
            base64Content
          }),
        });
        
        if (!response.ok) {
          throw new Error(`Error al guardar el documento ${fileUpload.name}`);
        }
        
        return fileUpload.id; // Devolver el UUID para usarlo en el formulario
      });
      
      // Esperar a que todos los documentos se suban
      await Promise.all(documentPromises);
      
      // Preparar los datos para enviar al endpoint
      const today = new Date().toISOString().split('T')[0];
      
      // Convertir valores a los tipos esperados por el backend
      const identificationTypeId = form.tipoDocumento ? parseInt(form.tipoDocumento) : null;
      const childrenCount = typeof form.numeroHijos === 'string' ? parseInt(form.numeroHijos) : form.numeroHijos || 0;
      const originDistrictId = typeof form.lugarNacimientoId === 'string' ? parseInt(form.lugarNacimientoId) : form.lugarNacimientoId || null;
      const careerId = form.escuelaProfesional ? parseInt(form.escuelaProfesional) : null;
      
      const enrollmentData = {
        name: form.nombres,
        paternalSurname: form.apellidoPaterno,
        maternalSurname: form.apellidoMaterno,
        identificationTypeId,
        identification: form.numeroDocumento,
        gender: form.sexo,
        birthDate: form.fechaNacimiento,
        civilStatus: form.estadoCivil === 'Soltero' ? 'S' : 
                    form.estadoCivil === 'Casado' ? 'C' : 
                    form.estadoCivil === 'Divorciado' ? 'D' : 'V',
        childrenCount,
        provenanceCountry: form.procedencia,
        registrationDate: today,
        originDistrictId,
        originInstitutionType: form.tipoInstitucionOrigen,
        // Asignar los UUIDs de los documentos
        dniAttachment: getFileByType('dni')?.id || '',
        studyCertificateAttachment: getFileByType('certificado')?.id || '',
        compromiseStudyCertificateAttachment: getFileByType('resolucion')?.id || '',
        criminalRecordAttachment: getFileByType('declaracionPenales')?.id || '',
        veracityDeclarationAttachment: getFileByType('declaracionVeracidad')?.id || '',
        careerId
      };

      console.log('Enviando datos:', enrollmentData);
      
      // Enviar los datos al endpoint
      const response = await fetch('http://localhost:8080/api/candidates/enroll', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(enrollmentData),
      });

      if (!response.ok) {
        const errorData = await response.text();
        throw new Error(errorData || 'Error al enviar el formulario');
      }

      const responseData = await response.text();
      console.log('Respuesta:', responseData);
      
      // Cerrar el mensaje de carga y mostrar éxito
      Swal.fire({
        icon: 'success',
        title: 'Formulario enviado correctamente',
        confirmButtonText: 'Aceptar'
      });
      
      // Limpiar el formulario después de enviar exitosamente
      setForm(initialFormState);
      setFiles([]);
    } catch (error) {
      console.error('Error:', error);
      Swal.fire({
        icon: 'error',
        title: 'Error al enviar el formulario',
        text: error instanceof Error ? error.message : 'Error desconocido',
        confirmButtonText: 'Aceptar'
      });
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-50 py-8">
      <div className="max-w-4xl mx-auto px-4">
        <div className="bg-white rounded-xl shadow-lg p-6 md:p-8">
          <h1 className="text-2xl font-bold text-indigo-900 mb-6">
            Ficha de Postulación
          </h1>

          <form onSubmit={handleSubmit} className="space-y-6">
            <div className="bg-gradient-to-r from-indigo-500 to-blue-500 p-4 rounded-lg">
              <h2 className="text-white font-semibold mb-4">Datos Personales</h2>
              <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Nombres <span className="text-red-300">*</span>
                  </label>
                  <input
                    type="text"
                    name="nombres"
                    value={form.nombres}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Apellido Paterno <span className="text-red-300">*</span>
                  </label>
                  <input
                    type="text"
                    name="apellidoPaterno"
                    value={form.apellidoPaterno}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Apellido Materno <span className="text-red-300">*</span>
                  </label>
                  <input
                    type="text"
                    name="apellidoMaterno"
                    value={form.apellidoMaterno}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Tipo de Documento <span className="text-red-300">*</span>
                  </label>
                  <select
                    name="tipoDocumento"
                    value={form.tipoDocumento || ''}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  >
                    <option value="">Seleccione...</option>
                    {identificationTypes.map((type) => (
                      <option key={type.id} value={type.id}>
                        {type.name}
                      </option>
                    ))}
                  </select>
                </div>
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Número de Documento <span className="text-red-300">*</span>
                  </label>
                  <input
                    type="text"
                    name="numeroDocumento"
                    value={form.numeroDocumento || ''}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Sexo <span className="text-red-300">*</span>
                  </label>
                  <select
                    name="sexo"
                    value={form.sexo}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  >
                    <option value="">Seleccione...</option>
                    <option value="M">Masculino</option>
                    <option value="F">Femenino</option>
                  </select>
                </div>
              </div>

              <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mt-6">
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Fecha de Nacimiento <span className="text-red-300">*</span>
                  </label>
                  <input
                    type="date"
                    name="fechaNacimiento"
                    value={form.fechaNacimiento}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Estado Civil <span className="text-red-300">*</span>
                  </label>
                  <select
                    name="estadoCivil"
                    value={form.estadoCivil}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  >
                    <option value="Soltero">Soltero</option>
                    <option value="Casado">Casado</option>
                    <option value="Divorciado">Divorciado</option>
                    <option value="Viudo">Viudo</option>
                  </select>
                </div>
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Número de Hijos
                  </label>
                  <input
                    type="number"
                    name="numeroHijos"
                    value={form.numeroHijos}
                    onChange={handleInputChange}
                    min="0"
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    País de Procedencia <span className="text-red-300">*</span>
                  </label>
                  <select
                    name="procedencia"
                    value={form.procedencia}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  >
                    {paises.map((pais) => (
                      <option key={pais} value={pais}>
                        {pais}
                      </option>
                    ))}
                  </select>
                </div>
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Tipo de Institución de Origen <span className="text-red-300">*</span>
                  </label>
                  <select
                    name="tipoInstitucionOrigen"
                    value={form.tipoInstitucionOrigen}
                    onChange={handleInputChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  >
                    <option value="P">Pública</option>
                    <option value="E">Privada</option>
                  </select>
                </div>
              </div>

              <div className="mt-4 mb-4">
                <h3 className="text-white font-medium mb-4">Lugar de Nacimiento</h3>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                  <div>
                    <label className="block text-sm font-medium text-white mb-1">
                      Departamento <span className="text-red-300">*</span>
                    </label>
                    <select
                      value={selectedDepartment || ''}
                      onChange={handleDepartmentChange}
                      className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    >
                      <option value="">Seleccione un departamento</option>
                      {departments.map((department) => (
                        <option key={department.id} value={department.id}>
                          {department.name}
                        </option>
                      ))}
                    </select>
                    {isLoading.departments && <p className="text-white text-sm mt-1">Cargando departamentos...</p>}
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-white mb-1">
                      Provincia <span className="text-red-300">*</span>
                    </label>
                    <select
                      value={selectedProvince || ''}
                      onChange={handleProvinceChange}
                      className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                      disabled={!selectedDepartment || isLoading.provinces}
                    >
                      <option value="">Seleccione una provincia</option>
                      {provinces.map((province) => (
                        <option key={province.id} value={province.id}>
                          {province.name}
                        </option>
                      ))}
                    </select>
                    {isLoading.provinces && <p className="text-white text-sm mt-1">Cargando provincias...</p>}
                  </div>
                  <div>
                    <label className="block text-sm font-medium text-white mb-1">
                      Distrito <span className="text-red-300">*</span>
                    </label>
                    <select
                      value={selectedDistrict || ''}
                      onChange={handleDistrictChange}
                      className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                      disabled={!selectedProvince || isLoading.districts}
                    >
                      <option value="">Seleccione un distrito</option>
                      {districts.map((district) => (
                        <option key={district.id} value={district.id}>
                          {district.name}
                        </option>
                      ))}
                    </select>
                    {isLoading.districts && <p className="text-white text-sm mt-1">Cargando distritos...</p>}
                  </div>
                </div>
              </div>
            </div>

            <div className="bg-gradient-to-r from-purple-500 to-pink-500 p-4 rounded-lg mt-8">
              <h2 className="text-white font-semibold mb-4">Información Académica</h2>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Área Académica
                  </label>
                  <select
                    name="areaAcademica"
                    value={selectedArea}
                    onChange={handleAreaChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  >
                    <option value="">Seleccione un área</option>
                    {areas.map((area) => (
                      <option key={area.id} value={area.id}>
                        {area.name}
                      </option>
                    ))}
                  </select>
                  {isLoading.areas && <p className="text-white text-sm mt-1">Cargando áreas...</p>}
                </div>
                <div>
                  <label className="block text-sm font-medium text-white mb-1">
                    Escuela Profesional
                  </label>
                  <select
                    name="escuelaProfesional"
                    value={form.escuelaProfesional}
                    onChange={handleCareerChange}
                    className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    disabled={!selectedArea || isLoading.careers}
                  >
                    <option value="">Seleccione una escuela</option>
                    {filteredCareers.map((career) => (
                      <option key={career.id} value={career.id.toString()}>
                        {career.name}
                      </option>
                    ))}
                  </select>
                  {isLoading.careers && <p className="text-white text-sm mt-1">Cargando carreras...</p>}
                </div>
              </div>
              {form.escuelaProfesional && (
                <div className="col-span-full">
                  <label className="block text-sm font-medium text-white mb-1">
                    Facultad:
                  </label>
                  <p className="text-white bg-purple-600 px-3 py-2 rounded-md w-full">
                    {filteredCareers.find(career => career.id.toString() === form.escuelaProfesional)?.facultyName || ''}
                  </p>
                </div>
              )}
            </div>

            <div className="bg-gradient-to-r from-emerald-500 to-teal-500 p-4 rounded-lg mt-8">
              <h3 className="text-lg font-medium text-white mb-4">
                Lista de requisitos anexos
              </h3>
              
              <div className="space-y-4">
                {documentTypes.map((docType) => {
                  const uploadedFile = getFileByType(docType.id);
                  return (
                    <div key={docType.id} className="bg-white/90 backdrop-blur-sm border rounded-lg p-4">
                      <div className="flex flex-col space-y-2">
                        <h4 className="text-sm font-medium text-gray-900">
                          {docType.title} <span className="text-red-500">{docType.description}</span>
                        </h4>
                        
                        <div className="flex items-center gap-4">
                          <div className="flex-1">
                            {uploadedFile ? (
                              <div className="flex items-center gap-2 text-sm text-emerald-600">
                                <FileCheck className="h-5 w-5" />
                                <span>{uploadedFile.name}</span>
                              </div>
                            ) : (
                              <div className="flex items-center gap-2 text-sm text-gray-500">
                                <AlertCircle className="h-5 w-5" />
                                <span>Ningún archivo seleccionado</span>
                              </div>
                            )}
                          </div>
                          
                          <div className="relative">
                            <input
                              type="file"
                              onChange={(e) => handleFileChange(e, docType)}
                              accept=".pdf"
                              className="absolute inset-0 w-full h-full opacity-0 cursor-pointer"
                            />
                            <Button
                              type="button"
                              variant="outline"
                              size="sm"
                            >
                              Buscar
                            </Button>
                          </div>
                        </div>
                      </div>
                    </div>
                  );
                })}
              </div>
            </div>

            <div className="flex justify-end gap-4 mt-8">
              <Button
                type="button"
                variant="outline"
                onClick={() => {
                  setForm(initialFormState);
                  setFiles([]);
                }}
              >
                Limpiar
              </Button>
              <Button type="submit">
                Enviar Postulación
              </Button>
            </div>
          </form>
        </div>
      </div>
      <Toaster position="top-right" />
    </div>
  );
};

export default Registration;
