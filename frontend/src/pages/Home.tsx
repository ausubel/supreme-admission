import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button } from '../components/Button';

const Home: React.FC = () => {
  const navigate = useNavigate();

  const handleStartRegistration = () => {
    navigate('/registration');
  };

  const handleAdminProcesses = () => {
    navigate('/admin/processes');
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-50 flex flex-col items-center justify-center p-6">
      <div className="max-w-4xl w-full bg-white rounded-xl shadow-lg p-8 text-center">
        <h1 className="text-3xl font-bold text-indigo-900 mb-4">
          Sistema de Admisión Universitaria
        </h1>
        <p className="text-gray-600 mb-8 text-lg">
          Bienvenido al portal de inscripción para el proceso de admisión universitaria.
          Para iniciar su proceso de postulación, haga clic en el botón de abajo.
        </p>
        
        <div className="flex justify-center space-x-4">
          <Button 
            onClick={handleStartRegistration}
            className="text-lg px-8 py-3 bg-gradient-to-r from-indigo-600 to-blue-500 hover:from-indigo-700 hover:to-blue-600 transition-all duration-300 transform hover:scale-105"
          >
            INICIAR INSCRIPCIÓN
          </Button>
          
          <Button 
            onClick={handleAdminProcesses}
            variant="outline"
            className="text-lg px-8 py-3 transition-all duration-300 transform hover:scale-105"
          >
            ADMINISTRAR PROCESOS
          </Button>
        </div>
      </div>
      
      <div className="mt-12 max-w-4xl w-full grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="bg-white rounded-xl shadow-md p-6 text-center hover:shadow-lg transition-shadow">
          <h3 className="text-xl font-semibold text-indigo-800 mb-3">Información</h3>
          <p className="text-gray-600">Consulta fechas, requisitos y procesos de admisión.</p>
        </div>
        
        <div className="bg-white rounded-xl shadow-md p-6 text-center hover:shadow-lg transition-shadow">
          <h3 className="text-xl font-semibold text-indigo-800 mb-3">Estado de Postulación</h3>
          <p className="text-gray-600">Verifica el estado de tu postulación en curso.</p>
        </div>
        
        <div className="bg-white rounded-xl shadow-md p-6 text-center hover:shadow-lg transition-shadow">
          <h3 className="text-xl font-semibold text-indigo-800 mb-3">Contacto</h3>
          <p className="text-gray-600">Comunícate con nosotros para resolver tus dudas.</p>
        </div>
      </div>
    </div>
  );
};

export default Home;
