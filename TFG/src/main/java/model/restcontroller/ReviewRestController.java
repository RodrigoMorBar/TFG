package model.restcontroller;

import java.math.BigDecimal;


import java.util.List;
import java.util.stream.Collectors;

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

import model.dto.ReviewDTO;
import model.dto.ReviewResponseDTO;
import model.entities.AlbumsCache;
import model.entities.Review;
import model.entities.Users;
import model.service.AlbumCacheService;
import model.service.ReviewService;
import model.service.UsersService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reviews")
public class ReviewRestController {
	
	@Autowired
    private ReviewService reviewSer;
    
    @Autowired
    private UsersService userSer;
    
    @Autowired
    private AlbumCacheService albumSer;
    
    
    
 
    
    @GetMapping("/todos")
    public List<Review> findAll() {
        return reviewSer.findAll();
    }
    
    @GetMapping("/id/{id}")
    public Review findById(@PathVariable Integer id) {
        return reviewSer.findById(id);
    }
    
    @GetMapping("/user/{username}")
    public List<Review> findByUsername(@PathVariable String username) {
        Users user = userSer.findByUserName(username);
        if (user != null) {
            return reviewSer.findByUser(user);
        }
        return null;
    }
    
    @GetMapping("/album/{spotifyAlbumId}")
    public List<Review> findByAlbum(@PathVariable String spotifyAlbumId) {
        AlbumsCache album = albumSer.findById(spotifyAlbumId);
        if (album != null) {
            return reviewSer.findByAlbum(album);
        }
        return null;
    }
    
    @GetMapping("/user/{username}/album/{spotifyAlbumId}")
    public Review findByUserAndAlbum(
            @PathVariable String username,
            @PathVariable String spotifyAlbumId) {
        
        Users user = userSer.findByUserName(username);
        AlbumsCache album = albumSer.findById(spotifyAlbumId);
        
        if (user != null && album != null) {
            return reviewSer.findByUserAndAlbum(user, album);
        }
        return null;
    }
    
    @GetMapping("/rating/{rating}")
    public List<Review> findByRating(@PathVariable BigDecimal rating) {
        return reviewSer.findByRating(rating);
    }
    
    @GetMapping("/average/{spotifyAlbumId}")
    public BigDecimal getAverageRating(@PathVariable String spotifyAlbumId) {
        AlbumsCache album = albumSer.findById(spotifyAlbumId);
        if (album != null) {
            return reviewSer.media(album);
        }
        return BigDecimal.ZERO;
    }
    
    @GetMapping("/followed/{userId}/recent")
    public List<ReviewResponseDTO> findRecentFollowedReviews(@PathVariable Integer userId, 
    		@RequestParam(defaultValue = "30") int limit) {
    	  // El controller SOLO llama al servicio y convierte a DTO
        List<Review> reviews = reviewSer.findRecentFollowedReviews(userId, limit);
        
        return reviews.stream()
                .map(ReviewResponseDTO::new)
                .collect(Collectors.toList());
    }
    
    @PostMapping("/create")
    public int createOrUpdate(@RequestBody ReviewDTO reviewDTO) {
        Users user = userSer.findById(reviewDTO.getUserId());
        AlbumsCache album = albumSer.findById(reviewDTO.getAlbumSpotifyId());
       
        if (user == null || album == null) {
            return -3; // Usuario o álbum no encontrado
        }
        
        Review review = Review.builder()
            .user(user)
            .album(album)
            .rating(reviewDTO.getRating())
            .comment(reviewDTO.getComment())
            .build();
        
        return reviewSer.saveOrUpdate(review);
    }
    
    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable Integer id) {
        return reviewSer.delete(id);
    }
    

}
