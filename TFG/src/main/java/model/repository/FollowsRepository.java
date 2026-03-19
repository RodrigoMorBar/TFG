package model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.entities.Follows;
import model.entities.Users;

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
    
    //Usuarios con mas seguidores 
    @Query ("SELECT f.followed From Follows f GROUP BY f.followed ORDER BY COUNT(f) DESC")
    List<Users> findUsersOrderedByFollowers();
    
    
    @Query("SELECT f FROM Follows f WHERE f.follower.username = ?1 AND f.followed.username = ?2")
    Optional<Follows> findByFollowerAndFollowed(String follower, String followed);

    @Query("SELECT COUNT(f) > 0 FROM Follows f WHERE f.follower.username = ?1 AND f.followed.username = ?2")
    boolean existsByFollowerAndFollowed(String follower, String followed);
}


