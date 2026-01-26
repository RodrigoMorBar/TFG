package model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	
	public Users findByUsername(String username);
	public Users findByUsernameAndPassword(String username, String password);
	public int deleteByUsername(String username);

}
