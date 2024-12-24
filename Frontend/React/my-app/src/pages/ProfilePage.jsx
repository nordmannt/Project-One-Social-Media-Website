import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import Comments from './Comments';
import LikeButton from './LikeButton';
import Navbar from "./Navbar";
import { theme } from "../styles/Themes";
import {
  SmallText,
  NavbarContainer,
  NewsFeedContainer,
  TwoColumnContainer,
  PrimaryButton,
  ContainerCard,
  Input,
  MainTitle,
} from "../components";


const ProfilePage = () => {
  const { userId: userIdProf } = useParams();
  const loggedInUserId = localStorage.getItem("userId");
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
          `http://localhost:8080/api/auth/profile/${userIdProf}`,
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
  }, [userIdProf]);
  
  

  if (loading) return <p>Loading profile...</p>;
  if (error) return <p>{error}</p>;
  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    navigate("/login");
  }
  return (
    <>
      {/* Navbar Section */}
      <NavbarContainer>
        <Navbar
          userId={loggedInUserId}
          profilePicture={profile?.avatarUrl}
          onLogout={handleLogout}
        />
      </NavbarContainer>

      {/* Profile and Feed Container */}
      <TwoColumnContainer>
        {/* Profile Info Column */}
        <div style={styles.profileInfoColumn}>
          <img
            src={profile?.avatarUrl || "https://via.placeholder.com/150"}
            alt={`${profile?.firstName || "User"}'s avatar`}
            style={styles.profilePicture}
          />
          <h2>
            {`${profile?.firstName || "Unknown"} ${
              profile?.lastName || "User"
            }`}
          </h2>
          <p>{profile?.bio || "No bio available."}</p>
          <p>{profile?.location || "Location not provided."}</p>
        </div>

        {/* Posts Column */}
        <div style={styles.postsColumn}>
           <MainTitle>Posts</MainTitle>
          <ul style={styles.postsList}>
            {posts?.length > 0 ? (
              posts.map((post) => (
                <li key={post.messageId} style={styles.postItem}>
                  <p>{post.messageText}</p>
                  <p>
                    Posted by{" "}
                    <a
                      href={`/profile/${post.postedBy}`}
                      style={styles.postLink}
                    >
                      {post.firstName} {post.lastName}
                    </a>{" "}
                    on{" "}
                    {new Date(post.timePosted * 1000).toLocaleString()}
                    <LikeButton
                      type="message"
                      id={post.messageId}
                      initialLikes={post.likeCount || 0}
                      userId={userIdProf}
                    
                    />

                  </p>
                  

                  <Comments messageId={post.messageId} userId={userIdProf} />
                </li>
                
              ))
              
            ) : (
              <p>No posts to display.</p>
            )}
          </ul>
         
        </div>
        
      </TwoColumnContainer>
    </>
  );
};




const styles = {

  
  
   

  
  profileInfoColumn: {
    flex: "1 1 30%",
    backgroundColor: "#f9f9f9",
    padding: "20px",
    borderRadius: "15px",
    boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
    textAlign: "center",
    border: "5px solid #FF0000", // Red border for About Me section
    marginBottom: "20px", // Spacing below
    marginTop: "20px", // Spacing below
    marginRight: "20px", // Spacing below
    backgroundColor: "#ffffff",
  },
  profilePicture: {
    width: "150px",
    height: "150px",
    borderRadius: "50%",
    marginBottom: "10px",
    border: "5px solid #FFD700", // Yellow border for the profile picture
  },
  postsList:{
     listStyle: 'none', padding: 0, margin: 0
     },
  
    
  postsColumn: {
    flex: "1 1 70%",
    backgroundColor: "#ffffff",
    padding: "20px",
    borderRadius: "15px",
    boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
    border: "5px solid #0000FF", // Blue border for Posts section
    marginBottom: "20px", // Spacing below
    marginTop: "50px", // Spacing below
    marginLeft: "10px", // Spacing below
  },
  postItem: {
    
    position: "relative", // Allows for positioning of comments
    padding: "15px",
    marginBottom: "30px", // Spacing between posts
    borderRadius: "10px",
    border: "5px solid #008000", // Green border for posts
    
    backgroundColor: "#ffffff",
  },
  commentsContainer: {
    position: "absolute", // Align it relative to the post box
    top: "0", // Align with the top of the post
    right: "-220px", // Shift it to the right of the post
    width: "60%", // Smaller width compared to the post
    maxWidth: "300px", // Max width to keep it consistent
    padding: "10px",
    backgroundColor: "#fff",
    borderRadius: "10px",
    border: "5px solid #FFD700", // Yellow border for comments
    boxShadow: "0 2px 4px rgba(0, 0, 0, 0.1)",
  },
  commentItem: {
    padding: "8px",
    marginBottom: "5px",
    borderBottom: "1px solid #ddd",
  },
};

export default ProfilePage;