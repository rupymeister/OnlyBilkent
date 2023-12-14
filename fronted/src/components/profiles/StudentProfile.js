import React, { useState, useEffect } from 'react';
import { getUser } from '../../api/axiosConfig'; // Update with the actual path

const StudentProfile = () => {
  const [userData, setUserData] = useState(null);

  useEffect(() => {
    const userId = 'user-id'; // Replace with actual logic to get the user's id

    getUser(userId)
      .then(response => {
        setUserData(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the user data:', error);
      });
  }, []);

  if (!userData) {
    return <div>Loading...</div>;
  }

  return (
    <>
      {/* Your existing HTML/JSX code */}
      <main>
        <div className="album bg-light mt-3">
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <h1>{userData.name}</h1> {/* Adjust this according to your data structure */}
                {/* Display more user details here if needed */}
              </div>
            </div>
            {/* Rest of your existing code */}
          </div>
        </div>
      </main>
    </>
  );
};

export default StudentProfile;
