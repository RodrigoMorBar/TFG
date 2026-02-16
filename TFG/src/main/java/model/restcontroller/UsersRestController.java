package model.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import model.dto.LoginRequest;
import model.dto.RegisterDTO;
import model.dto.UserResponseDTO;
import model.entities.Role;
import model.entities.Users;
import model.service.ImagenUploadService;
//import model.service.ImagenUploadService;
import model.service.UsersService;
import security.JwtService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")

public class UsersRestController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ImagenUploadService imagenUploadService;
	
	@Autowired
	private JwtService jwtService;
	
	
	
	@PostMapping("/username/{username}/foto")
	public ResponseEntity<?> uploadFoto(@PathVariable String username, @RequestParam("file") MultipartFile file) {
	    try {
	        // 1. Subir imagen a Cloudinary
	        String imageUrl = imagenUploadService.uploadFile(file);
	        
	        // 2. Actualizar foto del usuario usando el SERVICE
	        Users usuario = userService.updateFoto(username, imageUrl);
	        
	        if(usuario == null) {
	            return ResponseEntity.status(404).body("Usuario no encontrado");
	        }
	        
	        // 3. Respuesta
	        return ResponseEntity.ok(Map.of(
	            "message", "Foto actualizada correctamente",
	            "foto", imageUrl,
	            "username", username
	        ));
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).body("Error al subir imagen: " + e.getMessage());
	    }
	}
	
	@GetMapping("/todos")
	public List<UserResponseDTO> findAll(){
		List<Users> users = userService.findAll();
		List<UserResponseDTO> response = new ArrayList<>();
		for(Users user : users) {
		    response.add(new UserResponseDTO(user));
		    }
		return response;
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
	    try {
	        // Autenticar con Spring Security
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                loginRequest.getUsername(),
	                loginRequest.getPassword()
	            )
	        );
	        
	        //Generamos el token con  el username
	        String token = jwtService.generateToken(loginRequest.getUsername());
	        
	        // Si llega aquí, las credenciales son correctas
	        Users user = userService.findByUserName(loginRequest.getUsername());
	        UserResponseDTO userDTO = new UserResponseDTO(user);
	        
	        return ResponseEntity.ok(Map.of(
	        		"token", token,
	        		"user", userDTO
	        		));
	        
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body("Usuario o contraseña incorrectos");
	    }
	}
	@GetMapping("/username/{username}")
	public UserResponseDTO findByUsername(@PathVariable String username) {
	    Users user = userService.findByUserName(username);
	    return new UserResponseDTO(user);
	}
	@PostMapping("/register")
	public int register(@RequestBody RegisterDTO dto) {
	    Users user = dto.toEntity();
	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    user.setRole(Role.USER);
	    return userService.insert(user);
	}
	@DeleteMapping("/delete/{username}")
	public int delete (@PathVariable String username) {
		return userService.delete(username);
	}
	@PostMapping("/update")
	public int update (@RequestBody Users usuario) {
		return userService.update(usuario);
	}
	@GetMapping("/top-reviewers")
	public List<UserResponseDTO> getTopReviewers() {
	    List<Users> users = userService.findUsersOrderedByReviews();
	    List<UserResponseDTO> response = new ArrayList<>();
	    for(Users user : users) {
	        response.add(new UserResponseDTO(user));
	    }
	    return response;
	}

	@GetMapping("/top-rated")
	public List<UserResponseDTO> getTopRated() {
	    List<Users> users = userService.findUsersOrderedByAvgRating();
	    List<UserResponseDTO> response = new ArrayList<>();
	    for(Users user : users) {
	        response.add(new UserResponseDTO(user));
	    }
	    return response;
	}
	
	

}
