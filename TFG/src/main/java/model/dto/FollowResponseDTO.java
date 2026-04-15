package model.dto;

import model.entities.Follows;
import java.time.LocalDateTime;

public class FollowResponseDTO {
    private String followerUsername;
    private String followerAvatarUrl;
    private String followedUsername;
    private String followedAvatarUrl;
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
            this.followerAvatarUrl = follow.getFollower() != null ? follow.getFollower().getAvatarUrl() : null;
            this.followedUsername = follow.getFollowed() != null ? follow.getFollowed().getUsername() : null;
            this.followedAvatarUrl = follow.getFollowed() != null ? follow.getFollowed().getAvatarUrl() : null;
            this.createdAt = follow.getCreatedAt();
        }
    }
    
    public String getFollowerAvatarUrl() {
		return followerAvatarUrl;
	}

	public void setFollowerAvatarUrl(String followerAvatarUrl) {
		this.followerAvatarUrl = followerAvatarUrl;
	}

	public String getFollowedAvatarUrl() {
		return followedAvatarUrl;
	}

	public void setFollowedAvatarUrl(String followedAvatarUrl) {
		this.followedAvatarUrl = followedAvatarUrl;
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