import React, { useState, useEffect } from 'react';
import axios from 'axios';

const FriendsList = () => {
  const [friends, setFriends] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  
  useEffect(() => {
    const fetchFriends = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/auth/friends/${userId}`, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`,
          },
        });
        setFriends(response.data);
        setLoading(false);
      } catch (error) {
        console.error(error);
        setError("Failed to load friends.");
        setLoading(false);
      }
    };

    fetchFriends();
  }, [userId, token]); // Include token in the dependency array

  if (loading) return <p>Loading friends...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h3>Your Friends</h3>
      <ul>
        {friends.map(friend => (
          <li key={friend.id}>
            {friend.firstName} {friend.lastName} (@{friend.username})
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FriendsList;
