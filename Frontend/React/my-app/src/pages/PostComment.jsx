import React, { useState } from 'react';
import axios from 'axios';
import { theme } from "../styles/Themes";
import {
  PrimaryButton
  } from "../components";
const PostComment = ({ messageId, onCommentSuccess }) => {
    const [newComment, setNewComment] = useState('');
    
    const handlePostComment = async () => {
        if (newComment.trim() === '') {
            alert('Comment cannot be empty!');
            return;
        }

        try {
            await axios.post(`http://localhost:8080/api/auth/comments/${messageId}`, {
                commentText: newComment,
                commentedBy: 1001, // Replace with actual user ID
            });
            setNewComment(''); // Clear the input field after successful submission
            onCommentSuccess(); // Notify parent component of successful submission
        } catch (err) {
            console.error('Error posting comment', err);
            alert('Failed to post comment');
        }
    };

    return (
        <div className="post-comment-form">
            <input
                type="text"
                placeholder="Share a comment"
                value={newComment}
                style={(styles.textArea)}
                onChange={(e) => setNewComment(e.target.value)}
            />
            <PrimaryButton onClick={handlePostComment}>Post</PrimaryButton>
        </div>
    );
};
const styles = {textArea: {
    width: "50%",
    padding: "10px",
    fontSize: "1rem",
    borderRadius: "3px",
    border: "1px solid #ccc",
    resize: "none",
    marginBottom: "5px",
    marginRight: "15px",
  },
};
export default PostComment;
