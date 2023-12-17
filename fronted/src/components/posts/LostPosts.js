import React, { useState, useEffect } from 'react';
import { getLostPosts, getPost} from '../../api/axiosConfig';
import { useNavigate, Link, useParams } from 'react-router-dom';

const LostPosts = () => {
    const [postData, setPostData] = useState([]);
    //const [imageData, setImageData] = useState([]);
    const { postId } = useParams();
    const [posts, setPosts] = useState([]);
    const [runCount, setRunCount] = useState(0);
    const navigate = useNavigate();
    const [error, setError] = useState(''); // State to handle any error


    useEffect(() => {
        const fetchSellingPosts = async () => {
            try {
                const response = await getLostPosts();
                setPosts(response.data);
            } catch (error) {
                setError(error.response?.data?.message || 'Error occured during fetching the posts');
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
                    // const imageIdTemp = postResponse?.data.imageId;
                    // console.log(imageIdTemp);
                    // const imageResponse = await getBaseImage(imageIdTemp);
                    // setImageData(imageResponse.data);
                    // console.log(imageResponse.data);
                } catch (error) {
                    setError(error.postResponse?.data?.message || 'Error occured during fetching the posts')
                }
            }
        };
    
        fetchDataForPosts();
    }, [posts]);

    const handleHomeButton = () => {
        // Redirect to user's profile page
        navigate(`/dashboard/`);
      };

      const handeLogout = () => {
        navigate(`/`);
      };
    

    
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

    // Render only when both postData and imageData are available
    if (postData) {
        return (

            <>
            <meta charSet="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
    crossOrigin="anonymous"
    />
    <link rel="stylesheet" href="styles.css" />
    <link rel="shortcut icon" href="favicon.png" type="image/png" />
    <title>Title</title>
    <style
    dangerouslySetInnerHTML={{
        __html:
        "\n  .caret {\n    display: inline-block;\n    width: 13px;\n    height: 13px;\n    background-color: lightgray;\n  }\n  .bg-custom-1 {\n    background-color: #85144b;\n  }\n  \n  .bg-custom-2 {\n  background-image: linear-gradient(15deg, #13547a 0%, #80d0c7 100%);\n  }\n\n  .boyut{\n    \n    font-size: 19px;\n  }\n\n  .helper{\n    font-size: 12px;\n  }\n"
    }}
    />
    <nav
    className="navbar navbar-expand-lg navbar-light bg-light"
    style={{
        background:
        "radial-gradient(circle, rgba(238,174,202,1) 0%, rgba(212,221,232,1) 100%)"
    }}
    >
    <div className="container " >
        
        <a className="navbar-brand me-0 me-lg-5" href="#">
        <img
            src="img/lofoufak.png"
            alt=""
            className="rounded-circle d-inline-block align-text-top"
        />
        </a>
        
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav me-auto mb-2 mb-lg-0 boyut">
            <li className="nav-item">
            <a className="nav-link active" aria-current="page" href="#" onClick={handleHomeButton}>
                Home
            </a>
            </li>
            <li className="nav-item">
            <a className="nav-link" href="#">
                Student Friendly Places
            </a>
            </li>
            <li className="nav-item">
            <a className="nav-link" href="http://kafemud.bilkent.edu.tr/monu_eng.html">
                Table D'hote Menu
            </a>
            </li>
            <li className="nav-item">
            <a className="nav-link" href="https://w3.bilkent.edu.tr/bilkent/transportation/">
                Bus Schedule
            </a>
            </li>
            <li className="nav-item">
            <a className="nav-link" href="#">
                Student Clubs
            </a>
            </li>
            
        </ul>
        <div className="form-check form-switch ms-auto mt-3 me-3">
            <label className="form-check-label ms-3" htmlFor="lightSwitch">
            <svg
                xmlns="http://www.w3.org/2000/svg"
                width={25}
                height={25}
                fill="currentColor"
                className="bi bi-brightness-high"
                viewBox="0 0 16 16"
            >
                <path d="M8 11a3 3 0 1 1 0-6 3 3 0 0 1 0 6zm0 1a4 4 0 1 0 0-8 4 4 0 0 0 0 8zM8 0a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 0zm0 13a.5.5 0 0 1 .5.5v2a.5.5 0 0 1-1 0v-2A.5.5 0 0 1 8 13zm8-5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2a.5.5 0 0 1 .5.5zM3 8a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1 0-1h2A.5.5 0 0 1 3 8zm10.657-5.657a.5.5 0 0 1 0 .707l-1.414 1.415a.5.5 0 1 1-.707-.708l1.414-1.414a.5.5 0 0 1 .707 0zm-9.193 9.193a.5.5 0 0 1 0 .707L3.05 13.657a.5.5 0 0 1-.707-.707l1.414-1.414a.5.5 0 0 1 .707 0zm9.193 2.121a.5.5 0 0 1-.707 0l-1.414-1.414a.5.5 0 0 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .707zM4.464 4.465a.5.5 0 0 1-.707 0L2.343 3.05a.5.5 0 1 1 .707-.707l1.414 1.414a.5.5 0 0 1 0 .708z" />
            </svg>
            </label>
            <input
            className="form-check-input"
            type="checkbox"
            id="lightSwitch"
            />
        </div>
        
        

        <div className="dropdown form-switch ms-auto me-auto mb-auto mb-lg-1 boyut" style={{ textAlign: 'center',   marginBottom: '5px', marginTop: '5px' }}>
        <span className="caret" >
        <div className="col-md-12">
              </div>
    </span>
    </div>
    <div className="dropdown form-switch ms-auto me-auto mb-auto mb-lg-1 boyut" style={{ textAlign: 'center',   marginBottom: '5px', marginTop: '5px' }}>
            <img
            className="rounded-circle dropdown-toggle"
            width={40}
            marginRight={10}
            marginLeft={10}
            src="https://avatars.githubusercontent.com/u/72274639?v=4"
            id="dropdownMenuButton1"
            data-bs-toggle="dropdown"
            aria-expanded="false"
            />
            
            <span
            className="caret"
            id="dropdownMenuButton1"
            data-bs-toggle="dropdown"
            aria-expanded="false"
            >
            
            </span>
            <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li>
                <hr className="dropdown-divider" />
            </li>
            <li>
                <a className="dropdown-item" href="#" onClick={handeLogout}>
                Logout
                </a>
            </li>
            </ul>
        </div>
        </div>
    </div>
    </nav>
            <div>
                <h1>Lost Posts</h1>
                <div className="posts-container">
                    {posts.map(post => (
                        <div key={post.id} className="post">
                            {/* Link to the post's page */}
                            <h2>
                                <Link to={`/post/${post.id}`}>{post.title}</Link>
                            </h2>
                            <p>{post.content}</p>
                            {/* Display the sender's name with a link to their profile */}
                            {post.sender && (
                                <p>
                                    <Link to={`/ProfilePage/${post.senderId}`}>
                                        {post.sender.name}
                                    </Link>
                                </p>
                            )}
                        </div>
                    ))}
                </div>
            </div>
            </>
        );
    } else {
        // Loading state or alternate content if postData or imageData is not available yet
        return <p>Loading...</p>;
    }
};

export default LostPosts;
