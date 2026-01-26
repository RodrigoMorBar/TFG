package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "review_likes")
public class ReviewLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_review_likes")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReviewLike;

	
	
	
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

	
    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    
}
