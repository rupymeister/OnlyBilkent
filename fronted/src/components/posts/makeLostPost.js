import React, { useState, useEffect } from 'react';
import { createLostPost, getPost } from '../../api/axiosConfig';
import { useParams, useNavigate } from 'react-router-dom';
import '../../themes/styles.css'

const MakeLoanPost = () => {
   const [title, setTitle] = useState('');
   const [content, setContent] = useState('');
   const [category, setCategory] = useState('');
   const [image, setImage] = useState(null);
   const { postId } = useParams();
   const navigate = useNavigate();
   const [error, setError] = useState('');
   const [postData, setPostData] = useState(null);
   const [successMessage, setSuccessMessage] = useState('');

   const handleImageChange = (event) => {
    setImage(event.target.files[0]);
  };
  
  useEffect(() => {
    getPost(postId)
      .then(response => {
        setPostData(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the post data:', error);
      });
  }, [postId]);

    const userId = postData?.senderId || 'Default Id';

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            console.log(postId); // This is the postId of the post that was created in the previous step
            const response = await createLostPost(postId, title, content, category);
            
            console.log("Created the post");
            setSuccessMessage('Your Post has been made succesfully!');
        } catch (err) {
            setError(err.response?.data?.message || 'An error occurred during making the post');
        }
    }

    const handleGoBack = () => {
      navigate(`/student-profile/${userId}`);
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
              <label htmlFor="postdetail"><b>Post Content</b></label>
              <textarea className="form-control" name="postdetail" id="postdetail" rows="3" placeholder="Please enter your post details" value={content} onChange={(e) => setContent(e.target.value)}></textarea>
            </div>

            <div className="form-group mt-2">
              <label htmlFor="category"><b>Category</b></label>
              <select id="category" className="form-control" value={category} onChange={(e) => setCategory(e.target.value)}>
                <option value="">Select Category</option>
                <option value="ELECTRONICS">Electronics</option>
                <option value="BOOKS">Books</option>
                <option value="DORMITORY">Dormitory</option>
                <option value="OTHER">Others</option>
              </select>
            </div>

            <div className="form-group mt-2">
              <button type="submit" className="form-control btn btn-outline-success btn-block">Send Post</button>
            </div>

            <div className="form-group mt-2">
              <button type="button" className="btn btn-secondary" onClick={handleGoBack}>Go Back to Profile</button>
            </div>
            {error && <div className="alert alert-danger">{error}</div>}
            {successMessage && <div className="alert alert-success">{successMessage}</div>}
          </form>
        </div>
      </div>
    </div>
    </div>
  </>
  );
};

export default MakeLoanPost;