import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

const NavComponent = () => {
  return (
    <div className="container">
      <h1 className="m-4">Bilkent University Student/Alumni Communication System</h1>
      <div className="card" style={{ width: '26rem', margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
        <img src="img/logo.png" className="card-img-top mt-3" style={{ width: '50%', margin: 'auto' }} alt="Bilkent Uni Logo" />
        <div className="card-body">
          <form action="post" className="myform">
            {/* Form contents like email and password fields */}
            <div className="form-group mt-2">
              <Link to="api/registration" className="btn btn-success">Sign in as Student</Link>
            </div>
            <div className="form-group mt-2">
              <Link to="api/login-board" className="btn btn-success">Sign in as Student Club Representative</Link>
            </div>
            <div className="form-group mt-2">
              <Link to="api/login/asStudent" className="btn btn-primary">Login</Link>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default NavComponent;

