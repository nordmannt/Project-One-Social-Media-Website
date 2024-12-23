import React, {useState} from 'react'
import axios from 'axios';

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
    placeholder="Share something with your chums!"
    value={newMessage}
    onChange={(e) => setNewMessage(e.target.value)}
    />
    <button onClick={handlePostMessage}>Post</button>
    </div>
  );
};

export default PostMessage