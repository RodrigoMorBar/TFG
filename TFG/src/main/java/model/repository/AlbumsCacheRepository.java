package model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.entities.AlbumsCache;

public interface AlbumsCacheRepository extends JpaRepository<AlbumsCache, String>{
	List<AlbumsCache> findByTitleContainingIgnoreCase(String title);
	List<AlbumsCache> findByArtistContainingIgnoreCase(String artist);


}
