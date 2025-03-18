import React, { useState, useEffect } from 'react';
import { Button } from '../components/Button';
import axios from 'axios';
import { Download, FileText } from 'lucide-react';

interface Exam {
  id: number;
  type: string;
  documentId: string | null;
  areaId: string;
  admisionProcessId: number;
}


const ExamManagement: React.FC = () => {
  const [exams, setExams] = useState<Exam[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>('');
  const [success, setSuccess] = useState<string>('');
  const [generatingPdf, setGeneratingPdf] = useState<{[key: number]: boolean}>({});

  useEffect(() => {
    fetchExams();
  }, []);

  const fetchExams = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await axios.get<Exam[]>('http://localhost:8080/api/exams');
      setExams(response.data);
    } catch (err) {
      console.error('Error fetching exams:', err);
      setError('Error al cargar los exámenes. Por favor, intente nuevamente.');
    } finally {
      setLoading(false);
    }
  };

  

  const handleGeneratePdf = async (examId: number) => {
    setGeneratingPdf(prev => ({ ...prev, [examId]: true }));
    setError('');
    setSuccess('');
    try {
      await axios.post(`http://localhost:8080/api/exams/${examId}/generate-pdf`);
      setSuccess(`PDF generado exitosamente para el examen ID: ${examId}`);
      // Refresh the exam list to get updated documentId
      await fetchExams();
    } catch (err) {
      console.error(`Error generating PDF for exam ID: ${examId}`, err);
      setError(`Error al generar PDF para el examen ID: ${examId}. Por favor, intente nuevamente.`);
    } finally {
      setGeneratingPdf(prev => ({ ...prev, [examId]: false }));
    }
  };

  const handleDownloadPdf = async (examId: number) => {
    try {
      const response = await axios.get(`http://localhost:8080/api/exams/${examId}/download-pdf`, {
        responseType: 'blob'
      });
      
      // Create a blob URL for the PDF
      const blob = new Blob([response.data], { type: 'application/pdf' });
      const url = window.URL.createObjectURL(blob);
      
      // Create a temporary link and trigger download
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', `examen-${examId}.pdf`);
      document.body.appendChild(link);
      link.click();
      
      // Clean up
      document.body.removeChild(link);
      window.URL.revokeObjectURL(url);
    } catch (err) {
      console.error(`Error downloading PDF for exam ID: ${examId}`, err);
      setError(`Error al descargar PDF para el examen ID: ${examId}. Por favor, intente nuevamente.`);
    }
  };

  const handleDownloadExamKeysCSV = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/exam-keys/export/csv', {
        responseType: 'blob'
      });
      
      // Create a blob URL for the CSV
      const blob = new Blob([response.data], { type: 'text/csv' });
      const url = window.URL.createObjectURL(blob);
      
      // Create a temporary link and trigger download
      const link = document.createElement('a');
      link.href = url;
      const timestamp = new Date().toISOString().replace(/[:.]/g, '-').substring(0, 19);
      link.setAttribute('download', `claves_examen_${timestamp}.csv`);
      document.body.appendChild(link);
      link.click();
      
      // Clean up
      document.body.removeChild(link);
      window.URL.revokeObjectURL(url);
      
      setSuccess('Claves de exámenes descargadas exitosamente');
    } catch (err) {
      console.error('Error downloading exam keys CSV:', err);
      setError('Error al descargar las claves de exámenes. Por favor, intente nuevamente.');
    }
  };

  const getAreaName = (areaId: string) => {
    switch (areaId) {
      case 'A': return 'CIENCIAS DE LA SALUD';
      case 'B': return 'CIENCIAS SOCIALES Y HUMANIDADES';
      case 'C': return 'CIENCIAS E INGENIERÍA';
      default: return areaId;
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-50 p-6">
      <div className="max-w-6xl mx-auto bg-white rounded-xl shadow-lg p-8">
        <h1 className="text-3xl font-bold text-indigo-900 mb-6">Gestión de Exámenes</h1>
        
        {loading && (
          <div className="flex justify-center my-8">
            <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-indigo-500"></div>
          </div>
        )}
        
        {error && (
          <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
            {error}
          </div>
        )}
        
        {success && (
          <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
            {success}
          </div>
        )}
        
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white border border-gray-200 rounded-lg overflow-hidden">
            <thead className="bg-gray-100">
              <tr>
                <th className="py-3 px-4 text-left text-sm font-medium text-gray-700">ID</th>
                <th className="py-3 px-4 text-left text-sm font-medium text-gray-700">Tipo</th>
                <th className="py-3 px-4 text-left text-sm font-medium text-gray-700">Área</th>
                <th className="py-3 px-4 text-left text-sm font-medium text-gray-700">Proceso ID</th>
                <th className="py-3 px-4 text-left text-sm font-medium text-gray-700">Estado PDF</th>
                <th className="py-3 px-4 text-left text-sm font-medium text-gray-700">Acciones</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {exams.length === 0 && !loading ? (
                <tr>
                  <td colSpan={6} className="py-4 px-4 text-center text-gray-500">
                    No hay exámenes disponibles
                  </td>
                </tr>
              ) : (
                exams.map(exam => (
                  <tr key={exam.id} className="hover:bg-gray-50">
                    <td className="py-3 px-4 text-sm text-gray-700">{exam.id}</td>
                    <td className="py-3 px-4 text-sm text-gray-700">{exam.type}</td>
                    <td className="py-3 px-4 text-sm text-gray-700">{getAreaName(exam.areaId)}</td>
                    <td className="py-3 px-4 text-sm text-gray-700">{exam.admisionProcessId}</td>
                    <td className="py-3 px-4 text-sm">
                      {exam.documentId ? (
                        <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800">
                          PDF Generado
                        </span>
                      ) : (
                        <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-yellow-100 text-yellow-800">
                          Pendiente
                        </span>
                      )}
                    </td>
                    <td className="py-3 px-4 text-sm flex space-x-2">
                      <Button
                        onClick={() => handleGeneratePdf(exam.id)}
                        disabled={generatingPdf[exam.id] || !!exam.documentId}
                        className={`text-xs px-2 py-1 ${exam.documentId ? 'bg-gray-400' : 'bg-blue-600 hover:bg-blue-700'}`}
                      >
                        {generatingPdf[exam.id] ? 'Generando...' : exam.documentId ? 'Generado' : 'Generar PDF'}
                      </Button>
                      
                      {exam.documentId && (
                        <Button
                          onClick={() => handleDownloadPdf(exam.id)}
                          className="text-xs px-2 py-1 bg-green-600 hover:bg-green-700 flex items-center"
                        >
                          <Download className="h-3 w-3 mr-1" />
                          Descargar
                        </Button>
                      )}
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
        
        <div className="mt-8 flex justify-between">
          <Button 
            onClick={fetchExams}
            className="bg-indigo-600 hover:bg-indigo-700"
          >
            Actualizar Lista
          </Button>
          
          <Button 
            onClick={handleDownloadExamKeysCSV}
            className="bg-purple-600 hover:bg-purple-700 flex items-center"
          >
            <FileText className="h-4 w-4 mr-2" />
            Descargar Claves de Exámenes (CSV)
          </Button>
        </div>
      </div>
    </div>
  );
};

export default ExamManagement;
