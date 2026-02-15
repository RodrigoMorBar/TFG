package model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	public Users findByUsername(String username);
	public Users findByUsernameAndPassword(String username, String password);
	public int deleteByUsername(String username);
	
	@Query("Select r.user FROM Review r GROUP BY r.user ORDER BY COUNT(r) DESC")
	List<Users> findUsersOrderedByReviews();
	
	@Query("Select r.user FROM Review r GROUP BY r.user ORDER BY AVG(r.rating) DESC")
	List<Users> findUsersOrderedByAvgRating();

}
