import React, { useState } from 'react';
import { createUserPost } from '../../api/axiosConfig';
import { useParams, useNavigate } from 'react-router-dom';
import '../../themes/styles.css'

const MakePost = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [postType, setPostType] = useState('');
  const [category, setCategory] = useState('');
  const [image, setImage] = useState(null);
  const { userId } = useParams();
  const navigate = useNavigate();

  const handleImageChange = (event) => {
    setImage(event.target.files[0]);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    
    const formData = new FormData();
    formData.append('title', title);
    formData.append('content', content);
    formData.append('postType', postType);
    formData.append('category', category);
    if (image) {
      formData.append('image', image);
    }

    try {
      const response = await createUserPost(userId, formData); // Update this call based on your backend's requirement
      console.log('Post created:', response.data);
      // Handle success (e.g., show success message, redirect to another page, etc.)
    } catch (error) {
      console.error('Error creating post:', error);
      // Handle error (e.g., show error message)
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
              <label htmlFor="formFileLg" className="form-label"><b>Post Image</b></label>
              <input className="form-control form-control-lg" id="formFileLg" type="file" onChange={handleImageChange} />
            </div>

            <div className="form-group mt-2">
              <label htmlFor="postheader"><b>Post Title</b></label>
              <input id="postheader" className="form-control" type="text" name="postheader" placeholder="Please enter your post header" value={title} onChange={(e) => setTitle(e.target.value)} />
            </div>

            <div className="form-group mt-2">
              <label htmlFor="postdetail"><b>Post Definition</b></label>
              <textarea className="form-control" name="postdetail" id="postdetail" rows="3" placeholder="Please enter your post details" value={content} onChange={(e) => setContent(e.target.value)}></textarea>
            </div>

            <div className="form-group mt-2">
              <label htmlFor="postType"><b>Post Type</b></label>
              <select id="postType" className="form-control" value={postType} onChange={(e) => setPostType(e.target.value)}>
                <option value="">Select Post Type</option>
                <option value="sell">Sell</option>
                <option value="borrow">Borrow</option>
                <option value="lost">Lost</option>
                <option value="found">Found</option>
                <option value="free">Free</option>
              </select>
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

export default MakePost;
