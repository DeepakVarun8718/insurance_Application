package in.deepak.serviceImpl;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import in.deepak.entities.User;
import in.deepak.enums.EmploymentType;
import in.deepak.enums.Gender;
import in.deepak.enums.IncomeSlab;
import in.deepak.enums.Role;
import in.deepak.jwt.JwtService;
import in.deepak.repository.UserRepository;
import in.deepak.service.NotificationClient;
import in.deepak.service.PremiumService;

public class AuthServiceImplTest {

	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	@MockBean
	private ModelMapper modelMapper;
	
	@MockBean
	private JwtService jwtService;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private NotificationClient notificationClient;
	
	@MockBean
	private PremiumService premiumService;
	
	
	
	
	//------------
	private User createUser() {
		User user=new User();
		user.setAadhaarNumber("42");
	      user.setAddress("42 Main St");
	      user.setCreatedDate(LocalDate.of(1970, 1, 1));
	      user.setDependents(new ArrayList<>());
	      user.setDob(LocalDate.of(1970, 1, 1));
	      user.setEmail("test@example.com");
	      user.setEmploymentType(EmploymentType.SALARIED);
	      user.setGender(Gender.MALE);
	      user.setIncomeSlab(IncomeSlab.LESS_THAN_2);
	      user.setMobileNumber("42");
	      user.setName("name");
	      user.setPassword("password");
	      user.setRole(Role.USER);
	      user.setUserId(1);
	      return user;
	}
		
	
	//----------------------------------
	
	@Test
	void testCreateUser() {
		User user=createUser();
//		when(modelMapper.map(Mock, null))
	}
}
