package model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entities.Review;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewResponseDTO {
	
	  private Integer id;
	    private UserResponseDTO user;
	    private AlbumResponseDTO album;
	    private BigDecimal rating;
	    private String comment;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;
	    
	    // Constructor desde entidad Review
	    public ReviewResponseDTO(Review review) {
	        this.id = review.getId();
	        this.user = new UserResponseDTO(review.getUser());
	        this.album = new AlbumResponseDTO(review.getAlbum());
	        this.rating = review.getRating();
	        this.comment = review.getComment();
	        this.createdAt = review.getCreatedAt();
	        this.updatedAt = review.getUpdatedAt();
	    }

}
