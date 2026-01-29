package model.dto;

import model.entities.Follows;
import java.time.LocalDateTime;

public class FollowResponseDTO {
    private String followerUsername;
    private String followedUsername;
    private LocalDateTime createdAt;
    
    public FollowResponseDTO() {
    }
    
    public FollowResponseDTO(String followerUsername, String followedUsername, LocalDateTime createdAt) {
        this.followerUsername = followerUsername;
        this.followedUsername = followedUsername;
        this.createdAt = createdAt;
    }
    
    public FollowResponseDTO(Follows follow) {
        if(follow != null) {
            this.followerUsername = follow.getFollower() != null ? follow.getFollower().getUsername() : null;
            this.followedUsername = follow.getFollowed() != null ? follow.getFollowed().getUsername() : null;
            this.createdAt = follow.getCreatedAt();
        }
    }
    
    public String getFollowerUsername() {
        return followerUsername;
    }
    
    public void setFollowerUsername(String followerUsername) {
        this.followerUsername = followerUsername;
    }
    
    public String getFollowedUsername() {
        return followedUsername;
    }
    
    public void setFollowedUsername(String followedUsername) {
        this.followedUsername = followedUsername;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}