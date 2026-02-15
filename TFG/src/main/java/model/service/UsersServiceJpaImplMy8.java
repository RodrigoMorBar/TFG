package model.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import model.entities.Users;
import model.repository.UsersRepository;

@Service
public class UsersServiceJpaImplMy8 implements UsersService, UserDetailsService {
	
	@Autowired
	private UsersRepository usepo;

	@Override
	public Users findById(Integer idUser) {
		// TODO Auto-generated method stub
		return usepo.findById(idUser).orElse(null);
	}

	@Override
	public Users findByUserName(String username) {
		// TODO Auto-generated method stub
		return usepo.findByUsername(username);
	}

	@Override
	public Users findByUserNamePassword(String username, String password) {
		// TODO Auto-generated method stub
		return usepo.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return usepo.findAll();
	}

	@Override
	public int insert(Users usuario) {
		int filas =0;
		if(usuario.getIdUser() !=null && usepo.existsById(usuario.getIdUser()) ) {
			filas = -1;
		}else {
			try {
				usepo.save(usuario);
				filas=1;
			}catch(Exception e){
				e.printStackTrace();
				filas=0;
			}
		}
		return filas;
	}

	@Override
	public int delete(String username) {
		if(usepo.findByUsername(username)!=null) {
			try {
				usepo.delete(usepo.findByUsername(username));
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}	
		}else {
			return 0;
		}
		
	}

	@Override
	public int update(Users usuario) {
		int filas=0;
		Users existing = usepo.findByUsername(usuario.getUsername());
		if(existing != null) {
			try {
				usuario.setIdUser(existing.getIdUser());
				usepo.save(usuario);
				filas=1;
			}catch(Exception e) {
				e.printStackTrace();
				filas=-1;
			}
		}else
			filas=0;
		return filas;
	}

	@Override
	public Users updateFoto(String username, String avatarUrl) {
		Users users = usepo.findByUsername(username);
		if(users != null) {
			users.setAvatarUrl(avatarUrl);
			return usepo.save(users);
		}
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Users usuario = usepo.findByUsername(username);
		    if (usuario == null) {
		        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
		    }
		    return usuario;
	}

	@Override
	public List<Users> findUsersOrderedByReviews() {
		// TODO Auto-generated method stub
		return usepo.findUsersOrderedByReviews();
	}

	@Override
	public List<Users> findUsersOrderedByAvgRating() {
		// TODO Auto-generated method stub
		return usepo.findUsersOrderedByAvgRating();
	}

	
}
