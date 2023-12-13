import React, { useState } from 'react';
import { userLogin } from '../../api/axiosConfig'; // Import the userLogin function
//import { useHistory } from 'react-router-dom';

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(''); // State to handle any error

  const handleLogin = async (event) => {
    event.preventDefault(); // Prevent default form submission
    try {
      const response = await userLogin('normal', email, password);
      console.log(response.data);
      //const userId = response.data.userId; 
      //history.push(`/profile/${userId}`); // Redirect to user's profile page
    } catch (err) {
      setError(err.response?.data?.message || 'An error occurred during login');
      // Handle login error here
    }
  };

  return (
    <>
      {/* Meta tags and links */}
      <div className="container">
        {/* Container content */}
        <form className="myform" onSubmit={handleLogin}>
          <div className="form-group mt-2">
            <label htmlFor="email">E-mail</label>
            <input
              id="email"
              className="form-control"
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder="Please enter your email address"
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="password">Password</label>
            <input
              id="password"
              className="form-control"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Please enter your password"
            />
          </div>
          <div className="form-group mt-2">
            <button type="submit" className="btn btn-primary">
              Login
            </button>
            {/* Other buttons */}
          </div>
          {error && <div className="alert alert-danger mt-2">{error}</div>}
        </form>
      </div>
      {/* Footer */}
    </>
  );
};

export default LoginPage;

