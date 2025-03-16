export interface StudentForm {
  nombres: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  tipoDocumento: string;
  numeroDocumento: string;
  fechaNacimiento: string;
  edad: string | number;
  estadoCivil: 'Soltero' | 'Casado' | 'Divorciado' | 'Viudo';
  numeroHijos: string | number;
  sexo: 'M' | 'F' | 'N';
  procedencia: string;
  tipoInstitucionOrigen: 'P' | 'E';
  areaAcademica: string;
  escuelaProfesional: string;
  lugarNacimientoId: string | number;
}

export interface FileUpload {
  id: string;
  name: string;
  file: File;
  type: 'dni' | 'certificado' | 'resolucion' | 'declaracionPenales' | 'declaracionVeracidad';
}

export type DocumentType = {
  id: FileUpload['type'];
  title: string;
  description: string;
};