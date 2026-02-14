package model.service;

import java.util.List;

import model.entities.Follows;
import model.entities.Users;

public interface FollowsService {
    List<Follows> findAll();
    Follows findById(Integer Id);
    int insert(Follows follow);
    int update(Follows follow);
    int delete(Integer id);
    
    List<Follows> findFollowersByUsername(String username);
    List<Follows> findFollowingByUsername(String username);
    
    List<Integer> findFollowedIdsByUserId(Integer idUser);
    List<Users> findUsersOrderedByFollowers(); 
	
}