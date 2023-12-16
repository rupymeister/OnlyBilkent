import React, { useState } from 'react';
import { createFoundPost } from '../../api/axiosConfig';
import { useParams, useNavigate } from 'react-router-dom';
import '../../themes/styles.css'

const MakeFoundPost = () => {
    console.log("Found page")
   const [title, setTitle] = useState('');
   const [content, setContent] = useState('');
   const [category, setCategory] = useState('');
   const [image, setImage] = useState(null);
   const { userId } = useParams();
   const navigate = useNavigate();
   const [error, setError] = useState('');

   const handleImageChange = (event) => {
    setImage(event.target.files[0]);
  };

 
    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await createFoundPost(userId, content, image, category);
            console.log("Created the post");
            navigate('/student-profile/:userId'); // Navigates to student profile page
        } catch (err) {
            setError(err.response?.data?.message || 'An error occurred during making the post');
        }
    }

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
              <label htmlFor="formFileLg" className="form-label"><b>Post Image</b></label>
              <input className="form-control form-control-lg" id="formFileLg" type="file" onChange={handleImageChange} />
            </div>

            <div className="form-group mt-2">
              <label htmlFor="postheader"><b>Post Title</b></label>
              <input id="postheader" className="form-control" type="text" name="postheader" placeholder="Please enter your post header" value={title} onChange={(e) => setTitle(e.target.value)} />
            </div>

            <div className="form-group mt-2">
              <label htmlFor="postdetail"><b>Post Content</b></label>
              <textarea className="form-control" name="postdetail" id="postdetail" rows="3" placeholder="Please enter your post details" value={content} onChange={(e) => setContent(e.target.value)}></textarea>
            </div>

            <div className="form-group mt-2">
              <label htmlFor="category"><b>Category</b></label>
              <select id="category" className="form-control" value={category} onChange={(e) => setCategory(e.target.value)}>
                <option value="">Select Category</option>
                <option value="electronics">Electronics</option>
                <option value="books">Books</option>
                <option value="dormitory">Dormitory</option>
                <option value="others">Others</option>
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

export default MakeFoundPost;