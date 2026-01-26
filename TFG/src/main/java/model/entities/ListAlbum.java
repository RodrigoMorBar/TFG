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
@Table( name = "list_albums")
public class ListAlbum implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id_list_album")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idListAlbum;
	
	
	
    @ManyToOne
    @JoinColumn(name = "identificador_lista")
    private SoundList list;

	
    @ManyToOne
    @JoinColumn(name = "album_spotify_id")
    private AlbumsCache album;


    @Column(name = "added_at")
    private LocalDateTime addedAt;

   
}
