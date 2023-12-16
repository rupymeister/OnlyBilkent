import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { userRegister } from '../../api/axiosConfig';
import '../../themes/styles.css'; // Assuming you have a styles.css file with your custom styles

const RegisterPage = () => {
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
        <>
            {/* Include your Navbar here as a separate component */}
            <div className="container">
                <form onSubmit={handleRegister}>
                    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
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
                        <button type="submit" className="btn btn-outline-secondary">Register</button>
                    </div>
                </form>
            </div>
        </>
    );
};

export default RegisterPage;
