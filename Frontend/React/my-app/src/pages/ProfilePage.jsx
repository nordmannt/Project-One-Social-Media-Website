import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Comments from './Comments';
import LikeButton from './LikeButton';

const ProfilePage = () => {
  const { userId } = useParams();
  const [profile, setProfile] = useState(null);
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    

    const fetchProfile = async () => {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get(
          `http://localhost:8080/api/auth/profile/${userId}`,
          {
            headers: { Authorization: `Bearer ${token}` },
          }
        );
        console.log("Profile Response:", response.data); // Verify response structure
        setProfile(response.data); // Updated to use the top-level response data
        setPosts(response.data.posts || []); // Ensure posts is always an array
        setLoading(false);
      } catch (err) {
        console.error("Error fetching profile:", err);
        setError("Failed to load profile data.");
        setLoading(false);
      }
    };
    fetchProfile();
  }, [userId]);
  
  

  if (loading) return <p>Loading profile...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div>
      {loading ? (
        <p>Loading profile...</p>
      ) : error ? (
        <p>{error}</p>
      ) : profile ? (
        <>
          <header>
            <button onClick={() => navigate("/newsfeed")}>Back to Newsfeed</button>
            <button onClick={() => navigate("/friend-search")}>
              Search for Friends
            </button>
            <button
              onClick={() => {
                localStorage.removeItem("token");
                navigate("/login");
              }}
            >
              Logout
            </button>
          </header>
          <h2>
            {profile.firstName} {profile.lastName}'s Profile
          </h2>
          <p>{profile.bio || "No bio available."}</p>
          <img
            src={profile.avatarUrl || "https://via.placeholder.com/150"}
            alt={`${profile.firstName}'s avatar`}
            style={{ width: "150px", height: "150px", borderRadius: "50%" }}
          />
          <p>{profile.location || "Location not provided."}</p>
          <h3>Posts</h3>
          <ul>
            {posts.length > 0 ? (
              posts.map((post) => (
                <li key={post.messageId}>
                  <p>{post.messageText}</p>
                  <p>
                                            Posted by{' '}
                                            <a
                                                href={`/profile/${post.postedBy}`}
                                                style={{ textDecoration: 'none', color: 'blue' }}
                                            >
                                                {post.firstName} {post.lastName}
                                            </a>{' '}
                                            on {new Date(post.timePosted * 1000).toLocaleString()}
                                        </p>
                                        <LikeButton
                                            type="message"
                                            id={post.messageId}
                                            initialLikes={post.likeCount || 0}
                                            userId={userId}
                                        />
                                        <Comments messageId={post.messageId} userId={userId} />
                  <small>
                  {new Date(post.timePosted * 1000).toLocaleString()}
                  </small>
                </li>
              ))
            ) : (
              <p>No posts to display.</p>
            )}
          </ul>
        </>
      ) : (
        <p>No profile data available.</p>
      )}
    </div>
  );
};

export default ProfilePage;
