package model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.entities.Follows;

public interface FollowsRepository extends JpaRepository<Follows, Integer>{
	  // Obtener todos los que siguen a un usuario (sus seguidores)
    @Query("SELECT f FROM Follows f WHERE f.followed.username = ?1")
    List<Follows> findFollowersByUsername(String username);
    
    // Obtener todos los que un usuario sigue (a quién sigue)
    @Query("SELECT f FROM Follows f WHERE f.follower.username = ?1")
    List<Follows> findFollowingByUsername(String username);
    
 // NUEVO: Obtener IDs de las personas que YO sigo
    @Query("SELECT f.followed.id FROM Follows f WHERE f.follower.id = :idUser")
    List<Integer> findFollowedIdsByUserId(Integer idUser);
}


