package model.entities;

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
@Table( name = "albums_cache")
public class AlbumsCache {

    @Id
    @Column(name = "spotify_album_id")
    private String spotifyAlbumId;

    
    private String title;

    
    private String artist;

    
    private String coverUrl;

    private Integer releaseYear;

    
    private String spotifyUrl;

    @Column(name = "cached_at")
    private LocalDateTime cachedAt;

 
}
