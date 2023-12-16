import React, { useState, useEffect } from 'react';
import { getSellingPosts } from '../../api/axiosConfig';

const Dashboard = () => {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        const fetchSellingPosts = async () => {
            try {
                const response = await getSellingPosts();
                setPosts(response.data); // Assume response.data is the array of posts
            } catch (error) {
                console.error('Error fetching selling posts:', error);
                // Handle error appropriately
            }
        };

        fetchSellingPosts();
    }, []);

    return (
        <div>
            <h1>Selling Posts</h1>
            <div className="posts-container">
                {posts.map(post => (
                    <div key={post.id} className="post">
                        <h2>{post.title}</h2>
                        <p>{post.content}</p>
                        {post.imageBase64 && (
                            <img src={`data:image/jpeg;base64,${post.imageBase64}`} alt={post.title} style={{ maxWidth: '100%' }} />
                        )}
                        {/* Additional post details here */}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Dashboard;
