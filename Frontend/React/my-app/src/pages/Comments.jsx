import React, { useState, useEffect } from "react";
import axios from "axios";
import PostComment from './PostComment';
import LikeButton from './LikeButton';

const Comments = ({ messageId, userId }) => {
    const [comments, setComments] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const fetchComments = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/auth/comments/${messageId}`, 
                {
                    headers: {
                    Authorization: `Bearer ${localStorage.getItem('token')}`,
                },
            }
            );
            setComments(response.data); // Corrected to setComments
        } catch (error) {
            console.error("Error fetching comments:", error);
            setError("Failed to load comments.");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchComments();
    }, [messageId]); // Correct dependency

    return (
        <div className="comments">
            <h4>Comments</h4>
            {loading && <p>Loading comments...</p>}
            {error && <p>{error}</p>}
            {!loading && !error && comments.length === 0 && <p>No comments. Be the first to comment.</p>}
            {!loading && !error && comments.length > 0 && (
                <ul style={{ listStyle: 'none', padding: 3, margin: 3 }}>
                    {comments.map((comment) => (
                        <li key={comment.commentId}>
                            <p>{comment.commentText}</p>
                            <small>
                                Posted by{' '}
                                <a href={`/profile/${comment.commentedby}`} style={{ textDecoration: "none",  color: 'blue' }}>
                                    {comment.firstName} {comment.lastName}
                                </a>{' '}
                                on {comment.timePosted}
                            </small>
                            <LikeButton
                                type="comment"
                                id={comment.commentId}
                                initialLikes={comment.likeCount}
                                userId={userId} // Use the prop passed from the parent
                            />
                        </li>
                    ))}
                </ul>
            )}
            <PostComment messageId={messageId} onCommentSuccess={fetchComments} />
        </div>
    );
};    
export default Comments;
