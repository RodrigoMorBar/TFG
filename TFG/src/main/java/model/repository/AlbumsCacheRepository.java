package model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.entities.AlbumsCache;

public interface AlbumsCacheRepository extends JpaRepository<AlbumsCache, String>{
	List<AlbumsCache> findByTitle(String title);
    List<AlbumsCache> findByArtist(String artist);

}
