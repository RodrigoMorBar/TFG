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
@Table(name = "sound_lists")
public class SoundList implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_sound_lists")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    
    private String name;

   
    private String description;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    
}
