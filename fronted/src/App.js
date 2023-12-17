// src/App.js

import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './components/auth/LoginPage';
import LoginBoardPage from './components/auth/LoginBoardPage';
import NavComponent from './components/auth/NavComponent';
import PasswordPage from './components/auth/ForgotPassword';
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
import PostPage from './components/posts/postPage';
import MakeAnnouncementPage from './components/announcements/MakeAnnouncement';
import AnnouncementPage from './components/announcements/AnnouncementPage';
import DashboardMock from './components/layout/dashboardMock';
import ProfilePage from './components/layout/ProfilePage';
import MessagePage from './components/chat/Message';
import ChatPage from './components/chat/Chat';
import BoardRequestPage from './components/layout/BoardRequestPage';
import SellPostsPage from './components/posts/SellPosts';
import LoanPostsPage from './components/posts/LoanPosts';
import LostPostsPage from './components/posts/LostPosts';
import FreePostsPage from './components/posts/FreePosts';
import FoundPostsPage from './components/posts/FoundPosts';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/api/login-board" element={<LoginPage />} />
        <Route path="/api/login/asStudent" element={<LoginPage />} />
        <Route path="/api/login/asBoard" element={<LoginBoardPage />} />
        <Route path="/" element={<NavComponent />} />
        <Route path="/api/registration" element={<RegistrationPage />} />
        <Route path="/student-profile/:userId" element={<StudentProfile />} />
        <Route path="/board-profile/:userId" element={<BoardProfile />} />
        <Route path="/dashboard/:userId" element={<Dashboard/>} />
        <Route path="/verification-page" element={<VerificationPage/>} />
        <Route path="/edit-profile/:userId" element={<EditProfilePage/>} />
        <Route path="/make-post/:userId" element={<MakePostPage/>} />
        <Route path="/make-sell-post/:postId" element={<MakeSellPostPage/>} />
        <Route path="/make-loan-post/:postId" element={<MakeLoanPostPage/>} />
        <Route path="/make-lost-post/:postId" element={<MakeLostPostPage/>} />
        <Route path="/make-found-post/:postId" element={<MakeFoundPostPage/>} />
        <Route path="/make-free-post/:postId" element={<MakeFreePostPage/>} />
        <Route path="/dashboard0" element={<DashboardMock/>} />
        <Route path="/post/:postId" element={<PostPage/>} />
        <Route path="/make-announcement/:userId" element={<MakeAnnouncementPage/>} />
        <Route path="/ProfilePage/:userId" element={<ProfilePage/>} />
        <Route path="/messages/:userId/:chatId" element={<MessagePage/>} />
        <Route path="/chats/:userId" element={<ChatPage/>} />
        <Route path="/board-request/:userId" element={<BoardRequestPage/>} />
        <Route path="/posts/Selling" element={<SellPostsPage/>} />
        <Route path="/posts/Loan" element={<LoanPostsPage/>} />
        <Route path="/posts/Lost" element={<LostPostsPage/>} />
        <Route path="/posts/Found" element={<FoundPostsPage/>} />
        <Route path="/posts/Free" element={<FreePostsPage/>} />
        <Route path="/announcement/:announcementId" element={<AnnouncementPage/>} />
        <Route path="/forgot-password" element={<PasswordPage/>} />
      </Routes>
    </Router>
  );
}

export default App;


