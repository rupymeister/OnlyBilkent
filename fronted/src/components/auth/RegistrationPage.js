import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { userRegister } from '../../api/axiosConfig';
import '../../themes/styles.css'; // Assuming you have a styles.css file with your custom styles
import logo from '../assets/logo.png';

const RegisterPage = () => {
    const containerStyle = {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        minHeight: '100vh', // Ensures the container takes at least the full height of the viewport
      };
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [repassword, setRePassword] = useState('');
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [bio, setBio] = useState('');
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
        if(password.length < 8){
            alert("Password must be at least 8 characters long");
            return;
        }
        //checks if the confirm password is correct
        if (password !== repassword) {
            alert("Passwords do not match!");
            return;
        }
        
        // Registering the user 
        try {
            const response = await userRegister(email, password, name, surname, bio);
            console.log("Registered!!")
            navigate('/verification-page'); // Navigates to verification page for email verification
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
                        <input 
                            type="password" 
                            name="password"
                            className="form-control mb-2" 
                            value={password} 
                            onChange={(e) => setPassword(e.target.value)} 
                            placeholder="Password" 
                            required 
                        />
                        <input 
                            type="password" 
                            name="repassword"
                            className="form-control mb-2" 
                            value={repassword} 
                            onChange={(e) => setRePassword(e.target.value)} 
                            placeholder="Re-type Password" 
                            required 
                        />
                        <input 
                            type="text" 
                            name="name"
                            className="form-control mb-2" 
                            value={name} 
                            onChange={(e) => setName(e.target.value)} 
                            placeholder="Name" 
                            required 
                        />
                        <input 
                            type="text" 
                            name="surname"
                            className="form-control mb-2" 
                            value={surname}
                            onChange={(e) => setSurname(e.target.value)} 
                            placeholder="Surname" 
                            required 
                        />
                        <textarea 
                            name="bio"
                            className="form-control mb-2"
                            value={bio}
                            onChange={(e) => setBio(e.target.value)}
                            placeholder="Introduce Yourself"
                            rows="5"
                            style={{ resize: 'both' }}
                            required
                        ></textarea>
                        <div className="form-group mt-2" >
                        <button type="submit" className="btn btn-success" >Register</button>
                        </div>
                    </div>
                </form>
                {error && <div className="alert alert-danger">{error}</div>}
            </div>
        </div>
    </div>
                    
    );
};

export default RegisterPage;
