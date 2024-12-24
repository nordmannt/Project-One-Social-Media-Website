import React, { useState, useRef, useEffect } from "react";
import axios from "axios";
import { theme } from "../styles/Themes";
import { Text, PrimaryButton } from "../components";

const FriendSearch = () => {
  const [query, setQuery] = useState("");
  const [results, setResults] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [showDropdown, setShowDropdown] = useState(false);
  const dropdownRef = useRef(null);

  const handleSearch = () => {
    if (!query.trim()) {
      setError("Please enter a search term.");
      return;
    }

    setLoading(true);
    setError(null);
    setShowDropdown(true);

    const token = localStorage.getItem("token");

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
        setError("Failed to fetch search results.");
        setLoading(false);
      });
  };

  const handleClickOutside = (event) => {
    if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
      setShowDropdown(false);
    }
  };

  useEffect(() => {
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  const sendFriendRequest = (receiverId) => {
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");

    if (!userId) {
      console.error("UserId is not defined.");
      alert("Please log in again.");
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
      .then(() => alert("Friend request sent!"))
      .catch((error) => console.error("Error sending friend request:", error));
  };

  return (
    <div style={styles.friendSearchContainer}>
      <input
        id="searchInput"
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Search for friends..."
        style={styles.searchInput}
        onFocus={() => setShowDropdown(true)}
      />
      <button
        onClick={handleSearch}
        style={styles.searchButton}
        disabled={!query.trim()} // Disable button for empty query
      >
        Search
      </button>

      {showDropdown && (
        <div style={styles.dropdown} ref={dropdownRef} role="menu" aria-labelledby="searchInput">
          {loading && <Text style={{ color: "blue" }}>Loading...</Text>}
          {error && <Text style={{ color: "red" }}>{error}</Text>}
          {results.map((user) => (
            <div key={user.accountId} style={styles.dropdownItem}>
              <img
                src={user.avatarUrl}
                alt={`${user.firstName} ${user.lastName}`}
                style={styles.profilePicture}
              />
              <Text>{`${user.firstName} ${user.lastName}`}</Text>
              <PrimaryButton onClick={() => sendFriendRequest(user.accountId)}>
                Add Friend
              </PrimaryButton>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

const styles = {
  friendSearchContainer: {
    position: "sticky",
    top: 0,
    backgroundColor: "white",
    zIndex: 1000,
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    padding: "10px",
   
  },
  searchInput: {
    width: "200px",
    padding: "5px",
    marginRight: "10px",
  },
  searchButton: {
    padding: "5px 10px",
    backgroundColor: "blue",
    color: "white",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
    opacity: 1,
  },
  dropdown: {
    position: "absolute",
    top: "100%",
    left: "0",
    width: "100%",
    maxHeight: "200px",
    overflowY: "auto",
    backgroundColor: "white",
  
    borderRadius: "5px",

    zIndex: 1000,
  },
  dropdownItem: {
    display: "flex",
    alignItems: "center",
    gap: "10px",
    padding: "10px",
    borderBottom: "1px solid #ddd",
  },
  profilePicture: {
    width: "50px",
    height: "50px",
    borderRadius: "50%",
  },
};

export default FriendSearch;
