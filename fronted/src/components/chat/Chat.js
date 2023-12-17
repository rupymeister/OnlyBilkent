import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getUserChats } from '../../api/axiosConfig'; 

const Chats = () => {
  const [chats, setChats] = useState([]);
  const [error, setError] = useState(null); // Added for error handling
  const { userId } = useParams();
  const navigate = useNavigate();

  console.log(userId);

  useEffect(() => {
    getUserChats(userId)
      .then(response => {
        setChats(response.data);
      })
      .catch(error => {
        console.error('Error fetching chats:', error);
        setError(error); // Set error state
      });
  }, [userId]);

  const handleChatSelect = (chatId) => {
    navigate(`/messages/${chatId}`);
  };

  if (error) {
    return <div>Error loading chats.</div>; // Display error message
  }

  return (
    <div>
      <h2>My Chats</h2>
      <ul>
        {chats.map(chat => (
          <li key={chat.id} onClick={() => handleChatSelect(chat.id)}>
            Chat with {chat.receiverName}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Chats;
