import axios from 'axios';

export const baseURL = 'http://localhost:8080';

const api = axios.create({
    baseURL: baseURL
  });
  

// Login / Logout
export const userLoginAsStudent = (email, password) => (
  axios.post(`${baseURL}/api/login/asStudent`, {
    email,
    password
  })
);

export const userLoginAsBoard = (email, password) => (
  axios.post(`${baseURL}/api/login/asBoard`, {
    email,
    password
  })
);

export const userLoginAsAdmin = (email, password) => (
  axios.post(`${baseURL}/api/login/asAdmin`, {
    email,
    password
  })
);

export const userLogout = (authType, id) => (
  axios.post(`${baseURL}/user/logout`)
);

export const adminLogout = () => (
  axios.post(`${baseURL}/admin/logout`)
);

export const boardLogout = () => (
  axios.post(`${baseURL}/board/logout`)
);

//User Registartion
export const userRegister = (email, password, name, surname, bio) => (
  axios.post(`${baseURL}/registration/userReg`, { email, password, name, surname, bio })
);

export const verifyUser = (verificationCode, email) => (
  axios.post(`${baseURL}/registration/confirm`, null, {
    params: { token: verificationCode, email }
  })
);

// User info
export const getUser = (id) => (
  axios.get(`${baseURL}/users/${id}`)
);

exposrt const getPost = (postId) => (
  axios.get(`${baseURL}/posts/${postId}`)
);

export const updateUser = (userId, name, surname, password, bio) => {
  const url = `http://localhost:8080/users/editUser/${userId}`;
  console.log(password);
  const data = { name, surname, password, bio };

  // Assuming you're using Axios for HTTP requests
  return axios.post(url, data);
};

// BoardAccounts
export const getBoards = () => (
  axios.get(`${baseURL}/clubs`)
);

export const createBoard = (userId, clubId, clubName) => (
  axios.post(`${baseURL}/clubs/create`, {
    userId,
    clubId,
    clubName
  })
);

// User Posts
export const createPost = (userId) => (
  axios.post(`${baseURL}/posts-create/sell/${userId}`, {
    userId,
  })
);

export const createSellPost = (userId, postContent, image, title, type) => (
  axios.post(`${baseURL}/posts/createSellPost/${postId}`, {
    userId,
    postContent,
    image,
    title, 
    type
  })
);

export const createLostPost = (userId, postContent, image, title, type) => (
  axios.post(`${baseURL}/posts/createLostPost/${postId}`, {
    userId,
    postContent,
    image,
    title, 
    type
  })
);

export const createFoundPost = (userId, postContent, image, title, type) => (
  axios.post(`${baseURL}/posts/createFoundPost/${postId}`, {
    userId,
    postContent,
    image,
    title, 
    type
  })
);

export const createLoanPost = (userId, postContent, image, title, type) => (
  axios.post(`${baseURL}/posts/createLoanPost/${postId}`, {
    userId,
    postContent,
    image,
    title, 
    type
  })
);

export const createFreePost = (userId, postContent, image, title, type) => (
  axios.post(`${baseURL}/posts/createFreePost/${postId}`, {
    userId,
    postContent,
    image,
    title, 
    type
  })
);

export const getUserPosts = (userId) => (
  axios.get(`${baseURL}/posts/user/${userId}`)
);

// Notifications
export const getNotifications = id => (
  axios.get(`${baseURL}/notification/user/${id}`)
);

export const markAsReadNotification = id => (
  axios.post(`${baseURL}/notification/markRead/${id}`)
);

// Announcements
export const createAnnouncement = (boardId, announcementTitle, content) => (
  axios.post(`${baseURL}/board/createPost`, {
    boardId,
    announcementTitle, 
    content
  })
);

export const getAnnouncement = (boardId) => (
  axios.get(`${baseURL}/board/posts/${boardId}`)
);

// get all announcements (for dashboard)
export const getAnnouncements = () => (
  axios.get(`${baseURL}/announcements/all`)
);

// Admin Functions
export const getReportedUsers = () => (
  axios.get(`${baseURL}/admin/reportedUsers`)
);

export const banUser = (userId) => (
  axios.post(`${baseURL}/admin/banUser/${userId}`)
);

export const getBannedUsers = () => (
  axios.get(`${baseURL}/admin/bannedUsers`)
);

// Fetch all user posts (for feed)
export const getAllUserPosts = () => (
  axios.get(`${baseURL}/posts/all`)
);

//Fetch posts for each type
export const getSellingPosts = () => (
  axios.get(`${baseURL}/posts/sell`)
);

export const getBorrowPosts = () => (
  axios.get(`${baseURL}/posts/borrow`)
);

export const getFreePosts = () => (
  axios.get(`${baseURL}/posts/free`)
);

// File/Image Upload
export const uploadImage = (file) => (
  fetch(`${baseURL}/upload/image`, {
    method: 'POST',
    body: file
  })
);

// Optional: Handle user reports
export const reportUser = (reportingUserId, reportedUserId, reason) => (
  axios.post(`${baseURL}/user/report`, {
    reportingUserId,
    reportedUserId,
    reason
  })
);

export default api




