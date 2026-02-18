package model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.entities.ListAlbum;

public interface ListAlbumRepository extends JpaRepository<ListAlbum, Integer>{
	// Obtener todos los álbumes de una lista específica (ordenados por fecha de añadido)
    @Query("SELECT la FROM ListAlbum la WHERE la.list.name = ?1 ORDER BY la.addedAt DESC")
    List<ListAlbum> findByListName(String listName);
    
    //Todas las listas de albunes de un album/tema concreto 
    @Query("SELECT la FROM ListAlbum la WHERE la.album.spotifyAlbumId = ?1")
    List <ListAlbum> findByAlbumId(String albumSpotifyId);
    
    //Todos los listAlbum de una lista 
    @Query("SELECT la FROM ListAlbum la WHERE la.list.id = ?1 ORDER BY la.addedAt DESC")
    List <ListAlbum> findByListId(Integer listId);
    
    //Buscar por lista y album 
    @Query("SELECT la FROM ListAlbum la WHERE la.list.id = ?1 AND la.album.spotifyAlbumId =?2")
    ListAlbum findByListAndAlbumId(Integer listId, String albumSpotifyId);

}
