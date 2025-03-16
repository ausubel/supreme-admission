import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Home from './pages/Home';
import Registration from './pages/Registration';
import { Toaster } from 'react-hot-toast';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/registration" element={<Registration />} />
        <Route path="/" element={<Navigate to="/home" replace />} />
      </Routes>
      <Toaster position="top-right" />
    </Router>
  );
}

export default App;
