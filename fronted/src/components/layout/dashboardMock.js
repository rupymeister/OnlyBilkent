import React, { useState, useEffect } from 'react';
import { getLoanPost, getPost, getImage } from '../../api/axiosConfig';
import { useParams } from 'react-router-dom';


const Dashboard = () => {
    const [postData, setPostData] = useState([]);
    const [imageData, setImageData] = useState([]);

    const { postId } = useParams();
    const [posts, setPosts] = useState([]);
    useEffect(() => {
        const fetchSellingPosts = async () => {
            try {
                const response = await getLoanPost();
                setPosts(response.data); // Assume response.data is the array of posts
            } catch (error) {
                console.error('Error fetching selling posts:', error);
                // Handle error appropriately
            }
        };

        fetchSellingPosts();
    }, []);

     useEffect(() => {
        getPost(postId)
      .then(response => {
        setPostData(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the post data:', error);
      });
    }, [postId]);

    const imageId = postData?.imageId;

    useEffect(() => {
        const fetchImage = async () => {
            try {
                const response = await getImage(imageId);
                setImageData(response.data); // Assume response.data is the array of posts
            } catch (error) {
                console.error('Error fetching image:', error);
                // Handle error appropriately
            }
        };

        fetchImage();
    }, []);

    console.log(imageData);




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
