import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { createPost } from '../../api/axiosConfig';
import '../../themes/styles.css'

const MakePost = () => {
  const [postType, setPostType] = useState('');
  const [postData, setPostData] = useState('');
  const navigate = useNavigate();
  const [error, setError] = useState('');
  const { userId } = useParams();

 
  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
        // Call API to create a basic post and get postId
        console.log(postType);
        const response = await createPost(postType, userId);
        const postId = response.data; // Assuming response contains postId
        console.log(postId);
        
        switch (postType) {
          case 'SALE':
            navigate(`/make-sell-post/${postId}`);
            console.log('sell');
            break;
          case 'LOAN':

            navigate(`/make-loan-post/${postId}`);
            console.log('loan');
            break;
          case 'LOST':
            navigate(`/make-lost-post/${postId}`);
            console.log('lost');
            break;
          case 'FOUND':
            navigate(`/make-found-post/${postId}`);
            console.log('found');
            break;
          case 'FREE':
            navigate(`/make-free-post/${postId}`);
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
        console.error('Error creating post:', error.response ? error.response.data : 'Unknown error');
        setError('An error occurred during selecting post category');
      }
  };

  const handleGoBack = () => {
    navigate(`/board-profile/${userId}`);
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
                <option value="SALE">Sell</option>
                <option value="LOAN">Loan</option>
                <option value="LOST">Lost</option>
                <option value="FOUND">Found</option>
                <option value="FREE">Free</option>
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
