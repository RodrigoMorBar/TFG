package model.service;

import java.util.List;

import model.entities.AlbumsCache;

public interface AlbumCacheService{
	
	AlbumsCache findById(String spotifyAlbumId);
    List<AlbumsCache> findByTitle(String title);
    List<AlbumsCache> findByArtist(String artist);
    List<AlbumsCache> findAll();
    int insert(AlbumsCache album);
    int delete(String spotifyAlbumId);
    int update(AlbumsCache album);

}
