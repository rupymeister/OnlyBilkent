import React, { useState, useEffect } from 'react';

function Dashboard() {
  const [categories, setCategories] = useState([]);
  const [announcements, setAnnouncements] = useState([]);

  useEffect(() => {
    // Fetch categories and announcements here
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
      </aside>
      <main className="main-content">
        <h2>Announcements</h2>
        {announcements.map(announcement => (
          <article key={announcement.id}>
            <h3>{announcement.title}</h3>
            <p>{announcement.content}</p>
          </article>
        ))}
      </main>
    </div>
  );
}

export default Dashboard;
