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

export const userLoginEmail = (email) => (
  axios.post(`${baseURL}/api/login/email`, {
    email
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

export const getPost = (postId) => (
  axios.get(`${baseURL}/posts/${postId}`)
);

export const getPostId = (posts) => (
  axios.get(`${baseURL}/posts/${posts}`)
);

export const getImage = (photoId) => (
  axios.get(`${baseURL}/image1/${photoId}`)
);

export const getBaseImage = (photoId) => (
  axios.get(`${baseURL}/image2/${photoId}`)
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
export const createPost = (postType, userId) => (
  axios.post(`${baseURL}/posts/create/${userId}`, {
    postType,
    userId
  })
);

export const createSellPost = (postId, formData) => (
  axios.put(`${baseURL}/posts/createSalePost/${postId}`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryqfDZNbBopJT4MiqA;charset=UTF-8'
    }
  })
);

export const createLostPost = (postId, postContent, image, title, type) => (
  axios.post(`${baseURL}/posts/createLostPost/${postId}`, {
    postId,
    postContent,
    image,
    title, 
    type
  })
);

export const createFoundPost = (postId, postContent, image, title, type) => (
  axios.post(`${baseURL}/posts/createFoundPost/${postId}`, {
    postId,
    postContent,
    image,
    title, 
    type
  })
);

export const createLoanPost = (postId, title, content, category, loanPricePerTime) => (
  axios.put(`${baseURL}/posts/createLoanPost/${postId}`, {
    postId,
    title,
    content,
    category, 
    loanPricePerTime
  })
);

export const createFreePost = (postId, postContent, image, title, type) => (
  axios.post(`${baseURL}/posts/createFreePost/${postId}`, {
    postId,
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

export const getLoanPost = () => (
  axios.get(`${baseURL}/posts/loan`)
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




