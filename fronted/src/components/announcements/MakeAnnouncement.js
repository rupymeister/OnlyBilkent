import React, { useState } from 'react';
import { createAnnouncement } from '../../api/axiosConfig';
import { useParams, useNavigate } from 'react-router-dom';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

import '../../themes/styles.css'

const MakeAnnouncement = () => {
    console.log("Found page")
   const [title, setTitle] = useState('');
   const [content, setContent] = useState('');
   const [image, setImage] = useState(null);
   const [date, setDate] = useState(new Date()); // State for date
   const { userId } = useParams();
   const navigate = useNavigate();
   const [error, setError] = useState('');
   const [successMessage, setSuccessMessage] = useState('');

   const handleImageChange = (event) => {
    setImage(event.target.files[0]);
  };

  const handleDateChange = (date) => {
    setDate(date);
};

 
    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const formattedDate = date.toISOString();
            const response = await createAnnouncement(userId, title, content, formattedDate);
            console.log("Created the announcement");
            setSuccessMessage('Your Announcement has been made succesfully!');
        } catch (err) {
            setError(err.response?.data?.message || 'An error occurred during making the post');
        }
    }

    const handleGoBack = () => {
      navigate(`/board-profile/${userId}`);
    };

  return (
    <>
    <div id="root" className="card" style={{ margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
    <div className="container">
      <h1 className="mt-4 text-center">Make a Announcement</h1>
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
                <label htmlFor="datePicker"><b>Date and Time of Activity</b></label>
                <DatePicker
                    id="datePicker"
                    selected={date}
                    onChange={handleDateChange}
                    showTimeSelect
                    dateFormat="Pp"
                    className="form-control"
                />
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

export default MakeAnnouncement;
