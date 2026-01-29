package model.service;

import java.util.List;

import model.entities.SoundList;

public interface SoundListService {
	List<SoundList> findAll();
	SoundList findById (Integer id);
	int insert (SoundList soundList);
	int update (SoundList soundList);
	int delete (Integer id);
	List <SoundList> findByUsername(String username);

}
