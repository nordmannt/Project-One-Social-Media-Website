import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import PostMessage from './PostMessage';
import Comments from './Comments';
import LikeButton from './LikeButton';
import FriendSearch from './FriendSearch';
import PendingRequests from "./Pending Requests";
import {
    PrimaryButton,
    ContainerCard,
    ContainerPlain,
    Input,
    Title,
} from '../components';

const NewsFeed = () => {
    const [posts, setPosts] = useState([]);
    const navigate = useNavigate();
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");

    const fetchMessages = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/auth/messages/${userId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            const messagesWithLikes = await Promise.all(
                response.data.map(async (message) => {
                    const likeCountResponse = await axios.get(
                        `http://localhost:8080/api/auth/likes/message/${message.messageId}/likeCount`,
                        {
                            headers: {
                                Authorization: `Bearer ${localStorage.getItem('token')}`,
                            },
                        }
                    );
                    return { ...message, likeCount: likeCountResponse.data };
                })
            );
            setPosts(messagesWithLikes);
        } catch (error) {
            console.error('Error fetching posts:', error);
            setError('Failed to load messages.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchMessages();
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('token'); // Optional: Clear token on logout
        localStorage.removeItem('userId'); // Optional: Clear userId on logout
        navigate('/login');
    };

    return (
        <div>
            <header>
                <nav>
                    <div className="logo">Chummy</div>
                    <h1>Welcome, {post.firstName} {post.lastName}!</h1>
                    <PendingRequests userId={userId} />
                    <FriendSearch />
                    <div className="search-bar">
                        <input type="text" placeholder="Search..." />
                    </div>
                    <div className="user-options">
                        <button onClick={() => navigate(`/profile/${userId}`)}>Profile</button> {/* Navigate to the user's profile */}
                        <button onClick={handleLogout}>Logout</button>
                    </div>
                </nav>
            </header>
            <main>
                <section id="newsFeed">
                    <Title>News Feed</Title>
                    <PostMessage onPostSuccess={fetchMessages} />
                    {loading ? (
                        <p>Loading posts...</p>
                    ) : error ? (
                        <p>{error}</p>
                    ) : (
                        <div className="post-container">
                            {posts.length > 0 ? (
                                posts.map((post) => (
                                    <div key={post.messageId} className="post">
                                        <h2>{post.messageText}</h2>
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
                                    </div>
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
        </div>
    );
    
};

export default NewsFeed;
