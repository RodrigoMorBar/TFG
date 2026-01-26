package model.service;

import java.math.BigDecimal;
import java.util.List;

import model.entities.AlbumsCache;
import model.entities.Review;
import model.entities.Users;

public interface ReviewService {
	
    Review findById(Integer id);
    List<Review> findByUser(Users user);
    List<Review> findByAlbum(AlbumsCache album);
    Review findByUserAndAlbum(Users user, AlbumsCache album);
    List<Review> findByRating(BigDecimal rating);
    List<Review> findAll();
 
    // Crear o actualizar review (automático)
    int saveOrUpdate(Review review);
    int delete(Integer id);
    
    // Calcular rating promedio de un álbum lo dejo hecho pero se puede borrar
    BigDecimal media(AlbumsCache album);

}
