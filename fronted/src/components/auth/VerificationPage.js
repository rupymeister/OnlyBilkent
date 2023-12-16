import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { verifyUser } from '../../api/axiosConfig'; // Assuming you have a verification API

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
      {/* Verification form */}
      <form onSubmit={handleVerification}>
          <input 
            type="text" 
            name="verificationCode"
            className="form-control mb-2" 
            value={verificationCode} 
            onChange={(e) => setVerificationCode(e.target.value)} 
            placeholder="Enter Verification Code" 
            required 
          />
        <button type="submit" className="btn btn-outline-secondary">Verify</button>
      </form>
    </div>
  );
};

export default VerificationPage;
