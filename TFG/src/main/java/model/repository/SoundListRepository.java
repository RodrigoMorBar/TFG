package model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.entities.SoundList;

public interface SoundListRepository extends JpaRepository<SoundList, Integer> {
	
	   @Query("SELECT s FROM SoundList s WHERE s.user.username = ?1")
	    List<SoundList> findByUsername(String username);
	

}
