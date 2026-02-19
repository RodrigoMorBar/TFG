package model.dto;

import model.entities.ListAlbum;
import java.time.LocalDateTime;

public class ListAlbumResponseDTO {
    private Integer idListAlbum;
    private Integer listId;
    private String listName;
    private String albumSpotifyId;
    private String albumTitle;
    private String albumArtist;
    private String albumCoverUrl;
    private LocalDateTime addedAt;
    private String listOwnerUsername;
    
    public ListAlbumResponseDTO() {
    }
    
    public ListAlbumResponseDTO(ListAlbum listAlbum) {
        this.idListAlbum = listAlbum.getIdListAlbum();
        this.listId = listAlbum.getList().getId();
        this.listName = listAlbum.getList().getName();
        this.albumSpotifyId = listAlbum.getAlbum().getSpotifyAlbumId();
        this.albumTitle = listAlbum.getAlbum().getTitle();
        this.albumArtist = listAlbum.getAlbum().getArtist();
        this.albumCoverUrl = listAlbum.getAlbum().getCoverUrl();
        this.addedAt = listAlbum.getAddedAt();
        this.listOwnerUsername=listAlbum.getList().getUser().getUsername();
    }
    
    // Getters y Setters
    public Integer getIdListAlbum() {
        return idListAlbum;
    }
    
    public void setIdListAlbum(Integer idListAlbum) {
        this.idListAlbum = idListAlbum;
    }
    
    public Integer getListId() {
        return listId;
    }
    
    public void setListId(Integer listId) {
        this.listId = listId;
    }
    
    public String getListName() {
        return listName;
    }
    
    public void setListName(String listName) {
        this.listName = listName;
    }
    
    public String getAlbumSpotifyId() {
        return albumSpotifyId;
    }
    
    public void setAlbumSpotifyId(String albumSpotifyId) {
        this.albumSpotifyId = albumSpotifyId;
    }
    
    public String getAlbumTitle() {
        return albumTitle;
    }
    
    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }
    
    public String getAlbumArtist() {
        return albumArtist;
    }
    
    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }
    
    public String getAlbumCoverUrl() {
        return albumCoverUrl;
    }
    
    public void setAlbumCoverUrl(String albumCoverUrl) {
        this.albumCoverUrl = albumCoverUrl;
    }
    
    public LocalDateTime getAddedAt() {
        return addedAt;
    }
    
    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
    public String getListOwnerUsername() {
        return listOwnerUsername;
    }

    public void setListOwnerUsername(String listOwnerUsername) {
        this.listOwnerUsername = listOwnerUsername;
    }
}