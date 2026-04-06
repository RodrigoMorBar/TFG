package model.restcontroller;

import model.service.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/spotify")
@CrossOrigin(origins = "*")
public class SpotifyRestController {

    @Autowired
    private SpotifyService spotifyService;

    
    @GetMapping("/search")
    public List<Map<String, Object>> search(@RequestParam String query) {
        try {
            return spotifyService.searchAlbums(query);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    
    @GetMapping("/album/{spotifyId}")
    public Map<String, Object> getAlbum(@PathVariable String spotifyId) {
        try {
            return spotifyService.getAlbum(spotifyId);
        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }

    
    @GetMapping("/album/{spotifyId}/tracks")
    public List<Map<String, Object>> getTracks(@PathVariable String spotifyId) {
        try {
            return spotifyService.getAlbumTracks(spotifyId);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
