package in.deepak.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
	
	@Bean
	public ModelMapper modalMap() {
		return new ModelMapper();
	}
	

}
