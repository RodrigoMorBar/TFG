package model.service;

import java.util.List;

import model.entities.ListAlbum;

public interface ListAlbumService {
	List<ListAlbum> findAll();
    ListAlbum findById(Integer id);
    int insert(ListAlbum listAlbum);
    int update(ListAlbum listAlbum);
    int delete(Integer id);
    List<ListAlbum> findByListName(String listName);

}
