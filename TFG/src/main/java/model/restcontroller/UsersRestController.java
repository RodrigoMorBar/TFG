package model.restcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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

import model.entities.Users;
//import model.service.ImagenUploadService;
import model.service.UsersService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")

public class UsersRestController {
	
	@Autowired
	private UsersService userService;
	
	/*@Autowired
	private ImagenUploadService imagenUploadService;
	
	
	
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
	}*/
	
	@GetMapping("/todos")
	public List<Users> findAll(){
		return userService.findAll();
	}
	@GetMapping("/login/{username}/{password}")
	public Users findbyUsernameAndPassword(@PathVariable String username, @PathVariable String password) {
		return userService.findByUserNamePassword(username, password);
	}
	@GetMapping("/username/{username}")
	public Users findByUsername(@PathVariable String username) {
		return userService.findByUserName(username);
	}
	@PostMapping("/register")
	public int register(@RequestBody Users usuario) {
		return userService.insert(usuario);
	}
	@DeleteMapping("/delete/{username}")
	public int delete (@PathVariable String username) {
		return userService.delete(username);
	}
	@PostMapping("/update")
	public int update (@RequestBody Users usuario) {
		return userService.update(usuario);
	}
	
	

}
