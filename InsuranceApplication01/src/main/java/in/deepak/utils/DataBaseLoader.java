package in.deepak.utils;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import in.deepak.entities.User;
import in.deepak.enums.*;
import java.time.*;

import in.deepak.repository.UserRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataBaseLoader implements ApplicationRunner{

	
	    
	    private UserRepository userRepository;
	    
	    
	    private PasswordEncoder passwordEncoder;
	    
	   

		@Override
		public void run(ApplicationArguments args) throws Exception {
			// TODO Auto-generated method stub
			User user=new User();
	        user.setUserId(101);
	        user.setName("Shina");
	        user.setPassword(passwordEncoder.encode("Shina01"));
	        user.setEmail("Shina01@gmail.com");
	        user.setAddress("Indore");
	        user.setDob(LocalDate.of(1999, 12, 1));
	        user.setAadhaarNumber("123456789011");
	        user.setGender(Gender.FEMALE);
	        user.setEmploymentType(EmploymentType.SALARIED);
	        user.setIncomeSlab(IncomeSlab.SEVEN_TO_TEN);
	        user.setRole(Role.ADMIN);
	        user.setMobileNumber("1234789011");
	        if (!userRepository.existsByRole(user.getRole())) {
	            userRepository.save(user);
	            System.out.println("Admin saved successfully.");
	        }
	        User user1=new User();
	        user1.setUserId(102);
	        user1.setName("Shinam");
	        user1.setPassword(passwordEncoder.encode("Shinam01"));
	        user1.setEmail("Shinam01@gmail.com");
	        user1.setAddress("Indore");
	        user1.setDob(LocalDate.of(1999, 12, 1));
	        user1.setAadhaarNumber("123456789012");
	        user1.setGender(Gender.FEMALE);
	        user1.setEmploymentType(EmploymentType.SALARIED);
	        user1.setIncomeSlab(IncomeSlab.SEVEN_TO_TEN);
	        user1.setRole(Role.UNDERWRITER);
	        user1.setMobileNumber("1234567892");
	        if (!userRepository.existsByRole(user1.getRole())) {
	            userRepository.save(user1);
	            System.out.println("Underwriter saved successfully.");
	        }
			
		}
}
