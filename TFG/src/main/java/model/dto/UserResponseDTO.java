package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entities.Users;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
	
	private String username;
	private String email;
	private String bio;
	private String avatarUrl;
	
	 public UserResponseDTO(Users user) {
	        this.username = user.getUsername();
	        this.email = user.getEmail();
	        this.bio = user.getBio();
	        this.avatarUrl = user.getAvatarUrl();
	    }
}
