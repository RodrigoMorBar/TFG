package security;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
	
	private static final String SECRET_KEY = "tfgmkr_clave_secreta_proyecto_2024_segura";
	private static final long EXPIRATION_TIME = 86400000;
	
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	//Generar token con el username del usuario 
	public String generateToken(String username) {
		return Jwts.builder()
					.setSubject(username)
					.setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
					.signWith(getSigningKey(), SignatureAlgorithm.HS256)
					.compact();
	}
	//Sacamos el username del token 
	public String extractUsername(String token) {
		return Jwts.parserBuilder()
					.setSigningKey(getSigningKey())
					.build()
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
	}
	
	//Validamos que el token que se ha generado es correcto y no ha expirado
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token);
			return true;
		}catch( Exception e) {
			return false;
		}
	}

}
