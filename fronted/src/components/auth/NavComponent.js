import React from 'react';
import { Link } from 'react-router-dom';

const NavComponent = () => {
  return (
    <nav>
      {/* Other navigation links */}
      <Link to="/login">Login</Link>
      <Link to="/register">Sign in</Link>
    </nav>
  );
};

export default NavComponent;
