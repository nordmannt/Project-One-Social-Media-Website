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
  NewsFeedContainer,
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
      <NewsFeedContainer>
        <main>
          <section id="newsFeed" style={styles.section}>
            <MainTitle>News Feed</MainTitle>
            <PostMessage onPostSuccess={fetchMessages} />
            {loading ? (
              <p>Loading posts...</p>
            ) : error ? (
              <p>{error}</p>
            ) : (
              <div style={styles.postContainer}>
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
        </main>
        <footer>
          <p>&copy; Tyson Nordmann Revature SocialMediaApp. All rights reserved</p>
        </footer>
      </NewsFeedContainer>
    </div>
  );
};

const styles = {
  section: {
    padding: theme.spacing.lg,
  },
  postContainer: {
    display: "flex",
    flexDirection: "column",
    gap: theme.spacing.md,
  },
  post: {
    padding: theme.spacing.md,
    borderRadius: theme.radius.md,
    boxShadow: theme.shadows.medium,
  },
};

export default NewsFeed;
