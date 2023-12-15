import React from 'react';
import { Link } from 'react-router-dom';

const NavComponent = () => {
  return (
    <nav>
      {/* Other navigation links */}
      <Link to="/api/login/asStudent">Login    </Link>
      <Link to="/registration">   Sign in as Student</Link>
      <Link to="/api/login-board">     Sign in as Student Club Representative</Link>
    </nav>
  );
};

export default NavComponent;
