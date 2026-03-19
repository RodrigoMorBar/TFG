package model.restcontroller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.dto.FollowResponseDTO;
import model.dto.UserResponseDTO;
import model.entities.Follows;
import model.entities.Users;
import model.service.FollowsService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/follow")
public class FollowsRestController {
	
	@Autowired
	private FollowsService followService;
	
	@GetMapping("/todos")
	public List<FollowResponseDTO> findAll(){
		List<Follows> follows = followService.findAll();
		List<FollowResponseDTO> response = new ArrayList<>();
		for (Follows follow : follows) {
			response.add(new FollowResponseDTO(follow));
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable Integer id) {
	    Follows follow = followService.findById(id);
	    return "Follower: " + follow.getFollower().getUsername() + ", Followed: " + follow.getFollowed().getUsername();
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
	
	@GetMapping("/followers/{username}")
	public List<FollowResponseDTO> getFollowers(@PathVariable String username) {
		List<Follows> follows = followService.findFollowersByUsername(username);
		List<FollowResponseDTO> response = new ArrayList<>();
		for(Follows follow : follows) {
			response.add(new FollowResponseDTO(follow));
		}
		return response;
	}
	
	@GetMapping("/following/{username}")
	public List<FollowResponseDTO> getFollowing(@PathVariable String username) {
		List<Follows> follows = followService.findFollowingByUsername(username);
		List<FollowResponseDTO> response = new ArrayList<>();
		for(Follows follow : follows) {
			response.add(new FollowResponseDTO(follow));
		}
		return response;
	}
	@GetMapping("/top-users")
	public List<UserResponseDTO> getTopUsers(){
		List <Users> users= followService.findUsersOrderedByFollowers();
		List <UserResponseDTO> response = new ArrayList<>();
		for  (Users user : users) {
			response.add(new UserResponseDTO(user));
		}
		return response;
				
	}
	
	@GetMapping("/status/{followerUsername}/{followedUsername}")
	public boolean isFollowing(
	    @PathVariable String followerUsername,
	    @PathVariable String followedUsername) {
	    return followService.isFollowing(followerUsername, followedUsername);
	}

	@PostMapping("/{followedUsername}")
	public int follow(
	    @PathVariable String followedUsername,
	    @RequestParam String followerUsername) {
	    return followService.followByUsername(followerUsername, followedUsername);
	}

	@DeleteMapping("/{followedUsername}")
	public int unfollow(
	    @PathVariable String followedUsername,
	    @RequestParam String followerUsername) {
	    return followService.unfollowByUsername(followerUsername, followedUsername);
	}
}