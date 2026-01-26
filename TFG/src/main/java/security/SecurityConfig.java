package security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	 
	  
	  @Bean
	  SecurityFilterChain securityFilterChain(HttpSecurity http,
	                                          AuthenticationProvider authProvider) throws Exception {
	      http
	          .csrf(csrf -> csrf.disable())
	          .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	          .cors(Customizer.withDefaults())
	          .authorizeHttpRequests(auth -> auth
	              .requestMatchers("/user/username/*/foto").authenticated()
	              .requestMatchers("/api/usuarios/**").authenticated()
	              .anyRequest().authenticated()
	          )
	          .httpBasic(Customizer.withDefaults())
	          .authenticationProvider(authProvider);
	      return http.build();
	  }
	  
	  @Bean
	  AuthenticationProvider authenticationProvider(UserDetailsService uds, PasswordEncoder encoder) {
	      DaoAuthenticationProvider provider = new DaoAuthenticationProvider(uds);
	      provider.setPasswordEncoder(encoder);
	      return provider;
	  }
	  
	  @SuppressWarnings("deprecation")
	  @Bean
	  PasswordEncoder passwordEncoder() {
	      return NoOpPasswordEncoder.getInstance();
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
}
