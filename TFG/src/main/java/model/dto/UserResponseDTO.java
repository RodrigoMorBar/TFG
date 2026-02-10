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
	private Integer idUser;
	private String username;
	private String email;
	private String bio;
	private String avatarUrl;
	private String role;
	
	 public UserResponseDTO(Users user) {
		 	this.idUser = user.getIdUser();
	        this.username = user.getUsername();
	        this.email = user.getEmail();
	        this.bio = user.getBio();
	        this.avatarUrl = user.getAvatarUrl();
	        this.role = user.getRole().name();   // ← AÑADE ESTO también
	    }
}
