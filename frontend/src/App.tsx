import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import PrivateRoute from './components/PrivateRoute';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import SignupPage from './pages/SignupPage';
import ItemListPage from './pages/ItemListPage';
import ItemCreatePage from './pages/ItemCreatePage';

const App: React.FC = () => {
  return (
    <Router>
      <div style={{ minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
        <Navbar />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/signup" element={<SignupPage />} />
          <Route
            path="/items"
            element={
              <PrivateRoute>
                <ItemListPage />
              </PrivateRoute>
            }
          />
          <Route
            path="/items/new"
            element={
              <PrivateRoute>
                <ItemCreatePage />
              </PrivateRoute>
            }
          />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
