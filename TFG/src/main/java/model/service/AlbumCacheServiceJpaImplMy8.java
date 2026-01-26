package model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.entities.AlbumsCache;
import model.repository.AlbumsCacheRepository;
@Service
public class AlbumCacheServiceJpaImplMy8 implements AlbumCacheService{
	
	@Autowired
    private AlbumsCacheRepository cache;

	@Override
	public AlbumsCache findById(String spotifyAlbumId) {
		// TODO Auto-generated method stub
		return cache.findById(spotifyAlbumId).orElse(null);
	}

	@Override
	public List<AlbumsCache> findByTitle(String title) {
		// TODO Auto-generated method stub
		return cache.findByTitle(title);
	}

	@Override
	public List<AlbumsCache> findByArtist(String artist) {
		// TODO Auto-generated method stub
		return cache.findByArtist(artist);
	}

	@Override
	public List<AlbumsCache> findAll() {
		// TODO Auto-generated method stub
		return cache.findAll();
	}

	@Override
	public int insert(AlbumsCache album) {
		// TODO Auto-generated method stub
int filas = 0;
        
        if (album.getSpotifyAlbumId() != null && cache.existsById(album.getSpotifyAlbumId())) {
            filas = -1; 
        } else {
            try {
                album.setCachedAt(LocalDateTime.now());
                cache.save(album);
                filas = 1;
            } catch (Exception e) {
                e.printStackTrace();
                filas = 0;
            }
        }
        
        return filas;
    }
	

	@Override
	public int delete(String spotifyAlbumId) {
		// TODO Auto-generated method stub
		if (cache.findById(spotifyAlbumId).isPresent()) {
            try {
                cache.deleteById(spotifyAlbumId);
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        } else {
            return 0;
        }
	}

	



	@Override
	public int update(AlbumsCache album) {
		// TODO Auto-generated method stub
		int filas = 0;
        AlbumsCache existing = cache.findById(album.getSpotifyAlbumId()).orElse(null);
        
        if (existing != null) {
            try {
                album.setCachedAt(existing.getCachedAt());
                cache.save(album);
                filas = 1;
            } catch (Exception e) {
                e.printStackTrace();
                filas = -1;
            }
        } else {
            filas = 0;
        }
        
        return filas;
    }
	}
	