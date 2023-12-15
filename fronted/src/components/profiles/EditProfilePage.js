import React, { useState } from 'react';
import { userLoginAsStudent } from '../../api/axiosConfig'; // Import the userLogin function
import { useNavigate} from 'react-router-dom';


const EditProfilePage = ({ user, updateUser }) => {
    const [name, setName] = useState(user.name);
    const [email, setEmail] = useState(user.email);
    const [password, setPassword] = useState(user.password);
    const [password2, setPassword2] = useState(user.password);
    const [bio, setBio] = useState(user.bio);
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [surname, setSurname] = useState(user.surname);

    const handleUpdate = async (event) => {
        event.preventDefault(); // Prevent default form submission
        try {
            const response = await updateUser(user.id, { name, password, bio});
            console.log(response.data);
            setSuccess('Profile updated successfully');
        } catch (err) {
            setError(err.response?.data?.message || 'An error occurred during update');
            // Handle update error here
        }
    };

    return (
        <div className="container">
            <h1 className="m-4">Edit Profile</h1>
            <div className="card" style={{ width: '26rem', margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
                <img src="img/logo.png" className="card-img-top mt-3" style={{ width: '50%', margin: 'auto' }} alt="Bilkent Uni Logo" />
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
                            />
                        </div>

                        <div className="form-group mt-2">
                            <label htmlFor="name">Surname</label>
                            <input
                                id="surname"
                                className="form-control"
                                type="text"
                                value={name}
                                onChange={(e) => setSurname(e.target.value)}
                                placeholder="Please enter your surname"
                            />
                        </div>

                        <div className="form-group mt-2">
                            <label htmlFor="password">Enter Password</label>
                            <input
                                id="password"
                                className="form-control"
                                type="text"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                placeholder="Please enter your password"
                            />
                        </div>

                        <div className="form-group mt-2">
                            <label htmlFor="password2">Enter Password Again</label>
                            <input
                                id="password2"
                                className="form-control"
                                type="text"
                                value={password2}
                                onChange={(e) => setPassword2(e.target.value)}
                                placeholder="Please enter your password again"
                            />
                        </div>

                        <div className="form-group mt-2">
                            <label htmlFor="bio">Enter Bio</label>
                            <input
                                id="bio"
                                className="form-control"
                                type="text"
                                value={bio}
                                onChange={(e) => setBio(e.target.value)}
                                placeholder="Please enter your bio"
                            />
                        </div>

                        <div className="form-group mt-2">
                            <button type="submit" className="btn btn-primary">Update</button>
                        </div>
                    </form>
                    {error && <div className="alert alert-danger">{error}</div>}
                    {success && <div className="alert alert-success">{success}</div>}
                </div>
            </div>
        </div>
      

    );
}

export default EditProfilePage;