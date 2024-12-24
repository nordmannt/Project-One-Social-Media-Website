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
        <button onClick={handleLike} style={styles.yellowLikeButton}>
            {liked ? 'Unlike' : 'Like'} ({likes})
            
        </button>
    );
};

const styles = {
 
    yellowLikeButton: {
        float: "right",
        padding: "5px 10px", // Small size
        backgroundColor: "rgba(255, 215, 0, 0.8)", // Yellow with opacity
        color: "#333", // Dark text for contrast
        border: "none",
        borderRadius: "12px", // Rounded corners
        cursor: "pointer",
        fontWeight: "bold",
        fontSize: "0.85rem", // Slightly smaller text
        boxShadow: "0 1px 3px rgba(0, 0, 0, 0.2)", // Subtle shadow for depth
        transition: "all 0.3s ease", // Smooth transition on hover
      },
  likeButtonContainer: {
    marginLeft: "auto", // Push the button to the right
  },
};
export default LikeButton;
