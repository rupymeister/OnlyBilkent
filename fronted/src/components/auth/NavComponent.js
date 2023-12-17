import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS
import logo from '../assets/logo.png';

const NavComponent = () => {
  return (
    <div className="container">
      <style>
        {`
          body {
            
            background: radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%);
          }
        `}
      </style>
      <div className="card" style={{ margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
      <h2 className="card-title" style={{ textAlign: 'center', color: '#333', fontSize: '48px', fontWeight: 'bold', marginBottom: '10px', marginTop: '10px' }}>OnlyBilkent</h2>
      <img src={logo} className="card-img-top mt-3" style={{ width: '50%', margin: 'auto' }} alt="Bilkent Uni Logo" />
      
      <h1 className="m-4"></h1>
      <div className="card" style={{ width: '26rem', margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
      <h2 className="card-title" style={{ textAlign: 'center', color: '#333', fontSize: '24px', fontWeight: 'bold', marginBottom: '10px', marginTop: '10px' }}>For better Bilkenter!</h2>
        <div className="card-body">
          <form action="post" className="myform">
            {/* Form contents like email and password fields */}
            <div className="form-group mt-2">
              <Link to="api/registration" className="btn btn-success">Register</Link>
            </div>
            <div className="form-group mt-2">
              <Link to="api/login-board" className="btn btn-success">Student Club Representative Sign in</Link>
            </div>
            <div className="form-group mt-2">
              <Link to="api/login/asStudent" className="btn btn-primary">Login</Link>
            </div>
          </form>
        </div>
      </div>
      </div>
    </div>
  );
};

export default NavComponent;

