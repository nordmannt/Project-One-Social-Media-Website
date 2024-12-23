import React, { useState } from 'react';
import axios from 'axios';

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
                onChange={(e) => setNewComment(e.target.value)}
            />
            <button onClick={handlePostComment}>Post</button>
        </div>
    );
};

export default PostComment;
