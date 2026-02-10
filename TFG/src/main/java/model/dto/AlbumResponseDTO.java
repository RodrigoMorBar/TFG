package model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entities.AlbumsCache;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AlbumResponseDTO {
	
	 private String spotifyAlbumId;
	    private String title;
	    private String artist;
	    private String coverUrl;
	    private Integer releaseYear;
	    private String spotifyUrl;
	    
	    public AlbumResponseDTO(AlbumsCache album) {
	        this.spotifyAlbumId = album.getSpotifyAlbumId();
	        this.title = album.getTitle();
	        this.artist = album.getArtist();
	        this.coverUrl = album.getCoverUrl();
	        this.releaseYear = album.getReleaseYear();
	        this.spotifyUrl = album.getSpotifyUrl();
	    }

}
