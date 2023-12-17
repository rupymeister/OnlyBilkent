import React, { useState, useEffect } from 'react';
import { getLoanPost, getPost, getBaseImage } from '../../api/axiosConfig';
import { useParams } from 'react-router-dom';

const Dashboard = () => {
    const [postData, setPostData] = useState([]);
    const [imageData, setImageData] = useState([]);
    const { postId } = useParams();
    const [posts, setPosts] = useState([]);
    const [runCount, setRunCount] = useState(0);

    useEffect(() => {
        const fetchSellingPosts = async () => {
            try {
                const response = await getLoanPost();
                setPosts(response.data);
            } catch (error) {
                console.error('Error fetching selling posts:', error);
                // Handle error appropriately
            }
        };

        fetchSellingPosts();
    }, []);

    useEffect(() => {
        console.log(posts);
    
        const fetchDataForPosts = async () => {
            for (const post of posts) {
                try {
                    const postResponse = await getPost(post.id);
                    setPostData(postResponse.data);
                    console.log(postResponse.data);
                    console.log(post.id)
                    const imageIdTemp = postResponse?.data.imageId;
                    console.log(imageIdTemp);
                    const imageResponse = await getBaseImage(imageIdTemp);
                    setImageData(imageResponse.data);
                    console.log(imageResponse.data);
                } catch (error) {
                    console.error('Error fetching post or image data:', error);
                }
            }
        };
    
        fetchDataForPosts();
    }, [posts]);
    

    
    // Update runCount whenever postId or posts change
    useEffect(() => {
        setRunCount((prevRunCount) => prevRunCount + 1);
    }, [postId, posts]);

    // Fetch post details based on the postId when postId changes
    useEffect(() => {
        console.log('Fetching post details. Run count:', runCount);

        if (postId && posts.length > 0) {
            getPost(postId)
                .then(response => {
                    setPostData(response.data);
                })
                .catch(error => {
                    console.error('There was an error fetching the post data:', error);
                });
        }
    }, [postId, posts, runCount]); // Include runCount in the dependencies


    const imageId = postData?.imageId;
    console.log(imageId);


    console.log(imageData);
    // Render only when both postData and imageData are available
    if (postData && imageData) {
        return (
            <div>
                <h1>Selling Posts</h1>
                <div className="posts-container">
                    {posts.map(post => (
                        <div key={post.id} className="post">
                            <h2>{post.title}</h2>
                            <p>{post.content}</p>
                            {imageData && (
                                <img src={`data:image/jpeg;base64,${imageData}`} alt={post.title} style={{ maxWidth: '100%' }} />
                            )}
                            {/* Additional post details here */}
                        </div>
                    ))}
                </div>
            </div>
        );
    } else {
        // Loading state or alternate content if postData or imageData is not available yet
        return <p>Loading...</p>;
    }
};

export default Dashboard;
