package in.deepak.serviceImpl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import in.deepak.entities.User;
import in.deepak.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
public class CustomUserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	public CustomUserDetailServiceImpl() {
		
	}
	
	
	
	
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		System.out.println("LoadUserByUsername --------------------------"+email);	
		
		System.out.println("Hello");
		UserDetails user1 =userRepository.findByEmail(email)
				 .orElseThrow(() -> {
                   return new RuntimeException("User not found with email " + email);
				 });
		
		System.out.println("UserDetails-----"+user1);
		
		return user1;
		
	}

}
