import React, {useState} from 'react'
import axios from 'axios';
import { theme } from "../styles/Themes";
import {
  PrimaryButton,
} from "../components";
const PostMessage = ({onPostSuccess}) => {
    const [newMessage, setNewMessage] = useState('');
    

    const handlePostMessage = async() =>{
        if(newMessage.trim() === ''){
            alert('Message cannot be empty!');
            return;
        }

        try{
            await axios.post('http://localhost:8080/api/auth/messages', {
                messageText:  newMessage,
                postedBy: 1001
            });
            setNewMessage('');
            onPostSuccess();
        }catch(err){
            console.error('Error posting message', err);
            alert('Failed to post message');
        }
    };

  return (
    <div className ="post-message-form">
    <input 
    type="text"
    placeholder="Share!"
    value={newMessage}
    onChange={(e) => setNewMessage(e.target.value)}
    style={(styles.textArea)}
    />
    <PrimaryButton onClick={handlePostMessage}>Post</PrimaryButton>
    </div>
  );
};


const styles = {textArea: {
    width: "90%",
    padding: "10px",
    fontSize: "1rem",
    borderRadius: "8px",
    border: "1px solid #ccc",
    resize: "none",
    marginBottom: "10px",
  },
};
export default PostMessage

