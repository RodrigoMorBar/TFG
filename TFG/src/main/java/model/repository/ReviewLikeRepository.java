package model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.entities.Review;
import model.entities.ReviewLike;
import model.entities.Users;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Integer>{
	
    public ReviewLike findByUserAndReview(Users user, Review review);
 // comprueba si existe like
    public boolean existsByUserAndReview(Users user, Review review);
    
    // busca todos los likes de una review
    public List<ReviewLike> findByReview(Review review);
    
    // busca todos los likes de un usuario
    public List<ReviewLike> findByUser(Users user);
    
    // contador de likes de una review esto se puede hacer desde react pero ayuda si esta hecho desde el back
    public long countByReview(Review review);
    
    // Borra like específico igual q el contador
    public void deleteByUserAndReview(Users user, Review review);

}
