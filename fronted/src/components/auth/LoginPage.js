import React, { useState } from 'react';
import { userLoginAsStudent } from '../../api/axiosConfig'; // Import the userLogin function
import { useNavigate} from 'react-router-dom';

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(''); // State to handle any error

  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault(); // Prevent default form submission
    try {
      const response = await userLoginAsStudent(email, password);
      console.log(response.data);
      console.log("Login!!!")
      const userId = response.data; 
      navigate(`/student-profile/${userId}`); // Redirect to user's profile page
    } catch (err) {
      setError(err.response?.data?.message || 'An error occurred during login');
      // Handle login error here
    }
  };

  return (
    <div className="container">
      <h1 className="m-4">Bilkent University Student/Alumni Communication System</h1>
      <div className="card" style={{ width: '26rem', margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
        <img src="img/logo.png" className="card-img-top mt-3" style={{ width: '50%', margin: 'auto' }} alt="Bilkent Uni Logo" />
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
};

export default LoginPage;

