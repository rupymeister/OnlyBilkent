import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getPost } from '../../api/axiosConfig';
import { useParams } from 'react-router-dom';

const PostsPage = () => {
    const [postData, setPostData] = useState(null);
    const { postId } = useParams();
    const navigate = useNavigate();
    const [posts, setPosts] = useState(null);

    useEffect(() => {
        getPost(postId)
          .then(response => {
            setPostData(response.data);
          })
          .catch(error => {
            console.error('There was an error fetching the post data:', error);
          });
      }, [postId]);

    return (
        <div className="container">
            <div className="card" style={{ margin: 'auto', background: 'radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)' }}>
                <h2 className="card-title" style={{ textAlign: 'center', color: '#333', fontSize: '24px', fontWeight: 'bold', marginBottom: '10px', marginTop: '10px' }}>Posts</h2>
                <div className="card-body">
                    {posts.map(post => (
                        <div key={post.id} className="card" style={{ marginBottom: '10px', padding: '10px', background: '#fff' }}>
                            <div style={{ display: 'flex', alignItems: 'center', marginBottom: '10px' }}>
                                <img src={post.sender.profilePic} alt="Profile" style={{ width: '50px', height: '50px', borderRadius: '50%' }} />
                                <p style={{ marginLeft: '10px', cursor: 'pointer', color: '#007bff' }} onClick={() => navigate(`/profile/${post.sender.id}`)}>{post.sender.name}</p>
                            </div>
                            <h5 className="card-title">{post.title} ({post.type})</h5>
                            <p className="card-text">{post.content}</p>
                            <p className="card-text"><small className="text-muted">Category: {post.category}</small></p>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default PostsPage;
