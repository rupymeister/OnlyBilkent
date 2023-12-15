import React, { useState } from 'react';
import { getUser } from '../../api/axiosConfig'; // Update with the actual path
import { updateUser } from '../../api/axiosConfig';
import { userLoginAsStudent } from '../../api/axiosConfig'; // Import the userLogin function
import { useNavigate} from 'react-router-dom';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';
import logo from '../assets/logo.png';
import { set } from 'mongoose';
import { Link } from 'react-router-dom';
import '../../themes/styles.css';


const EditProfilePage = () => {
    const [userData, setUserData] = useState(null);
    const { userId } = useParams();
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [password2, setPassword2] = useState('');
    const [bio, setBio] = useState('');
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [surname, setSurname] = useState('');

    useEffect(() => {
        getUser(userId)
          .then(response => {
            setUserData(response.data);
          })
          .catch(error => {
            console.error('There was an error fetching the user data:', error);
          });
      }, [userId]);
   
      const [loading, setLoading] = useState(false); // Initialize loading state variable

      const handleUpdate = async (event) => {
        event.preventDefault();
      
        if (loading) {
          return;
        }
      
        setLoading(true);
      
        if (password.length < 8) {
          alert("Password must be at least 8 characters long");
          setLoading(false); // Reset loading state on error
          return;
        }
        if (password !== password2) {
          alert("Passwords do not match!");
          setLoading(false); // Reset loading state on error
          return;
        }
        if (name === '' || surname === '' || bio === '') {
          alert("Please fill all the fields");
          setLoading(false); // Reset loading state on error
          return;
        }
      
        try {
          const response = await updateUser(userId, name, surname, password, bio);
          setSuccess('Profile updated successfully');
        } catch (err) {
          setError(err.response?.data?.message || 'An error occurred during update');
          // Handle update error here
        } finally {
          setLoading(false); // Reset loading state regardless of success or failure
        }
      };
      

    return (
        <div id="root" className="card" style={{ margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
        <div className="container" style={{ padding: '20px' }}>
            <div className="card" style={{ width: '26rem', margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
            <h2 className="card-title" style={{ textAlign: 'center', color: '#333', fontSize: '24px', fontWeight: 'bold', marginBottom: '10px', marginTop: '10px' }}>Edit Profile</h2>
            <img src={logo} className="card-img-top mt-3" style={{ width: '50%', margin: 'auto' }} alt="Bilkent Uni Logo" />
            <div className="card-body">
                <form onSubmit={handleUpdate} className="myform">
                <div className="form-group mt-2">
                    <label htmlFor="name">Name</label>
                        
                        <input
                        id="name"
                        className="form-control"
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        placeholder="Please enter your full name"
                        required
                        autocomplete="name" // Add autocomplete attribute for name
                        />
                    </div>

                    <div className="form-group mt-2">
                        <label htmlFor="surname">Surname</label>
                        <input
                        id="surname"
                        className="form-control"
                        type="text"
                        value={surname}
                        onChange={(e) => setSurname(e.target.value)}
                        placeholder="Please enter your surname"
                        required
                        autocomplete="family-name" // Add autocomplete attribute for surname
                        />
                    </div>

                    <div className="form-group mt-2">
                        <label htmlFor="password">Enter Password</label>
                        <input
                        id="password"
                        className="form-control"
                        type="password" // Use type "password" for password fields
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Please enter your password"
                        required
                        autocomplete="new-password" // Add autocomplete attribute for password
                        />
                    </div>

                    <div className="form-group mt-2">
                        <label htmlFor="password2">Enter Password Again</label>
                        <input
                        id="password2"
                        className="form-control"
                        type="password" // Use type "password" for password fields
                        value={password2}
                        onChange={(e) => setPassword2(e.target.value)}
                        placeholder="Please enter your password again"
                        autocomplete="new-password2" // Add autocomplete attribute for password confirmation
                        />
                    </div>

                    <div className="form-group mt-2">
                        <label htmlFor="bio">Enter Bio</label>
                        <textarea
                        id="bio"
                        className="form-control"
                        value={bio}
                        onChange={(e) => setBio(e.target.value)}
                        placeholder="Please enter your bio"
                        autocomplete="off" // Set autocomplete to "off" for textarea (customize as needed)
                        ></textarea>
                    </div>

                    
                    <div className="form-group mt-2">
                    <button
                        type="submit"
                        className="btn btn-primary equal-size-btn"
                        disabled={loading}
                    >
                        {loading ? 'Updating...' : 'Update'}
                    </button>
                    
                    <Link to={`/student-profile/${userId}`}>
                        <button
                        type="button"
                        className="btn btn-primary equal-size-btn"
                        disabled={loading}
                        >
                        {loading ? 'Navigating...' : 'Navigate to Profile Page'}
                        </button>
                    </Link>
                    </div>

                    </form>

                    {error && <div className="alert alert-danger">{error}</div>}
                    {success && <div className="alert alert-success">{success}</div>}
                </div>
            </div>
        </div>
        </div>

    );
}

export default EditProfilePage;