package in.deepak.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


import in.deepak.jwt.JwtAuthenticationEntryPoint;
import in.deepak.serviceImpl.CustomUserDetailServiceImpl;

@Configuration
public class SecurityConfiguration {
	

	@Autowired
	private CustomUserDetailServiceImpl customUserDetailServiceImpl;

    @Autowired
    private JwtAuthenticationEntryPoint point;

  
	
	
	@Bean
	public SecurityFilterChain securityConfigmethod(HttpSecurity http) throws Exception {
	
		
		http.csrf(csrf->csrf.disable())
		.cors(cors->cors.disable())
		.authorizeHttpRequests(auth->
		    auth.requestMatchers("/auth/**").permitAll()
		    .requestMatchers("/insurance/**","/auth/**","/swagger-ui/**","/v3/api-docs/**").permitAll()
		    .anyRequest().authenticated())
		.exceptionHandling(ex->ex.authenticationEntryPoint(point))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception {
		return config.getAuthenticationManager();
		
	}
	
	@Bean
	public AuthenticationProvider authentionProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailServiceImpl);
        provider.setPasswordEncoder(passwordEncode());
        return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ModelMapper modalMapper() {
		return new ModelMapper();
	}
}
