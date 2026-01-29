package model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.entities.ListAlbum;

public interface ListAlbumRepository extends JpaRepository<ListAlbum, Integer>{
	// Obtener todos los álbumes de una lista específica (ordenados por fecha de añadido)
    @Query("SELECT la FROM ListAlbum la WHERE la.list.name = ?1 ORDER BY la.addedAt DESC")
    List<ListAlbum> findByListName(String listName);

}
