package config;

import com.cloudinary.Cloudinary;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClaudinaryConfig {
	
	 @Bean
	    public Cloudinary cloudinary() {
	        return new Cloudinary(ObjectUtils.asMap(
	                "cloud_name", "dz2sldekr",
	                "api_key", "868858272291998",
	                "api_secret", "AlI4f8B0MZNduxtXb1SItod4ZB8",
	                "secure", true
	        ));
	    }

}
