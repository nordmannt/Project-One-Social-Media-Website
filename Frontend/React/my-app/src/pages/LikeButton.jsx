import React, { useState } from 'react';
import axios from 'axios';

const LikeButton = ({ type, id, initialLikes, userId }) => {
    const [likes, setLikes] = useState(initialLikes || 0);
    const [liked, setLiked] = useState(false);

    const user = userId || localStorage.getItem("userId"); // Fallback to localStorage

    const handleLike = async () => {
        if (!user) {
            console.error('User ID is undefined.');
            return;
        }

        try {
            if (liked) {
                await axios.delete(`http://localhost:8080/api/auth/likes/${type}/${id}?likedBy=${user}`);
                setLikes(likes - 1);
            } else {
                await axios.post(`http://localhost:8080/api/auth/likes/${type}/${id}?likedBy=${user}`);
                setLikes(likes + 1);
            }
            setLiked(!liked);
        } catch (error) {
            console.error('Error updating like:', error);
        }
    };

    return (
        <button onClick={handleLike}>
            {liked ? 'Unlike' : 'Like'} ({likes})
        </button>
    );
};

export default LikeButton;
