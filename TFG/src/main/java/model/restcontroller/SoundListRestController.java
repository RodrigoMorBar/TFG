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
import org.springframework.web.bind.annotation.RestController;

import model.dto.SoundListResponseDTO;
import model.entities.SoundList;
import model.service.SoundListService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/soundlist")
public class SoundListRestController {
	
	@Autowired
	private SoundListService soundListService;
	
	@GetMapping("/todos")
	public List<SoundListResponseDTO> findAll(){
		List<SoundList> soundLists = soundListService.findAll();
		List<SoundListResponseDTO> response = new ArrayList<>();
		for(SoundList soundList : soundLists) {
			response.add(new SoundListResponseDTO(soundList));
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public SoundListResponseDTO findById(@PathVariable Integer id) {
		SoundList soundList = soundListService.findById(id);
		return new SoundListResponseDTO(soundList);
	}
	
	@PostMapping("/insert")
	public int insert(@RequestBody SoundList soundList) {
		return soundListService.insert(soundList);
	}
	
	@PostMapping("/update")
	public int update(@RequestBody SoundList soundList) {
		return soundListService.update(soundList);
	}
	
	@DeleteMapping("/delete/{id}")
	public int delete(@PathVariable Integer id) {
		return soundListService.delete(id);
	}
	
	@GetMapping("/user/{username}")
	public List<SoundListResponseDTO> findByUsername(@PathVariable String username) {
		List<SoundList> soundLists = soundListService.findByUsername(username);
		List<SoundListResponseDTO> response = new ArrayList<>();
		for(SoundList soundList : soundLists) {
			response.add(new SoundListResponseDTO(soundList));
		}
		return response;
	}
}