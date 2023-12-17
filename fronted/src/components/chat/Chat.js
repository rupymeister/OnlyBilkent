import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getUserChats } from '../../api/axiosConfig'; // Replace with your actual API call

const Chats = () => {
  const [chats, setChats] = useState([]);
  const { userId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch chats for the user
    getUserChats(userId)
      .then(response => {
        setChats(response.data);
      })
      .catch(error => {
        console.error('Error fetching chats:', error);
      });
  }, [userId]);

  const handleChatSelect = (chatId) => {
    // Navigate to the chat messages page
    navigate(`/messages/${chatId}`);
  };

  

  return (
    <div>
      <h2>My Chats</h2>
      <ul>
        {chats.map(chat => (
          <li key={chat.chatId} onClick={() => handleChatSelect(chat.id)}>
            Chat with {chat.receiverId} {/* Display other participant's name */}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Chats;
