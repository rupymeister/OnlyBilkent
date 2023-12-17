import React, { useState, useEffect } from 'react';
import {getAnnouncements, getAnnouncement} from '../../api/axiosConfig';
import { getUser } from '../../api/axiosConfig';
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';

import { Link } from 'react-router-dom';

const Dashboard = () => {
  const navigate = useNavigate();
  const [userData, setUserData] = useState(null);
  const { userId } = useParams();
  const [announcements, setAnnouncements] = useState([]);
  const [announceData, setAnnounceData] = useState([]);
  const [filteredAnnouncements, setFilteredAnnouncements] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [error, setError] = useState(''); // State to handle any error
  const [recentSearches, setRecentSearches] = useState([]);


  // Fetching categories and announcements
  useEffect(() => {
    const fetchAnnouncements = async () => {
      const response = await getAnnouncements();
      setAnnouncements(response.data);
      setFilteredAnnouncements(response.data);
    };
    fetchAnnouncements();
  }, []);


  const [categories, setCategories] = useState([
    // Assuming these are your categories; replace with API call if needed
    { id: 1, name: 'Selling' },
    { id: 2, name: 'Loan' },
    { id: 3, name: 'Lost' },
    { id: 3, name: 'Found' },
    { id: 3, name: 'Free' }
  ]);

  const handleHomeButton = () => {
    // Redirect to user's profile page
    navigate(`/dashboard/`);
  };

  const handleProfileClick = () => {
    // Redirect to user's profile page
    navigate(`/ProfilePage/${userId}`);
  };

  const handleMakePost = () => {
    navigate(`/make-post/${userId}`)
  }

  const handeLogout = () => {
    navigate(`/`);
  };

  const handleSearch = (event) => {
    const searchValue = event.target.value;
    setSearchTerm(searchValue);
    const filtered = announcements.filter(announcement => 
      announcement.title && announcement.title.toLowerCase().includes(searchValue.toLowerCase())
    );
    setFilteredAnnouncements(filtered);
  
    // Update recent searches
    setRecentSearches(prevSearches => {
      const updatedSearches = [searchValue, ...prevSearches];
      return updatedSearches.slice(0, 5); // Keep only the latest 5 searches
    });
  };

  const setSearchTermAndFilter = (search) => {
    setSearchTerm(search);
    const filtered = announcements.filter(announcement =>
      announcement.title && announcement.title.toLowerCase().includes(search.toLowerCase())
    );
    setFilteredAnnouncements(filtered);
  };
  
  

  useEffect(() => {
    const fetchAnnouncements = async () => {
        try {
            const response = await getAnnouncements();
            setAnnouncements(response.data);
        } catch (error) {
            setError(error.response?.data?.message || 'Error occured during fetching the announcements');
        }
    };

    fetchAnnouncements();
}, []);

useEffect(() => {
    console.log(announcements);

    const fetchDataForAnnouncements = async () => {
        for (const announce of announcements) {
            try {
                const announceResponse = await getAnnouncement(announce.id);
                setAnnounceData(announceResponse.data);
            } catch (error) {
                setError(error.announceResponse?.data?.message || 'Error occured during fetching the announcements')
            }
        }
    };
    fetchDataForAnnouncements();
}, [announcements]);

const handleAnnouncementClick = (announcementId) => {
  navigate(`/announcement/${announcementId}`);
};


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
            <a className="nav-link active" aria-current="page" href="#" onClick={handleHomeButton}>
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
            <a className="nav-link" href="https://w3.bilkent.edu.tr/www/kampuste-yasam/ulasim/merkez-kampus-ulasim-programi/">
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
        <span className="caret" >
        <div className="col-md-12">
              </div>
    </span>
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
                <a className="dropdown-item" href="#" onClick={handleProfileClick}>
                My Profile
                </a>
            </li>
            <li>
                <a className="dropdown-item" href="#">
                My Messages
                </a>
            </li>
            <li>
                <a className="dropdown-item" href="#">
                My Posts
                </a>
            </li>
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

    <div className="dashboard-container">
      <aside className="sidebar">
        {/* Categories */}
        <div className="categories">
          <h2>Categories</h2>
          <ul>
            {categories.map(category => (
              <li key={category.id}>
                <Link to={`/posts/${category.name}`}>{category.name}</Link>
              </li>
            ))}
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <div className="content-area">
          {/* Your existing content */}
        </div>
        <div className="announcements-area">
          {/* Announcements */}
          <h2>Announcements</h2>
          <input 
            type="text" 
            placeholder="Search Announcements..." 
            value={searchTerm} 
            onChange={handleSearch} 
          />
          <div className="posts-container">
            {announcements.map(announce => (
              <div 
                key={announce.id} 
                className="announcement" 
                onClick={() => handleAnnouncementClick(announce.announcementID)}
              >
                <h2>{announce.title}</h2>
                <p>{announce.content}</p>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  </>
  );
}

    
  
export default Dashboard;
