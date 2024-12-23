package com.tyson.socialmedia.controller;

import com.tyson.socialmedia.service.ProfileService;
import com.tyson.socialmedia.DTO.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController // Add this annotation to mark this class as a REST controller
@RequestMapping("/api/auth") // Fixed missing annotation declaration
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    // Dependency injection to obtain an instance of the ProfileService class

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ProfileDTO> getProfile(@RequestHeader("Authorization") String token, @PathVariable Integer userId) {
        logger.info("UserId from URL: {}", userId);
        logger.info("Authorization Header: {}", token);

        ProfileDTO profileDTO = profileService.getProfileDTOByAccountId(userId);
        if (profileDTO == null) {
            return ResponseEntity.notFound().build(); // Return 404 if profile is not found
        }
        return ResponseEntity.ok(profileDTO);
    }

    /*
    @PostMapping("/{accountId}")
    public ResponseEntity<ProfileDTO> createOrUpdateProfile(
        @PathVariable Integer accountId, @RequestBody ProfileDTO profileDTO) {
        // Add logic to map ProfileDTO to Profile and save it
        Profile updatedProfile = profileService.updateProfileFromDTO(accountId, profileDTO);
        ProfileDTO updatedDTO = new ProfileDTO(
            updatedProfile.getAccount().getFirstName(),
            updatedProfile.getAccount().getLastName(),
            updatedProfile.getBio(),
            updatedProfile.getAvatarUrl(),
            updatedProfile.getLocation()
        );
        return ResponseEntity.ok(updatedDTO);
    }
    */
}
