package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table (name="users")
public class Users implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    private String username;

    @Column(name="password_hash")
    private String password;

    private String email;
    
    private String bio;
    

    private String avatarUrl;

    @Column(name="created_at")
    private LocalDateTime creeatedAt;
    
    @Enumerated(EnumType.STRING)
    private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Spring espera "ROLE_ADMIN", "ROLE_USER", etc.
		return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
	}
}
