package model.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import model.entities.Follows;
import model.entities.Users;
import model.repository.FollowsRepository;
import model.repository.UsersRepository;

@Service
public class FollowsServiceJpaImplMy8 implements FollowsService {
    
    @Autowired
    private FollowsRepository followRepo;
    
    @Override
    public List<Follows> findAll() {
        return followRepo.findAll();
    }
	@Override
	public Follows findById(Integer Id) {
		// TODO Auto-generated method stub
		return followRepo.findById(Id).orElse(null);
	}
    
    
    @Override
    public int insert(Follows follow) {
        int filas = 0;
        if(follow.getIdFollows() != null && followRepo.existsById(follow.getIdFollows())) {
            filas = -1;
        } else {
            try {
                followRepo.save(follow);
                filas = 1;
            } catch(Exception e) {
                e.printStackTrace();
                filas = 0;
            }
        }
        return filas;
    }
    
    @Override
    public int update(Follows follow) {
        int filas = 0;
        Follows existing = followRepo.findById(follow.getIdFollows()).orElse(null);
        if(existing != null) {
            try {
                followRepo.save(follow);
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
        if(followRepo.findById(id).orElse(null) != null) {
            try {
                followRepo.deleteById(id);
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
	public List<Follows> findFollowersByUsername(String username) {
		// TODO Auto-generated method stub
		return followRepo.findFollowersByUsername(username);
	}
	@Override
	public List<Follows> findFollowingByUsername(String username) {
		// TODO Auto-generated method stub
		return followRepo.findFollowingByUsername(username);
	}
	
	@Override
	public List<Integer> findFollowedIdsByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return followRepo.findFollowedIdsByUserId(idUser);
	}
	@Override
	public List<Users> findUsersOrderedByFollowers() {
		// TODO Auto-generated method stub
		return followRepo.findUsersOrderedByFollowers();
	}
	
	@Autowired
	private UsersRepository usersRepo;

	@Override
	public boolean isFollowing(String followerUsername, String followedUsername) {
	    return followRepo.existsByFollowerAndFollowed(followerUsername, followedUsername);
	}

	@Override
	public int followByUsername(String followerUsername, String followedUsername) {
	    try {
	        if (followRepo.existsByFollowerAndFollowed(followerUsername, followedUsername)) {
	            return -1; // ya lo sigue
	        }
	        Users follower = usersRepo.findByUsername(followerUsername);
	        Users followed = usersRepo.findByUsername(followedUsername);
	        if (follower == null || followed == null) return 0;

	        Follows follow = Follows.builder()
	            .follower(follower)
	            .followed(followed)
	            .createdAt(LocalDateTime.now())
	            .build();
	        followRepo.save(follow);
	        return 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

	@Override
	public int unfollowByUsername(String followerUsername, String followedUsername) {
	    Follows follow = followRepo
	        .findByFollowerAndFollowed(followerUsername, followedUsername)
	        .orElse(null);

	    if (follow == null) return 0; // no existe

	    try {
	        followRepo.delete(follow);
	        return 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return -1;
	    }
	}
	
	}



	
	



	


