import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { verifyUser } from '../../api/axiosConfig'; // Assuming you have a verification API

const VerificationPage = () => {
  const [verificationCode, setVerificationCode] = useState('');
  const navigate = useNavigate();

  const handleVerification = async (event) => {
    event.preventDefault();
    try {
      const userId = await verifyUser(verificationCode);
      navigate(`/student-profile/${userId}`);
    } catch (err) {
        setError(err.response?.data?.message || 'An error occurred during Registration');
    }
  };

  return (
    <div className="container">
      {/* Verification form */}
      <form onSubmit={handleVerification}>
        {/* Verification code input */}
        <button type="submit" className="btn btn-outline-secondary">Verify</button>
      </form>
    </div>
  );
};

export default VerificationPage;
