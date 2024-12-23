import React, { useState, useEffect } from "react";
import axios from "axios";

const PendingRequests = () => { // Fixed extra parentheses and corrected syntax
  const [requests, setRequests] = useState([]); 
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId"); // Added missing semicolon
    axios
      .get(`http://localhost:8080/api/auth/friends/requests/${userId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((response) => {
        console.log("API Response:", response.data);
        const data = Array.isArray(response.data) ? response.data : [];
        setRequests(data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching pending requests:", error);
        setError("Failed to load pending requests.");
        setLoading(false);
      });
  }, []); // Removed `userId` from dependency array to avoid unnecessary re-renders

  const handleRequest = (friendshipId, status) => {
    const token = localStorage.getItem("token");

    axios
      .put(
        `http://localhost:8080/api/auth/friends/${friendshipId}`,
        null, // No body needed
        {
          params: { status },
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      )
      .then(() => {
        alert(`Request ${status}`);
        // Remove the processed request from the UI
        setRequests((prevRequests) =>
          prevRequests.filter((req) => req.friendshipId !== friendshipId)
        );
      })
      .catch((error) => console.error(`Error updating request:`, error));
  };

  if (loading) return <p>Loading pending requests...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      <h3>Pending Friend Requests</h3>
      <ul>
        {requests.map((request) => (
          <li key={request.friendshipId}>
            <span>Friend Request from User ID: {request.friendA}</span>
            <button
              onClick={() => handleRequest(request.friendshipId, "accepted")}
            >
              Accept
            </button>
            <button
              onClick={() => handleRequest(request.friendshipId, "rejected")}
            >
              Reject
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PendingRequests;
