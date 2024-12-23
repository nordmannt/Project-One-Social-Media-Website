package com.tyson.socialmedia;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyson.socialmedia.entity.Like;
import com.tyson.socialmedia.repository.LikesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LikesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LikesRepository likeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        likeRepository.deleteAll(); // Clean up before each test
    }

    @Test
    public void testAddLikeToMessage() throws Exception {
        // Arrange
        Like like = new Like();
        like.setLikedBy(1);
        like.setMessageId(100);

        // Act & Assert
        mockMvc.perform(post("/api/likes/message/100")
                .param("likedBy", "1") // Simulate likedBy parameter
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()) // Expect HTTP 201 Created
                .andExpect(jsonPath("$.likedBy").value(1))
                .andExpect(jsonPath("$.messageId").value(100));
    }

    @Test
    public void testRemoveLikeFromMessage() throws Exception {
        // Arrange: Add a like first
        Like like = new Like();
        like.setLikedBy(1);
        like.setMessageId(100);
        likeRepository.save(like); // Manually save like to database

        // Act & Assert: Remove the like
        mockMvc.perform(delete("/api/likes/message/100")
                .param("likedBy", "1")) // Simulate likedBy parameter
                .andExpect(status().isNoContent()); // Expect HTTP 204 No Content
    }
}
