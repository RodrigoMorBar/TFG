package model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewDTO {
	
	
	private Integer userId;
    private String albumSpotifyId;
    private BigDecimal rating;
    private String comment;
	

}
