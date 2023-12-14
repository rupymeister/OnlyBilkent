// src/App.js

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './components/auth/LoginPage';
import NavComponent from './components/auth/NavComponent';
import RegistrationPage from './components/auth/RegistrationPage';
import StudentProfile from './components/profiles/StudentProfile';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/api/login" element={<LoginPage />} />
        <Route path="/" element={<NavComponent />} />
        <Route path="/register" element={<RegistrationPage />} />
        <Route path="/student-profile/:userId" Component={StudentProfile} />
      </Routes>
    </Router>
  );
}

export default App;


