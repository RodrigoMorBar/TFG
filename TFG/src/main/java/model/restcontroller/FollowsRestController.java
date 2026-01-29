package model.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.entities.Follows;
import model.service.FollowsService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/follow")
public class FollowsRestController {
	
	@Autowired
	private FollowsService followService;
	
	@GetMapping("/todos")
	public List<Follows> findAll(){
		return followService.findAll();
	}
	
	@GetMapping("/{id}")
	public Follows findById(@PathVariable Integer id) {
		return followService.findById(id);
	}
	
	@PostMapping("/insert")
	public int insert(@RequestBody Follows follow) {
		return followService.insert(follow);
	}
	
	@PostMapping("/update")
	public int update(@RequestBody Follows follow) {
		return followService.update(follow);
	}
	
	@DeleteMapping("/delete/{id}")
	public int delete(@PathVariable Integer id) {
		return followService.delete(id);
	}
	
	// Ver seguidores de un usuario (quién lo sigue)
	@GetMapping("/followers/{username}")
	public List<Follows> getFollowers(@PathVariable String username) {
		return followService.findFollowersByUsername(username);
	}
	
	// Ver a quién sigue un usuario
	@GetMapping("/following/{username}")
	public List<Follows> getFollowing(@PathVariable String username) {
		return followService.findFollowingByUsername(username);
	}
}