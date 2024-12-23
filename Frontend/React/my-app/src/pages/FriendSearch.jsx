import React, { useState } from 'react';
import axios from 'axios';

const FriendSearch = () => {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSearch = () => {
    setLoading(true);
    setError(null);

    // Retrieve token from localStorage
    const token = localStorage.getItem('token');
    console.log('Token:', token);

    axios
      .get(`http://localhost:8080/api/auth/account/search?query=${query}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        setResults(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error(error);
        setError('Failed to fetch search results.');
        setLoading(false);
      });
  };

  const sendFriendRequest = (receiverId) => {
    // Retrieve token and userId from localStorage
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId'); // Add this line
    console.log('Token:', token);
    console.log('UserId:', userId);

    if (!userId) {
      console.error('UserId is not defined. Ensure it is stored in localStorage.');
      alert('UserId is not defined. Please log in again.');
      return;
    }

    axios
      .post(
        `http://localhost:8080/api/auth/friends/${userId}/request`,
        { receiverId },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then(() => alert('Friend request sent!'))
      .catch((error) => console.error('Error sending friend request:', error));
  };

  return (
    <div>
      <h3>Search for Friends</h3>
      <input
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Enter name to search"
      />
      <button onClick={handleSearch}>Search</button>

      {loading && <p>Loading...</p>}
      {error && <p>{error}</p>}

      <ul>
        {results.map((user) => (
          <li key={user.accountId}>
            {user.firstName} {user.lastName}
            <button onClick={() => sendFriendRequest(user.accountId)}>
              Add Friend
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default FriendSearch;
