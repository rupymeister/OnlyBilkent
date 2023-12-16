// src/App.js

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './components/auth/LoginPage';
import NavComponent from './components/auth/NavComponent';
import RegistrationPage from './components/auth/RegistrationPage';
import StudentProfile from './components/profiles/StudentProfile';
import BoardProfile from './components/profiles/BoardProfile';
import Dashboard from './components/layout/Dashboard';
import VerificationPage from './components/auth/VerificationPage';
import EditProfilePage from './components/profiles/EditProfilePage';
import MakePostPage from './components/posts/makePost';
import MakeSellPostPage from './components/posts/makeSellPost';
import MakeLoanPostPage from './components/posts/makeLoanPost';
import MakeLostPostPage from './components/posts/makeLostPost';
import MakeFoundPostPage from './components/posts/makeFoundPost';
import MakeFreePostPage from './components/posts/makeFreePost';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/api/login/asStudent" element={<LoginPage />} />
        <Route path="/" element={<NavComponent />} />
        <Route path="/api/registration" element={<RegistrationPage />} />
        <Route path="/student-profile/:userId" element={<StudentProfile />} />
        <Route path="/board-profile/:userId" element={<BoardProfile />} />
        <Route path="/dashboard" element={<Dashboard/>} />
        <Route path="/verification-page" element={<VerificationPage/>} />
        <Route path="/edit-profile/:userId" element={<EditProfilePage/>} />
        <Route path="/make-post/:userId" element={<MakePostPage/>} />
        <Route path="/make-sell-post/:userId" element={<MakeSellPostPage/>} />
        <Route path="/make-loan-post/:userId" element={<MakeLoanPostPage/>} />
        <Route path="/make-lost-post/:userId" element={<MakeLostPostPage/>} />
        <Route path="/make-found-post/:userId" element={<MakeFoundPostPage/>} />
        <Route path="/make-free-post/:userId" element={<MakeFreePostPage/>} />
      </Routes>
    </Router>
  );
}

export default App;


