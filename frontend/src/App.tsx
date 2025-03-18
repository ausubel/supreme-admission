import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Home from './pages/Home';
import Registration from './pages/Registration';
import AdminProcesses from './pages/AdminProcesses';
import ExamManagement from './pages/ExamManagement';
import { Toaster } from 'react-hot-toast';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/registration" element={<Registration />} />
        <Route path="/admin/processes" element={<AdminProcesses />} />
        <Route path="/admin/exams" element={<ExamManagement />} />
        <Route path="/" element={<Navigate to="/home" replace />} />
      </Routes>
      <Toaster position="top-right" />
    </Router>
  );
}

export default App;
