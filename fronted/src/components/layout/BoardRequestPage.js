import React, { useState } from 'react';
import { createBoard } from '../../api/axiosConfig'
import { useParams, useNavigate } from 'react-router-dom';
import logo from '../assets/logo.png'; // Make sure the path to your logo is correct

const BoardRequest = () => {
  const [clubName, setClubName] = useState('');
  const [reason, setReason] = useState('');
  const { userId } = useParams();
  const navigate = useNavigate();
  const [error, setError] = useState(''); // State to handle any error
  const [successMessage, setSuccessMessage] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    
    try {
      // Here, send the request data to your server
      console.log('Submitting board request:', { clubName, reason });
      const response = await createBoard(userId, clubName, reason);
      setSuccessMessage('Your Request has been made succesfully!');
      

      // Reset the form fields
      setClubName('');
      setReason('');
      setError(''); // Clear any existing errors
    
    } catch (err) {
      setError("There has been an issue with your request please fill the forms accordingly"); // Update the error message based on your error handling logic
    }
  };

  const handleGoBack = () => {
    navigate(`/student-profile/${userId}`);
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
        <h2 className="card-title" style={{ textAlign: 'center', color: '#333', fontSize: '24px', fontWeight: 'bold', marginBottom: '10px', marginTop: '10px' }}>Board Account Request</h2>
        <img src={logo} className="card-img-top mt-3" style={{ width: '50%', margin: 'auto' }} alt="Bilkent Uni Logo" />
        <div className="card-body">
          <form onSubmit={handleSubmit} className="myform">
            <div className="form-group mt-2">
              <label htmlFor="clubName">Club Name</label>
              <input 
                id="clubName" 
                className="form-control" 
                type="text" 
                value={clubName}
                onChange={(e) => setClubName(e.target.value)} 
                placeholder="Enter the club name" 
              />
            </div>

            <div className="form-group mt-2">
              <label htmlFor="reason">Reason for Request</label>
              <textarea 
                id="reason" 
                className="form-control" 
                rows="3" 
                value={reason}
                onChange={(e) => setReason(e.target.value)} 
                placeholder="Describe why you want to open a board page" 
              />
            </div>

            <div className="form-group mt-2">
              <button type="submit" className="btn btn-primary">Submit Request</button>
            </div>

            <div className="form-group mt-2">
              <button type="button" className="btn btn-secondary" onClick={handleGoBack}>Go Back to Profile</button>
            </div>
          </form>
          {error && <div className="alert alert-danger">{error}</div>}
          {successMessage && <div className="alert alert-success">{successMessage}</div>}
        </div>
      </div>
    </div>
  );
};

export default BoardRequest;
