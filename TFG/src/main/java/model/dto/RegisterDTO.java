package model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entities.Role;
import model.entities.Users;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {
	private String username;
	private String password;
	private String email;
	
	
	 public Users toEntity() {
	        Users user = new Users();
	        user.setUsername(this.username);
	        user.setPassword(this.password);
	        user.setEmail(this.email);
	        user.setRole(Role.USER);
	        
	        user.setCreeatedAt(LocalDateTime.now());
	        return user;
	    }

}
