import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { verifyUser } from '../../api/axiosConfig'; // Assuming you have a verification API
import logo from '../assets/logo.png';

const VerificationPage = () => {
  const [email, setEmail] = useState(''); // Assuming you need the user's email
  const [verificationCode, setVerificationCode] = useState('');
  const [error, setError] = useState(''); // State to handle any error

  const navigate = useNavigate();

  const handleVerification = async (event) => {
    event.preventDefault();
    try {
      const response = await verifyUser(verificationCode, email);
      console.log(response);
      navigate('/api/login/asStudent'); // Redirect to login page
    } catch (err) {
      setError(err.response?.data?.message || 'An error occurred during verification');
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
      <h2 className="card-title" style={{ textAlign: 'center', color: '#333', fontSize: '48px', fontWeight: 'bold', marginBottom: '10px', marginTop: '10px' }}>OnlyBilkent</h2>
      <img src={logo} className="card-img-top mt-3" style={{ width: '50%', margin: 'auto' }} alt="Bilkent Uni Logo" />
      <h2 className="card-title" style={{ textAlign: 'center', color: '#333', fontSize: '24px', fontWeight: 'bold', marginBottom: '10px', marginTop: '10px' }}>For better Bilkenter!</h2>
      <h2 className="card-title" style={{ textAlign: 'center', color: '#333', fontSize: '20px', marginBottom: '10px', marginTop: '10px' }}>Verification Code is sent to your email.</h2>
      {/* Verification form */}
      <form onSubmit={handleVerification}>
          <input 
            type="text" 
            name="verificationCode"
            className="form-control mb-2" 
            value={verificationCode} 
            onChange={(e) => setVerificationCode(e.target.value)} 
            placeholder="Enter Verification Code..." 
            required 
          />
        <button type="submit" className="btn btn-success">Verify</button>
      </form>
    </div>
    </div>
  );
};

export default VerificationPage;
