import React, { useState, useEffect } from 'react';
import { Button } from '../components/Button';
import axios from 'axios';

interface AdmisionProcess {
  id: number;
  name: string;
  startDate: string;
  endDate: string;
  status: number;
}

interface StartProcessFormData {
  name: string;
  startDate: string;
  endDate: string;
}

const AdminProcesses: React.FC = () => {
  const [activeProcess, setActiveProcess] = useState<AdmisionProcess | null>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>('');
  const [success, setSuccess] = useState<string>('');
  const [formData, setFormData] = useState<StartProcessFormData>({
    name: '',
    startDate: '',
    endDate: ''
  });
  const [showForm, setShowForm] = useState<boolean>(false);

  // Verificar si hay un proceso activo al cargar la página
  useEffect(() => {
    checkActiveProcess();
  }, []);

  const checkActiveProcess = async () => {
    setLoading(true);
    setError('');
    try {
      const response = await axios.get<AdmisionProcess>('http://localhost:8080/api/admision-processes/active');
      setActiveProcess(response.data);
    } catch (err) {
      if (axios.isAxiosError(err) && err.response?.status === 404) {
        // No hay proceso activo, esto es normal
        setActiveProcess(null);
      } else {
        setError('Error al verificar el proceso activo. Por favor, intente nuevamente.');
        console.error('Error al verificar proceso activo:', err);
      }
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleStartProcess = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError('');
    setSuccess('');

    try {
      await axios.post<string>(
        'http://localhost:8080/api/admision-processes/start',
        formData
      );
      try {
        await axios.post<string>(
          'http://localhost:8080/api/questions/distribute'
        );
        
        setSuccess('Proceso de admisión iniciado exitosamente y preguntas distribuidas correctamente');
      } catch (distributionErr) {
        console.error('Error al distribuir preguntas:', distributionErr);
        setSuccess('Proceso de admisión iniciado exitosamente, pero hubo un error al distribuir las preguntas');
      }

      setFormData({
        name: '',
        startDate: '',
        endDate: ''
      });
      setShowForm(false);
      
      // Verificar el proceso activo después de iniciar uno nuevo
      await checkActiveProcess();
    } catch (err) {
      setError('Error al iniciar el proceso de admisión. Por favor, intente nuevamente.');
      console.error('Error al iniciar proceso:', err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-50 p-6">
      <div className="max-w-4xl mx-auto bg-white rounded-xl shadow-lg p-8">
        <h1 className="text-3xl font-bold text-indigo-900 mb-6">Administración de Procesos de Admisión</h1>
        
        {loading ? (
          <div className="flex justify-center my-8">
            <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-indigo-500"></div>
          </div>
        ) : (
          <div>
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
            
            <div className="mb-8">
              <h2 className="text-xl font-semibold text-indigo-800 mb-3">Estado Actual</h2>
              
              {activeProcess ? (
                <div className="bg-blue-50 p-4 rounded-lg border border-blue-200">
                  <p className="font-medium text-blue-800 mb-2">Proceso Activo: {activeProcess.name}</p>
                  <p className="text-blue-600">Fecha de inicio: {new Date(activeProcess.startDate).toLocaleDateString()}</p>
                  <p className="text-blue-600">Fecha de fin: {new Date(activeProcess.endDate).toLocaleDateString()}</p>
                </div>
              ) : (
                <div className="bg-yellow-50 p-4 rounded-lg border border-yellow-200">
                  <p className="text-yellow-700">No hay ningún proceso de admisión activo actualmente.</p>
                </div>
              )}
            </div>
            
            <div className="flex justify-center mb-6">
              <Button 
                onClick={() => setShowForm(!showForm)}
                disabled={!!activeProcess}
                className={`text-lg px-6 py-3 ${activeProcess ? 'bg-gray-400' : 'bg-gradient-to-r from-indigo-600 to-blue-500 hover:from-indigo-700 hover:to-blue-600'} transition-all duration-300`}
              >
                {activeProcess ? 'Ya existe un proceso activo' : 'Iniciar Nuevo Proceso de Admisión'}
              </Button>
            </div>
            
            {showForm && !activeProcess && (
              <div className="bg-gray-50 p-6 rounded-lg border border-gray-200">
                <h3 className="text-lg font-medium text-gray-800 mb-4">Nuevo Proceso de Admisión</h3>
                
                <form onSubmit={handleStartProcess}>
                  <div className="mb-4">
                    <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="name">
                      Nombre del Proceso
                    </label>
                    <input
                      type="text"
                      id="name"
                      name="name"
                      value={formData.name}
                      onChange={handleInputChange}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      required
                    />
                  </div>
                  
                  <div className="mb-4">
                    <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="startDate">
                      Fecha de Inicio
                    </label>
                    <input
                      type="date"
                      id="startDate"
                      name="startDate"
                      value={formData.startDate}
                      onChange={handleInputChange}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      required
                    />
                  </div>
                  
                  <div className="mb-6">
                    <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor="endDate">
                      Fecha de Fin
                    </label>
                    <input
                      type="date"
                      id="endDate"
                      name="endDate"
                      value={formData.endDate}
                      onChange={handleInputChange}
                      className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                      required
                    />
                  </div>
                  
                  <div className="flex justify-end">
                    <Button 
                      type="button" 
                      variant="outline" 
                      onClick={() => setShowForm(false)}
                      className="mr-2"
                    >
                      Cancelar
                    </Button>
                    <Button type="submit" disabled={loading}>
                      {loading ? 'Iniciando...' : 'Iniciar Proceso'}
                    </Button>
                  </div>
                </form>
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default AdminProcesses;
