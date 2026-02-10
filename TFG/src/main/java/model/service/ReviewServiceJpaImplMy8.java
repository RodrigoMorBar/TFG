package model.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.entities.AlbumsCache;
import model.entities.Review;
import model.entities.Users;
import model.repository.FollowsRepository;
import model.repository.ReviewRepository;
@Service
public class ReviewServiceJpaImplMy8 implements ReviewService{
	
	@Autowired
    private ReviewRepository reviewRepo;
	
	@Autowired
	private FollowsRepository followRepo;

	@Override
    public Review findById(Integer id) {
        return reviewRepo.findById(id).orElse(null);
    }
    
    @Override
    public List<Review> findByUser(Users user) {
        return reviewRepo.findByUserOrderByCreatedAtDesc(user);
    }
    
    @Override
    public List<Review> findByAlbum(AlbumsCache album) {
        return reviewRepo.findByAlbumOrderByCreatedAtDesc(album);
    }
    
    @Override
    public Review findByUserAndAlbum(Users user, AlbumsCache album) {
        return reviewRepo.findByUserAndAlbum(user, album);
    }
    
    @Override
    public List<Review> findByRating(BigDecimal rating) {
        return reviewRepo.findByRating(rating);
    }
    
    @Override
    public List<Review> findAll() {
        return reviewRepo.findAllByOrderByCreatedAtDesc();
    }
    
    @Override
    public int saveOrUpdate(Review review) {
        int filas = 0;
        
        // 1. Validar rating
        if (!isValidRating(review.getRating())) {
            return -2; // Rating inválido
        }
        
        // 2. Verificar si ya existe una review
        Review existing = reviewRepo.findByUserAndAlbum(review.getUser(), review.getAlbum());
        
        if (existing != null) {
            // ACTUALIZAR review existente
            try {
                existing.setRating(review.getRating());
                existing.setComment(review.getComment());
                existing.setUpdatedAt(LocalDateTime.now());
                reviewRepo.save(existing);
                filas = 1; // Actualizado
            } catch (Exception e) {
                e.printStackTrace();
                filas = -1; // Error al actualizar
            }
        } else {
            // CREAR nueva review
            try {
                review.setCreatedAt(LocalDateTime.now());
                review.setUpdatedAt(LocalDateTime.now());
                reviewRepo.save(review);
                filas = 2; // Creado
            } catch (Exception e) {
                e.printStackTrace();
                filas = 0; // Error al crear
            }
        }
        
        return filas;
    }
    
    @Override
    public int delete(Integer id) {
        if (reviewRepo.findById(id).isPresent()) {
            try {
                reviewRepo.deleteById(id);
                return 1; // Eliminado
            } catch (Exception e) {
                e.printStackTrace();
                return -1; // Error al eliminar
            }
        } else {
            return 0; // No existe
        }
    }
    
    @Override
    public BigDecimal media(AlbumsCache album) {
        List<Review> reviews = reviewRepo.findByAlbumOrderByCreatedAtDesc(album);
        
        if (reviews.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        BigDecimal sum = reviews.stream()
            .map(Review::getRating)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        return sum.divide(
            new BigDecimal(reviews.size()), 
            1, 
            RoundingMode.HALF_UP
        );
    }
    
    // Método privado para validar rating
    private boolean isValidRating(BigDecimal rating) {
        if (rating == null) {
            return false;
        }
        
        // Rating debe estar entre 0.5 y 5.0
        if (rating.compareTo(new BigDecimal("0.5")) < 0 || 
            rating.compareTo(new BigDecimal("5.0")) > 0) {
            return false;
        }
        
        // Rating debe ser múltiplo de 0.5
        BigDecimal remainder = rating.remainder(new BigDecimal("0.5"));
        return remainder.compareTo(BigDecimal.ZERO) == 0;
    }

	@Override
	public List<Review> findRecentFollowedReviews(Integer userId, int limit) {
		 // 1. Obtener IDs de las personas que sigo
        List<Integer> followedIds = followRepo.findFollowedIdsByUserId(userId);
        
        // 2. Si no sigo a nadie, devolver lista vacía
        if (followedIds == null || followedIds.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 3. Buscar reviews de esas personas
        List<Review> reviews = reviewRepo.findByUserIdInOrderByCreatedAtDesc(followedIds);
        
        // 4. Limitar resultados
        return reviews.stream()
                .limit(limit)
                .collect(Collectors.toList());
	}

	
	

}
