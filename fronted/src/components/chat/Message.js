import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getAllMessages, sendMessage } from '../../api/axiosConfig'; 

const Message = () => {
  const [chat, setChat] = useState({ messages: [], participants: [] });
  const { chatId } = useParams(); // Get chatId from URL

  useEffect(() => {
    // Fetch chat details including messages and participants
    getAllMessages(chatId)
      .then(response => {
        setChat(response.data);
      })
      .catch(error => {
        console.error('Error fetching chat details:', error);
      });
  }, [chatId]);

  // Handler for sending a message
  const handleSendMessage = (content) => {
    // Placeholder for sending message logic
    sendMessage(chatId, content).then(() => {
      // Refresh chat or append new message to chat.messages
    }).catch(error => {
      console.error('Error sending message:', error);
    });
  };

  return (
    <div>
      <h2>Chat</h2>
      <div className="chat-window">
        {chat.messages.map((message, index) => (
          <div key={index} className={`message ${message.sentByCurrentUser ? 'sent' : 'received'}`}>
            {message.content}
          </div>
        ))}
      </div>
      <div className="send-message-form">
        <input type="text" placeholder="Type a message..." />
        <button onClick={() => handleSendMessage()}>Send</button>
      </div>
    </div>
  );
};

export default Message;
