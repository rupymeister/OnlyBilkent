import React, { useState, useEffect } from 'react';
import {getAnnouncements } from '../../axios.Config';
import axios from 'axios';
import Category from './Category';
import Announcement from './Announcement';
import Post from './Post';
import { Link } from 'react-router-dom';


function Dashboard() {
  const [categories, setCategories] = useState([
    // Assuming these are your categories; replace with API call if needed
    { id: 1, name: 'Selling' },
    { id: 2, name: 'Borrowing' },
    { id: 3, name: 'Lost and Found' }
  ]);
  const [announcements, setAnnouncements] = useState([]);
  const [filteredAnnouncements, setFilteredAnnouncements] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    const fetchAnnouncements = async () => {
      try {
        const response = await getAnnouncements();
        setAnnouncements(response.data);
        setFilteredAnnouncements(response.data); // Initialize filtered announcements
      } catch (error) {
        console.error('Error fetching announcements:', error);
      }
    };

    // Fetch posts 
    const createPost = (userId, postType, category) => {
      axios.post(`/createPost1/${userId}`, {
        userId,
        postType,
        category
      })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error creating post:', error);
      });
    };

    const createLoanPost = (postId, title, content, borrowUntilDate, loanPricePerTime) => {
      axios.put(`/createLoanPost/${postId}`, {
        title,
        content,
        borrowUntilDate,
        loanPricePerTime
      })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error creating loan post:', error);
      });
    };

    const createBorrowPost = (postId, title, content, borrowUntilDate, borrowPricePerTime) => {
      axios.put(`/createBorrowPost/${postId}`, {
        title,
        content,
        borrowUntilDate,
        borrowPricePerTime
      })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error creating borrow post:', error);
      });
    }

    const createSellPost = (postId, title, content, price) => {
      axios.put(`/createSellPost/${postId}`, {
        title,
        content,
        price
      })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error creating sell post:', error);
      });
    }

    const createFreePost = (postId, title, content) => {
      axios.put(`/createFreePost/${postId}`, {
        title,
        content
      })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error creating free post:', error);
      });
    }

    const deletePost = (postId) => {
      axios.delete(`/deletePost/${postId}`)
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error deleting post:', error);
      });
    }

    const editPost = (postId, title, content) => {
      axios.put(`/editPost/${postId}`, {
        title,
        content
      })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error editing post:', error);
      });
    }

    const editIsPostActive = (postId, isPostActive) => {
      axios.put(`/editIsPostActive/${postId}`, {
        isPostActive
      })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error editing isPostActive:', error);
      });
    }

    const searchByTitle = (title) => {
      axios.get(`/searchByTitle/${title}`)
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error searching by title:', error);
      });
    }

    const searchByContent = (content) => {
      axios.get(`/searchByContent/${content}`)
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error searching by content:', error);
      });
    }

    


  }, []);

  const handleSearch = (event) => {
    const searchValue = event.target.value;
    setSearchTerm(searchValue);
    const filtered = announcements.filter(announcement => 
      announcement.title.toLowerCase().includes(searchValue.toLowerCase())
    );
    setFilteredAnnouncements(filtered);
  };

  return (
    <div className="dashboard">
      <aside className="sidebar">
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
        <div className="announcements">
          <h2>Announcements</h2>
          <input 
            type="text" 
            placeholder="Search Announcements..." 
            value={searchTerm} 
            onChange={handleSearch} 
          />
          <ul>
            {filteredAnnouncements.map(announcement => (
              <li key={announcement.id}>{announcement.title}</li>
            ))}
          </ul>
        </div>
      </aside>
      {/* Other content can be added here */}
    </div>
  );
}

export default Dashboard;
