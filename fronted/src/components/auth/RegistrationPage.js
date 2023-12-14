import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { userRegister } from '../../api/axiosConfig';
import '../../themes/styles.css'; // Assuming you have a styles.css file with your custom styles

const RegisterPage = () => {
    const [formData, setFormData] = useState({
        eposta: '',
        parola: '',
        reparola: '',
        ad: '',
        soyad: '',
        bio: ''
    });
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        setFormData({...formData, [e.target.name]: e.target.value});
    };

    const handleRegister = async (event) => {
        event.preventDefault();
        if (formData.parola !== formData.reparola) {
            alert("Passwords do not match!");
            return;
        }
        // Additional validation if needed
        try {
            // Modify this to match your API call
            await userRegister({
                email: formData.eposta,
                password: formData.parola,
                name: formData.ad,
                surname: formData.soyad,
                bio: formData.bio
                // Add other fields as necessary
            });
            navigate('/verification-page'); // Update with the actual route
        } catch (error) {
            console.error('Registration error:', error);
            // Handle registration errors
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
                            name="eposta"
                            className="form-control mb-2" 
                            value={formData.eposta} 
                            onChange={handleInputChange} 
                            placeholder="Bilkent Mail" 
                            required 
                        />
                        <input 
                            type="password" 
                            name="parola"
                            className="form-control mb-2" 
                            value={formData.parola} 
                            onChange={handleInputChange} 
                            placeholder="Password" 
                            required 
                        />
                        <input 
                            type="password" 
                            name="reparola"
                            className="form-control mb-2" 
                            value={formData.reparola} 
                            onChange={handleInputChange} 
                            placeholder="Re-type Password" 
                            required 
                        />
                        <input 
                            type="text" 
                            name="ad"
                            className="form-control mb-2" 
                            value={formData.ad} 
                            onChange={handleInputChange} 
                            placeholder="Name" 
                            required 
                        />
                        <input 
                            type="text" 
                            name="soyad"
                            className="form-control mb-2" 
                            value={formData.soyad} 
                            onChange={handleInputChange} 
                            placeholder="Surname" 
                            required 
                        />
                        <textarea 
                            name="bio"
                            className="form-control mb-2"
                            value={formData.bio}
                            onChange={handleInputChange}
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
