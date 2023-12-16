import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { createPost } from '../../api/axiosConfig';
import '../../themes/styles.css'

const MakePost = () => {
  const [postType, setPostType] = useState('');
  const navigate = useNavigate();
  const [error, setError] = useState('');
  const { userId } = useParams();

 
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
        let response;
        response = await cre
        switch (postType) {
          case 'sell':
            navigate(`/make-sell-post/${userId}`);
            console.log('sell');
            break;
          case 'loan':
            navigate(`/make-loan-post/${userId}`);
            console.log('loan');
            break;
          case 'lost':
            navigate(`/make-lost-post/${userId}`);
            console.log('lost');
            break;
          case 'found':
            navigate(`/make-found-post/${userId}`);
            console.log('found');
            break;
          case 'free':
            navigate(`/make-free-post/${userId}`);
            console.log('free');
            break;
          default:
            // Handle default case or show an error
            setError('An error occurred during selecting post category');
            return;
        }
        console.log('navigating to post type');
        // Handle success (e.g., show success message, redirect to another page, etc.)
      } catch (error) {
        setError('An error occurred during selecting post category');
      }
  };

  return (
    <>
    <div id="root" className="card" style={{ margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
  <div className="container">
      <h1 className="mt-4 text-center">Make a Post</h1>
      <div className="card custom-card">
        <div className="card-body">
          <p className="card-text">Please pay attention to Bilkent rules in your definition.</p>
          <form onSubmit={handleSubmit} className="myform">

            <div className="form-group mt-2">
              <label htmlFor="postType"><b>Post Type</b></label>
              <select id="postType" className="form-control" value={postType} onChange={(e) => setPostType(e.target.value)}>
                <option value="">Select Post Type</option>
                <option value="sell">Sell</option>
                <option value="loan">Loan</option>
                <option value="lost">Lost</option>
                <option value="found">Found</option>
                <option value="free">Free</option>
              </select>
            </div>

            <div className="form-group mt-2">
              <button type="submit" className="form-control btn btn-outline-success btn-block">Send Post</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    </div>
  </>
  );
};

export default MakePost;
