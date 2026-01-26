package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.entities.Review;
import model.entities.ReviewLike;
import model.entities.Users;
import model.repository.ReviewLikeRepository;


@Service
public class ReviewLikeServiceJpaImplMy8 implements ReviewLikeService{
	
	@Autowired
    private ReviewLikeRepository likeRepo;

	@Override
	public ReviewLike findById(Integer id) {
		// TODO Auto-generated method stub
		return likeRepo.findById(id).orElse(null);
	}

	@Override
	public List<ReviewLike> findByReview(Review review) {
		// TODO Auto-generated method stub
		 return likeRepo.findByReview(review);
	}

	@Override
	public List<ReviewLike> findByUser(Users user) {
		// TODO Auto-generated method stub
		return likeRepo.findByUser(user);
	}

	@Override
	public long countByReview(Review review) {
		// TODO Auto-generated method stub
		return likeRepo.countByReview(review);
	}

	@Override
	public int toggleLike(Users user, Review review) {
		// TODO Auto-generated method stub
        int resultado = 0;
        
        try {
            ReviewLike existing = likeRepo.findByUserAndReview(user, review);
            
            if (existing != null) {
                // Ya existe → quitar like
                likeRepo.delete(existing);
                resultado = -1; // Like quitado
            } else {
                // No existe → dar like
                ReviewLike newLike = ReviewLike.builder()
                    .user(user)
                    .review(review)
                    .build();
                likeRepo.save(newLike);
                resultado = 1; // Like dado
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultado = 0; // Error
        }
        
        return resultado;
    
	}

	@Override
	public boolean likeado(Users user, Review review) {
		// TODO Auto-generated method stub
		 return likeRepo.existsByUserAndReview(user, review);
	}

}
