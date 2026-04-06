package model.restcontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.entities.AlbumsCache;
import model.service.AlbumCacheService;
import model.service.SpotifyService;

@RestController
@RequestMapping("/albums")
@CrossOrigin(origins ="*")
public class AlbumCacheRestController {
	@Autowired
    private AlbumCacheService albumService;
	@Autowired
	private SpotifyService spotifyService;
    
    @GetMapping("/todos")
    public List<AlbumsCache> findAll() {
        return albumService.findAll();
    }
    
    
    @GetMapping("/id/{spotifyAlbumId}")
    public AlbumsCache findById(@PathVariable String spotifyAlbumId) {
        // 1. Buscar en caché local
        AlbumsCache cached = albumService.findById(spotifyAlbumId);
        if (cached != null) return cached;

        // 2. Si no está → buscar en Spotify y guardar
        try {
            Map<String, Object> spotifyData = spotifyService.getAlbum(spotifyAlbumId);
            AlbumsCache newAlbum = new AlbumsCache();
            newAlbum.setSpotifyAlbumId((String) spotifyData.get("spotifyAlbumId"));
            newAlbum.setTitle((String) spotifyData.get("title"));
            newAlbum.setArtist((String) spotifyData.get("artist"));
            newAlbum.setCoverUrl((String) spotifyData.get("coverUrl"));
            newAlbum.setReleaseYear(Integer.parseInt((String) spotifyData.get("releaseYear")));
            newAlbum.setSpotifyUrl((String) spotifyData.get("spotifyUrl"));
            newAlbum.setCachedAt(java.time.LocalDateTime.now());
            albumService.save(newAlbum);
            return newAlbum;
        } catch (Exception e) {
        	System.out.println("ERROR guardando álbum: " + e.getMessage());
            return null;
        }
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
    @GetMapping("/search")
    public List<AlbumsCache> search(@RequestParam String query) {
        List<AlbumsCache> byTitle = albumService.findByTitle(query);
        List<AlbumsCache> byArtist = albumService.findByArtist(query);
        
        List<AlbumsCache> results = new ArrayList<>(byTitle);
        for (AlbumsCache a : byArtist) {
            if (results.stream().noneMatch(r -> r.getSpotifyAlbumId().equals(a.getSpotifyAlbumId()))) {
                results.add(a);
            }
        }
        return results;
    }
	    }

