package model.entities;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table( name = "follows")


public class Follows implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_follows")
	private Integer idFollows;
	
	@ManyToOne
	@JoinColumn(name = "follower_id")
	@JsonIgnoreProperties({"password", "authorities", "enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
	private Users follower;
	
	
	@ManyToOne
	@JoinColumn(name= "followed_id")
	@JsonIgnoreProperties({"password", "authorities", "enabled", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
	private Users followed;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	//gfrgrgr
	
	

}
