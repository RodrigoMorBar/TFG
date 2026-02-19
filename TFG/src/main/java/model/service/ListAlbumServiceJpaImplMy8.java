package model.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import model.entities.AlbumsCache;
import model.entities.ListAlbum;
import model.entities.SoundList;
import model.repository.ListAlbumRepository;

@Service
public class ListAlbumServiceJpaImplMy8 implements ListAlbumService{

	@Autowired
	private ListAlbumRepository listAlbumRepo;
	
	@Autowired
	private SoundListService soundListServ;
	
	@Autowired
	private AlbumCacheService albumCacheServ;
	
	
	@Override
	public List<ListAlbum> findAll() {
		// TODO Auto-generated method stub
		return listAlbumRepo.findAll();
	}

	@Override
	public ListAlbum findById(Integer id) {
		// TODO Auto-generated method stub
		return listAlbumRepo.findById(id).orElse(null);
	}

	@Override
	public int insert(ListAlbum listAlbum) {
		int filas = 0;
        if(listAlbum.getIdListAlbum() != null && listAlbumRepo.existsById(listAlbum.getIdListAlbum())) {
            filas = -1;
        } else {
            try {
                listAlbumRepo.save(listAlbum);
                filas = 1;
            } catch(Exception e) {
                e.printStackTrace();
                filas = 0;
            }
        }
        return filas;
	}

	@Override
	public int update(ListAlbum listAlbum) {
		int filas = 0;
        if(listAlbum.getIdListAlbum() != null && listAlbumRepo.existsById(listAlbum.getIdListAlbum())) {
            try {
                listAlbumRepo.save(listAlbum);
                filas = 1;
            } catch(Exception e) {
                e.printStackTrace();
                filas = -1;
            }
        } else {
            filas = 0;
        }
        return filas;
	}

	@Override
	public int delete(Integer id) {
		if(listAlbumRepo.findById(id).orElse(null) != null) {
            try {
                listAlbumRepo.deleteById(id);
                return 1;
            } catch(Exception e) {
                e.printStackTrace();
                return -1;
            }
        } else {
            return 0;
        }
	}

	@Override
	public List<ListAlbum> findByListName(String listName) {
		// TODO Auto-generated method stub
		return listAlbumRepo.findByListName(listName);
	}

	@Override
	public List<ListAlbum> findByAlbumId(String albumSpotifyId) {
		// TODO Auto-generated method stub
		return listAlbumRepo.findByAlbumId(albumSpotifyId);
	}

	@Override
	public List<ListAlbum> findByListId(Integer listId) {
		// TODO Auto-generated method stub
		return listAlbumRepo.findByListId(listId);
	}

	@Override
	public ListAlbum findByListAndAlbumId(Integer listId, String albumSpotifyId) {
		// TODO Auto-generated method stub
		return listAlbumRepo.findByListAndAlbumId(listId, albumSpotifyId);
	}

	@Override
	public int insertByIds(Integer listId, String albumSpotifyId) {
		try {
		   SoundList list = soundListServ.findById(listId);
           AlbumsCache album = albumCacheServ.findById(albumSpotifyId);
           if (list == null || album == null) {
               return 0; //no enocnotrados
           }
           //Uusuario actual 
           Authentication auth = SecurityContextHolder.getContext().getAuthentication();
           String currentUsername = auth.getName();
           if (!list.getUser().getUsername().equals(currentUsername)) {
               return -2; //si usuario actual es creador de la lista
           }
           ListAlbum existing = listAlbumRepo.findByListAndAlbumId(listId, albumSpotifyId);
           if (existing != null) {
               return -1; // Ya existe
           }
           ListAlbum la = new ListAlbum();
           la.setList(list);
           la.setAlbum(album);
           la.setAddedAt(LocalDateTime.now());
           listAlbumRepo.save(la);
           return 1; //Insertado
       } catch (Exception e) {
           e.printStackTrace();
           return 0;
       }
	}

	@Override
	public int deleteByListIdAndAlbumId(Integer listId, String albumSpotifyId) {
		 try { 
	            SoundList list = soundListServ.findById(listId);
	            if (list == null) {
	                return 0; // Lista no encontrada
	            }
	            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	            String currentUsername = auth.getName();
	            if (!list.getUser().getUsername().equals(currentUsername)) {
	                return -2; // No es tu lista
	            }
	            ListAlbum la = listAlbumRepo.findByListAndAlbumId(listId, albumSpotifyId);
	            if (la == null) {
	                return 0; // No existe
	            }
	            listAlbumRepo.deleteById(la.getIdListAlbum());
	            return 1;//Eliminado
	        } catch (Exception e) {
	            e.printStackTrace();
	            return -1;
	        }
	}

}
