import React from 'react';
import { Link } from 'react-router-dom';

const NavComponent = () => {
  return (
    <nav>
      {/* Other navigation links */}
      <Link to="api/login/asStudent" style={linkStyle}>Login</Link>
      <Link to="api/registration" style={linkStyle}>Sign in as Student</Link>
      <Link to="api/login-board" style={linkStyle}>Sign in as Student Club Representative</Link>
    </nav>
  );
};

// Example link style (you can customize these styles)
const linkStyle = {
  marginRight: '10px', // Add any additional styles you need
  color: '#007BFF',   // Blue color for the first link
  textDecoration: 'none', // Remove default underline
};

export default NavComponent;
