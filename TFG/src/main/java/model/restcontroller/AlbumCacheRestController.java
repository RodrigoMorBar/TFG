package model.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.entities.AlbumsCache;
import model.service.AlbumCacheService;

@RestController
@RequestMapping("/albums")
@CrossOrigin(origins ="*")
public class AlbumCacheRestController {
	@Autowired
    private AlbumCacheService albumService;
    
    @GetMapping("/todos")
    public List<AlbumsCache> findAll() {
        return albumService.findAll();
    }
    
    
    @GetMapping("/id/{spotifyAlbumId}")
    public AlbumsCache findById(@PathVariable String spotifyAlbumId) {
        return albumService.findById(spotifyAlbumId);
    }
    
  
    @GetMapping("/title/{title}")
    public List<AlbumsCache> findByTitle(@PathVariable String title) {
        return albumService.findByTitle(title);
    }
    
  
    @GetMapping("/artist/{artist}")
    public List<AlbumsCache> findByArtist(@PathVariable String artist) {
        return albumService.findByArtist(artist);
    }
    
   
    @PostMapping("/create")
    public int create(@RequestBody AlbumsCache album) {
        return albumService.insert(album);
    }
    
   
    @DeleteMapping("/delete/{spotifyAlbumId}")
    public int delete(@PathVariable String spotifyAlbumId) {
        return albumService.delete(spotifyAlbumId);
    }
    

    @PutMapping("/{spotifyAlbumId}")
    public int update(@PathVariable String spotifyAlbumId, @RequestBody AlbumsCache album) {
        album.setSpotifyAlbumId(spotifyAlbumId);
        return albumService.update(album);
    }
	    }

