package security;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import security.JwtFilter;

import model.entities.Users;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	 
	  
	  @Bean
	  SecurityFilterChain securityFilterChain(HttpSecurity http,
	                                          AuthenticationProvider authProvider,
	                                          JwtFilter jwtFilter) throws Exception {
		  
		      http
		          .csrf(csrf -> csrf.disable())
		          .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		          .cors(c -> c.configurationSource(corsConfigurationSource()))
		          .authorizeHttpRequests(auth -> auth
		        		    .requestMatchers("/user/register").permitAll()
		        		    .requestMatchers("/user/login/**").permitAll()
		        		    
		        		    .requestMatchers("/user/username/**").permitAll()
		                    .requestMatchers("/user/update-profile").permitAll()
		                    .requestMatchers("/user/update").permitAll()
		                    .requestMatchers("/reviews/**").permitAll()
		                    .requestMatchers("/albums/**").permitAll()
		                    .requestMatchers("/albumscache/search").permitAll()
		                    .requestMatchers("/soundlist/**").permitAll()
		                    .requestMatchers("/listalbum/**").permitAll()
		                    .requestMatchers("/follow/**").permitAll()
		                    .requestMatchers("/likes/**").permitAll()
		                    
		                    .requestMatchers("/spotify/**").permitAll()
		        		    
		        		    .anyRequest().authenticated()  // Hemos cambiado el permite all por el que esten authenticados
		        		)
		          .authenticationProvider(authProvider)
		          .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		      return http.build();
		  }
	          
	  
	  @Bean
	  AuthenticationProvider authenticationProvider(UserDetailsService uds, PasswordEncoder encoder) {
	      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	      provider.setUserDetailsService(uds);
	      provider.setPasswordEncoder(encoder);
	      return provider;
	  }
	 
	  
	  
	  
	  @Bean
	  PasswordEncoder passwordEncoder() {
	      return new BCryptPasswordEncoder();  
	  }
	  
	  
	  @Bean
	  public CorsConfigurationSource corsConfigurationSource() {
	      CorsConfiguration config = new CorsConfiguration();
	      config.setAllowedOrigins(List.of("http://localhost:5173"));
	      config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	      config.setAllowedHeaders(List.of("*"));
	      config.setAllowCredentials(true);
	      
	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      source.registerCorsConfiguration("/**", config);
	      return source;
	  }
	  @Bean
	  AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	      return authConfig.getAuthenticationManager();
	  }
}
