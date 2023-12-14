import axios from 'axios';

export const baseURL = 'http://localhost:8080';

const api = axios.create({
    baseURL: baseURL
  });
  

// Login / Logout
export const userLogin = (email, password) => (
  axios.post(`${baseURL}/api/login`, {
    email,
    password
  })
);

export const userLogout = (authType, id) => (
  axios.post(`${baseURL}/user/logout`)
);

export const adminLogin = (email, password) => (
  axios.post(`${baseURL}/admin/login?email=${email}&password=${password}`)
);

export const adminLogout = () => (
  axios.post(`${baseURL}/admin/logout`)
);

//User Registartion
export const userRegister = (email, password, name, surname, bio, profilePic) => (
  axios.post(`${baseURL}/registration`)
);

// User info
export const getUser = (id) => (
  axios.get(`${baseURL}/${id}`)
);

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
export const createUserPost = (userId, postContent, image, title, type) => (
  axios.post(`${baseURL}/posts/create`, {
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
  axios.get(`${baseURL}/posts`)
)

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




