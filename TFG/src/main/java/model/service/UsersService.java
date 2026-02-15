package model.service;



import java.util.List;

import model.entities.Users;


public interface UsersService {
	Users findById (Integer idUser);
	Users findByUserName(String username);
	Users findByUserNamePassword(String username, String password);
	Users updateFoto(String username, String avatarUrl);
	List<Users> findAll();
	int insert(Users usuario);
	int delete (String username);
	int update (Users usuario);
	List<Users> findUsersOrderedByReviews();
	List<Users> findUsersOrderedByAvgRating();
	
	
	

}
