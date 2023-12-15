import React, { useState, useEffect } from 'react';
import {getAnnouncements } from '../../axios.Config';
import axios from 'axios';


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
    axios.get('YOUR_API_URL')
      .then(response => {
        setPosts(response.data);
      })
      .catch(error => {
        console.error('Error fetching posts:', error);
      });
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