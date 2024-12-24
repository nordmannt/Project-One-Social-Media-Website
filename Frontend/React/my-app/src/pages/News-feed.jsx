import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import PostMessage from "./PostMessage";
import Comments from "./Comments";
import LikeButton from "./LikeButton";
import Navbar from "./Navbar";
import { theme } from "../styles/Themes";
import {
  NavbarContainer,
 
  PrimaryButton,
  ContainerCard,
  Input,
  MainTitle,
} from "../components";

const NewsFeed = () => {
  const [profile, setProfile] = useState(null);
  const [pError, setPerror] = useState(null);
  const [posts, setPosts] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");

  const fetchMessages = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/api/auth/messages/${userId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const messagesWithLikes = await Promise.all(
        response.data.map(async (message) => {
          const likeCountResponse = await axios.get(
            `http://localhost:8080/api/auth/likes/message/${message.messageId}/likeCount`,
            {
              headers: {
                Authorization: `Bearer ${token}`,
              },
            }
          );
          return { ...message, likeCount: likeCountResponse.data };
        })
      );
      setPosts(messagesWithLikes);
    } catch (err) {
      console.error("Error fetching posts:", err);
      setError("Failed to load messages.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const fetchProfile = async () => {
        try {
          const response = await axios.get(`http://localhost:8080/api/auth/profile/${userId}`, {
            headers: {
              Authorization: `Bearer ${token}`, // Pass the token here
            },
          });
          setProfile(response.data);
        } catch (err) {
          setPerror("Failed to load profile.");
        }
      };
      

    fetchProfile();
    fetchMessages();
  }, [userId]);

  if (pError) {
    return <p>{pError}</p>;
  }

  if (!profile) {
    return <p>Loading profile...</p>;
  }

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    navigate("/login");
  };

  return (
    <div>
      <NavbarContainer>
        <Navbar
          userId={userId}
          profilePicture={profile.avatarUrl}
          onLogout={handleLogout}
        />
      </NavbarContainer>
  {/* New Rectangular Box Section */}
  <div style={styles.rectangularBox}>
      <section id="newsFeed" style={styles.newsFeedBox}>
          <MainTitle>News Feed</MainTitle>
          
          <PostMessage onPostSuccess={fetchMessages} />
        </section>
      </div>
  
      <div>
  
        {/* Blue Box - Posts */}
        <section style={styles.postsContainer}>
          {loading ? (
            <p>Loading posts...</p>
          ) : error ? (
            <p>{error}</p>
          ) : (
            <div style={styles.postWrapper}>
              {posts.length > 0 ? (
                posts.map((post) => (
                  <ContainerCard key={post.messageId} style={styles.post}>
                    <h2>{post.messageText}</h2>
                    <p>
                      Posted by{" "}
                      <a
                        href={`/profile/${post.postedBy}`}
                        style={{ textDecoration: "none", color: "blue" }}
                      >
                        {post.firstName} {post.lastName}
                      </a>{" "}
                      on {new Date(post.timePosted * 1000).toLocaleString()}
                    </p>
                    <LikeButton
                      type="message"
                      id={post.messageId}
                      initialLikes={post.likeCount || 0}
                      userId={userId}
                    
                    />
                    <Comments messageId={post.messageId} userId={userId} />
                  </ContainerCard>
                ))
              ) : (
                <p>No posts to display.</p>
              )}
            </div>
          )}
        </section>
      </div>
  
      <footer style={styles.footer}>
        <p>&copy; Tyson Nordmann Revature SocialMediaApp. All rights reserved</p>
      </footer>
    </div>

  
     
  );
  
};

const styles = {
 
    rectangularBox: {
        width: "50%", // Full-width box
        padding: "20px",
        margin: "20px 0", // Spacing between navbar and the rest
        backgroundColor: "#f5f5f5", // Light gray background
        border: "5px solid #FF0000", // Red border for About Me section
    marginBottom: "20px", // Spacing below
    marginTop: "20px", // Spacing below
    marginRight: "100px", // Spacing below
    marginLeft: "500px", // Spacing below
    backgroundColor: "#ffffff",
    boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
    borderRadius: "15px",
      },

  section: {
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
  postContainer: {
    flex: "1 1 200%",
    backgroundColor: "#ffffff",
    padding: "20px",
    borderRadius: "15px",
    boxShadow: "0 4px 6px rgba(0, 0, 0, 0.1)",
    border: "5px solid #0000FF", // Blue border for Posts section
    marginBottom: "20px", // Spacing below
    marginTop: "50px", // Spacing below
    marginLeft: "10px", // Spacing below
  },
  post: {
    position: "relative", // Allows for positioning of comments
    padding: "15px",
    marginBottom: "30px", // Spacing between posts
    borderRadius: "10px",
    border: "5px solid #008000", // Green border for posts
    marginBottom: "20px", // Spacing below
    marginTop: "50px", // Spacing below
    marginLeft: "40px", // Spacing below
    
    backgroundColor: "#ffffff",
  },

  
};

export default NewsFeed;
