package model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import model.entities.SoundList;
import model.repository.SoundListRepository;

@Service
public class SoundListServiceJpaImplMy8 implements SoundListService {
    
    @Autowired
    private SoundListRepository soundListRepo;
    
    @Override
    public List<SoundList> findAll() {
        return soundListRepo.findAll();
    }
    
    @Override
    public SoundList findById(Integer id) {
        return soundListRepo.findById(id).orElse(null);
    }
    
    @Override
    public int insert(SoundList soundList) {
        int filas = 0;
        if(soundList.getId() != null && soundListRepo.existsById(soundList.getId())) {
            filas = -1;
        } else {
            try {
                soundListRepo.save(soundList);
                filas = 1;
            } catch(Exception e) {
                e.printStackTrace();
                filas = 0;
            }
        }
        return filas;
    }
    
    @Override
    public int update(SoundList soundList) {
        int filas = 0;
        if(soundList.getId() != null && soundListRepo.existsById(soundList.getId())) {
            try {
                soundListRepo.save(soundList);
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
        if(soundListRepo.findById(id).orElse(null) != null) {
            try {
                soundListRepo.deleteById(id);
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
    public List<SoundList> findByUsername(String username) {
        return soundListRepo.findByUsername(username);
    }
}