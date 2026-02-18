package model.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.dto.AddAlbumListRequest;
import model.dto.ListAlbumResponseDTO;
import model.entities.ListAlbum;
import model.service.ListAlbumService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/listalbum")
public class ListAlbumRestController {
	
	@Autowired
	private ListAlbumService listAlbumService;
	
	@GetMapping("/todos")
	public List<ListAlbumResponseDTO> findAll(){
		List<ListAlbum> listAlbums = listAlbumService.findAll();
		List<ListAlbumResponseDTO> response = new ArrayList<>();
		for(ListAlbum listAlbum : listAlbums) {
			response.add(new ListAlbumResponseDTO(listAlbum));
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public ListAlbumResponseDTO findById(@PathVariable Integer id) {
		ListAlbum listAlbum = listAlbumService.findById(id);
		return new ListAlbumResponseDTO(listAlbum);
	}
	
	@PostMapping("/insert")
	public int insert(@RequestBody ListAlbum listAlbum) {
		return listAlbumService.insert(listAlbum);
	}
	
	@PostMapping("/update")
	public int update(@RequestBody ListAlbum listAlbum) {
		return listAlbumService.update(listAlbum);
	}
	
	@DeleteMapping("/delete/{id}")
	public int delete(@PathVariable Integer id) {
		return listAlbumService.delete(id);
	}
	
	// Ver todos los álbumes de una lista específica
	@GetMapping("/list/{listName}")
	public List<ListAlbumResponseDTO> findByListName(@PathVariable String listName) {
	    List<ListAlbum> listAlbums = listAlbumService.findByListName(listName);
	    List<ListAlbumResponseDTO> response = new ArrayList<>();
	    for(ListAlbum listAlbum : listAlbums) {
	        response.add(new ListAlbumResponseDTO(listAlbum));
	    }
	    return response;
	}
	@GetMapping("/album/{albumSpotifyId}")
	public List <ListAlbumResponseDTO> findByAlbumId(@PathVariable String albumSpotifyId){
		
		List<ListAlbum> listAlbums = listAlbumService.findByAlbumId(albumSpotifyId);
		List <ListAlbumResponseDTO> response = new ArrayList<>();
		for (ListAlbum la : listAlbums) {
			response.add(new ListAlbumResponseDTO(la));
		}
		return response;
		
	}
	@GetMapping("/bylist/{listId}")
	public List <ListAlbumResponseDTO> findByListId(@PathVariable Integer listId){
		
		List<ListAlbum> listAlbums = listAlbumService.findByListId(listId);
		List <ListAlbumResponseDTO> response = new ArrayList<>();
		for (ListAlbum la : listAlbums) {
			response.add(new ListAlbumResponseDTO(la));
		}
		return response;
		
	}
	@PostMapping
    public int insertByIds(@RequestBody AddAlbumListRequest request) {
        if (request.getIdentificadorLista() == null || request.getAlbumSpotifyId() == null) {
            return 0;
        }
        return listAlbumService.insertByIds(
            request.getIdentificadorLista(),
            request.getAlbumSpotifyId()
        );
    }
	 @DeleteMapping("/{listId}/{albumSpotifyId}")
	    public int deleteByListAndAlbum(@PathVariable Integer listId,@PathVariable String albumSpotifyId) {
	        return listAlbumService.deleteByListIdAndAlbumId(listId, albumSpotifyId);
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}