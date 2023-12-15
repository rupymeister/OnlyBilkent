import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getAnnouncements } from '../../axios.Config';

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

    fetchAnnouncements();
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
