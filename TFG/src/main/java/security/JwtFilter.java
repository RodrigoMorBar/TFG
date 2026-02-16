package security;


import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.UsersServiceJpaImplMy8;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UsersServiceJpaImplMy8 usersDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		//Obtenemos la authorizacion
		String authHeader = request.getHeader("Authorization");
		
		//Si no tiene authorizacion o no empieza por Bearer (que es como decir esto de aqui es un token) 
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		//Extraemos el token quitando el bearer
		String token = authHeader.substring(7);
		
		//Validamos el token y autenticamos el usuario
		if (jwtService.validateToken(token)) {
			String username = jwtService.extractUsername(token);
			UserDetails userDetails = usersDetailsService.loadUserByUsername(username);
			
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			
			authToken.setDetails (new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}
		
		filterChain.doFilter(request, response);
		
	}

}
