import React, { useState } from 'react';
import { userLoginAsBoard } from '../../api/axiosConfig'; // Import the userLogin function
import { useNavigate} from 'react-router-dom';
import logo from '../assets/logo.png';

const LoginBoardPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(''); // State to handle any error

  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault(); // Prevent default form submission
    try {
      const response = await userLoginAsBoard(email, password);
      console.log(response.data);
      console.log("Login!!!")
      const userId = response.data; 
      navigate(`/board-profile/${userId}`); // Redirect to user's profile page
    } catch (err) {
      setError(err.response?.data?.message || 'An error occurred during login');
      // Handle login error here
    }
  };

  return (
    
    <div className="container">
      <style>
        {`
          body {
            
            background: radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%);
          }
        `}
      </style>
      <div className="card" style={{ margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
      <h2 className="card-title" style={{ textAlign: 'center', color: '#333', fontSize: '24px', fontWeight: 'bold', marginBottom: '10px', marginTop: '10px' }}>OnlyBilkent</h2>
        <img src={logo} className="card-img-top mt-3" style={{ width: '50%', margin: 'auto' }} alt="Bilkent Uni Logo" />
        <div className="card-body">
          <form onSubmit={handleLogin} className="myform">
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
              <button type="submit" className="btn btn-primary">Login</button>
              <a href="#" style={{ float: 'right' }}>Forgot Password?</a>
            </div>
          </form>
          {error && <div className="alert alert-danger">{error}</div>}
        </div>
      </div>
    </div>
  );
}

export default LoginBoardPage;

