import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { userForgotPassword } from '../../api/axiosConfig';
import '../../themes/styles.css'; 
import logo from '../assets/logo.png';

const PasswordPage = () => {
    const containerStyle = {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        minHeight: '100vh', // Ensures the container takes at least the full height of the viewport
      };
    const [email, setEmail] = useState('');
    const [error, setError] = useState(''); // State to handle any error


    const [verificationCode, setVerificationCode] = useState('');
    const [isRegistered, setIsRegistered] = useState(false);

    const navigate = useNavigate();

    const handleRegister = async (event) => {
        event.preventDefault();

        //checks if it is a bilkent email
        if (!email.endsWith('@ug.bilkent.edu.tr') && !email.endsWith('@alumni.bilkent.edu.tr')) {
          alert('Please use a Bilkent email address');
          return;
        }
        
        // Registering the user 
        try {
            const response = await userForgotPassword(email);
            console.log("Registered!!")
            navigate('/api/login/asStudent'); // Navigates to verification page for email verification
        } catch (err) {
            setError(err.response?.data?.message || 'An error occurred during registration');
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
                    <form onSubmit={handleRegister}>
                    <div className="form-group mt-2">
                        {/* Other form fields */}
                        <input 
                            type="email" 
                            name="email"
                            className="form-control mb-2" 
                            value={email} 
                            onChange={(e) => setEmail(e.target.value)} 
                            placeholder="Bilkent Mail" 
                            required 
                        />
                        <div className="form-group mt-2" >
                        <button type="submit" className="btn btn-success" onClick={handleRegister}>Send Password</button>
                        </div>
                    </div>
                </form>
                {error && <div className="alert alert-danger">{error}</div>}
            </div>
        </div>
    </div>
                    
    );
};

export default PasswordPage;
