package model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.entities.ListAlbum;
import model.repository.ListAlbumRepository;

@Service
public class ListAlbumServiceJpaImplMy8 implements ListAlbumService{

	@Autowired
	private ListAlbumRepository listAlbumRepo;
	
	
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

}
