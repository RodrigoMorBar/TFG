package model.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.entities.AlbumsCache;
import model.entities.Review;
import model.entities.Users;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
public List<Review> findByUserOrderByCreatedAtDesc(Users user);
    
    public List<Review> findByAlbumOrderByCreatedAtDesc(AlbumsCache album);
    
    public Review findByUserAndAlbum(Users user, AlbumsCache album);
    
    public List<Review> findByRating(BigDecimal rating);
    
    public List<Review> findByRatingGreaterThanEqual(BigDecimal rating);
    
    public boolean existsByUserAndAlbum(Users user, AlbumsCache album);
    
    public List<Review> findAllByOrderByCreatedAtDesc();

}
