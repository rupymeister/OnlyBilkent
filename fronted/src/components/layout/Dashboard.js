import React, { useState, useEffect } from 'react';
import {getAnnouncements } from '../../axios.Config';
import axios from 'axios';
import Category from './Category';
import Announcement from './Announcement';
import Post from './Post';


function Dashboard() {
  const [categories, setCategories] = useState([]);
  const [announcements, setAnnouncements] = useState([]);
  const [posts, setPosts] = useState([]);


  useEffect(() => {
    // Fetch categories
    axios.get('YOUR_CATEGORIES_API_URL')
      .then(response => {
        setCategories(response.data);
      })
      .catch(error => {
        console.error('Error fetching categories:', error);
      });

    // Fetch announcements
    axios.get('YOUR_ANNOUNCEMENTS_API_URL')
      .then(response => {
        setAnnouncements(response.data);
      })
      .catch(error => {
        console.error('Error fetching announcements:', error);
      });

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

  return (
    <div className="dashboard">
      <aside className="sidebar">
        <h2>Categories</h2>
        <ul>
          {categories.map(category => (
            <li key={category.id}>{category.name}</li>
          ))}
        </ul>
        <h2>Announcements</h2>
        <ul>
          {announcements.map(announcement => (
            <li key={announcement.id}>{announcement.title}</li>
          ))}
        </ul>
      </aside>
      {posts.map((post, index) => (
        <div key={index}>
          <h2>{post.title}</h2>
          <p>{post.content}</p>
        </div>
      ))}
    </div>
  );
}

export default Dashboard;