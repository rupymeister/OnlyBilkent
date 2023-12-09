import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080'; // Replace with your backend URL

const instance = axios.create({
  baseURL: API_BASE_URL,
  // Other configurations
});

export default instance;

