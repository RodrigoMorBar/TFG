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
    List<ListAlbum> findByAlbumId(String albumSpotifyId);
    List<ListAlbum> findByListId(Integer listId);
    ListAlbum findByListAndAlbumId(Integer listId, String albumSpotifyId);
    
    int insertByIds(Integer listId, String albumSpotifyId);
    int deleteByListIdAndAlbumId(Integer listId, String albumSpotifyId);

}
