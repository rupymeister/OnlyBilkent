import React, { useState, useEffect } from 'react';
import { getPost } from '../../api/axiosConfig'; // Update with the actual path
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

const PostPage = () => {
  const [postData, setPostData] = useState(null);
  const { postId } = useParams();
  const navigate = useNavigate();
  const [error, setError] = useState(''); // State to handle any error

  useEffect(() => {
    getPost(postId)
      .then(response => {
        setPostData(response.data);
      })
      .catch(error => {
        setError(error.response?.data?.message || 'Error occured during fetching announcement data.');
      });
  }, [postId]);

  console.log(postData);

  if (!postData) {
    return <div>Loading...</div>;
  }

  const handeLogout = () => {
    navigate(`/`);
  };

  const handleProfileClick = (announcementId) => {
    navigate(`/ProfilePage/${senderId}`);
  };
  

  const { title, content, senderName, senderId } = postData;

  return (
    <>
    
  <meta charSet="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
    crossOrigin="anonymous"
  />
  <link rel="stylesheet" href="styles.css" />
  <link rel="shortcut icon" href="favicon.png" type="image/png" />
  <title>Title</title>
  <style
    dangerouslySetInnerHTML={{
      __html:
        "\n  .caret {\n    display: inline-block;\n    width: 13px;\n    height: 13px;\n    background-color: lightgray;\n  }\n  .bg-custom-1 {\n    background-color: #85144b;\n  }\n  \n  .bg-custom-2 {\n  background-image: linear-gradient(15deg, #13547a 0%, #80d0c7 100%);\n  }\n\n  .boyut{\n    \n    font-size: 19px;\n  }\n\n  .helper{\n    font-size: 12px;\n  }\n"
    }}
  />
  <nav
    className="navbar navbar-expand-lg navbar-light bg-light"
    style={{
      background:
        "radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)"
    }}
  >
    <div className="container " >
      
      <a className="navbar-brand me-0 me-lg-5" href="#">
        <img
          src="img/lofoufak.png"
          alt=""
          className="rounded-circle d-inline-block align-text-top"
        />
      </a>
      
      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav me-auto mb-2 mb-lg-0 boyut">
          <li className="nav-item">
            <a className="nav-link active" aria-current="page" href="#">
              Home
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">
              Student Friendly Places
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="http://kafemud.bilkent.edu.tr/monu_eng.html">
              Table D'hote Menu
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="https://w3.bilkent.edu.tr/bilkent/transportation/">
              Bus Schedule
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link" href="#">
              Student Clubs
            </a>
          </li>
          
        </ul>
        <div className="form-check form-switch ms-auto mt-3 me-3">
          <label className="form-check-label ms-3" htmlFor="lightSwitch">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width={25}
              height={25}
              fill="currentColor"
              className="bi bi-brightness-high"
              viewBox="0 0 16 16"
            >
              <path d="M8 11a3 3 0 1 1 0-6 3 3 0 0 1 0 6zm0 1a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z" />
            </svg>
          </label>
          <input
            className="form-check-input"
            type="checkbox"
            id="lightSwitch"
          />
        </div>
        
        <div className="dropdown form-switch ms-auto me-auto mb-auto mb-lg-1 boyut" style={{ textAlign: 'center',   marginBottom: '5px', marginTop: '5px' }}>
        </div>
    <div className="dropdown form-switch ms-auto me-auto mb-auto mb-lg-1 boyut" style={{ textAlign: 'center',   marginBottom: '5px', marginTop: '5px' }}>
          <img
            className="rounded-circle dropdown-toggle"
            width={40}
            marginRight={10}
            marginLeft={10}
            src="https://avatars.githubusercontent.com/u/72274639?v=4"
            id="dropdownMenuButton1"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          />
          
          <span
            className="caret"
            id="dropdownMenuButton1"
            data-bs-toggle="dropdown"
            aria-expanded="false"
          >
          </span>
          <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li>
              <hr className="dropdown-divider" />
            </li>
            <li>
              <a className="dropdown-item" href="#" onClick={handeLogout}>
                Logout
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
  <main style={{ textAlign: 'center' }}> {/* Center align content */}
        <h1>{title}</h1>
        <p>{content}</p>
        <div 
                key={postId} 
                className="post" 
                onClick={() => handleProfileClick(postId)}
              >
                <h2>{senderName}</h2>
              </div>
      </main>
      
</>

  );
};

export default PostPage;