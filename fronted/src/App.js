// src/App.js

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './components/auth/LoginPage';
import NavComponent from './components/auth/NavComponent';
import RegistrationPage from './components/auth/RegistrationPage';
import StudentProfile from './components/profiles/StudentProfile';
import BoardProfile from './components/profiles/BoardProfile';
import Dashboard from './components/layout/Dashboard';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/api/login" element={<LoginPage />} />
        <Route path="/" element={<NavComponent />} />
        <Route path="/registration" element={<RegistrationPage />} />
        <Route path="/student-profile/:userId" element={<StudentProfile />} />
        <Route path="/board-profile/:userId" element={<BoardProfile />} />
        <Route path="/dashboard" element={<Dashboard/>} />
      </Routes>
    </Router>
  );
}

export default App;


