package model.service;

import java.util.List;

import model.entities.Review;
import model.entities.ReviewLike;
import model.entities.Users;

public interface ReviewLikeService {
	
    ReviewLike findById(Integer id);
    List<ReviewLike> findByReview(Review review);
    List<ReviewLike> findByUser(Users user);
    long countByReview(Review review);
    
    // Toggle like (dar o quitar)
    int toggleLike(Users user, Review review);
    
    // Verificar si un usuario dio like
    boolean likeado(Users user, Review review);

}
