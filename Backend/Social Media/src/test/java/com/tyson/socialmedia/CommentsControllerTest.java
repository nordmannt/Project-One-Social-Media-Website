package com.tyson.socialmedia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyson.socialmedia.entity.Comment;
import com.tyson.socialmedia.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testCreateComment() throws Exception {
        // Arrange
        Comment comment = new Comment();
        comment.setCommentedBy(1);
        comment.setMessageId(1);
        comment.setCommentText("This is a test comment");

        // Act & Assert
        mockMvc.perform(post("/api/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.commentText").value("This is a test comment"));
    }

    @Test
    public void testRetrieveAllComments() throws Exception {
        // Arrange: Add a comment to the in-memory database
        Comment comment = new Comment();
        comment.setCommentedBy(1);
        comment.setMessageId(1);
        comment.setCommentText("Retrieve this comment");
        commentRepository.save(comment);

        // Act & Assert
        mockMvc.perform(get("/api/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))) // Ensure one comment exists
                .andExpect(jsonPath("$[0].commentText").value("Retrieve this comment"));
    }

    @Test
    public void testUpdateComment() throws Exception {
        // Arrange: Add a comment
        Comment comment = new Comment();
        comment.setCommentedBy(1);
        comment.setMessageId(1);
        comment.setCommentText("Old Comment");
        Comment savedComment = commentRepository.save(comment);

        // Update the comment text
        String updatedText = "{\"comment_text\": \"Updated Comment\"}";

        // Act & Assert
        mockMvc.perform(patch("/api/comments/" + savedComment.getCommentId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedText))
                .andExpect(status().isNoContent());

        // Verify the update
        Optional<Comment> updatedComment = commentRepository.findById(savedComment.getCommentId());
        assert updatedComment.isPresent();
        assert updatedComment.get().getCommentText().equals("Updated Comment");
    }

    @Test
    public void testDeleteComment() throws Exception {
        // Arrange: Add a comment
        Comment comment = new Comment();
        comment.setCommentedBy(1);
        comment.setMessageId(1);
        comment.setCommentText("Comment to be deleted");
        Comment savedComment = commentRepository.save(comment);

        // Act & Assert: Delete the comment
        mockMvc.perform(delete("/api/comments/" + savedComment.getCommentId()))
                .andExpect(status().isNoContent());

        // Verify the deletion
        Optional<Comment> deletedComment = commentRepository.findById(savedComment.getCommentId());
        assert deletedComment.isEmpty();
    }
}
