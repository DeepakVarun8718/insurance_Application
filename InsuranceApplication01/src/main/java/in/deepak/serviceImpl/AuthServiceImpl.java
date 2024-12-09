package in.deepak.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.deepak.entities.AuthResponse;
import in.deepak.entities.User;
import in.deepak.enums.Role;
import in.deepak.exception.InvalidOtpException;
import in.deepak.jwt.JwtService;
import in.deepak.payloads.ApiResponse;
import in.deepak.payloads.UserDto;
import in.deepak.repository.UserRepository;
import in.deepak.service.NotificationClient;
import in.deepak.service.PremiumService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


@Service
@AllArgsConstructor
public class AuthServiceImpl {

	private UserRepository userRepository;
	
      private NotificationClient notificationService;
	 
     
     private ModelMapper modelMapper;
     
     private PasswordEncoder passwordEncode;
     
     private PremiumService premiumService;
     
     private JwtService jwtService;
     
     Map<String, User> temporaryStorage = new HashMap<>();
     Map<String, String> otpStorage = new HashMap<>();
     
     
     @Transactional
     public ApiResponse createUser(UserDto userDto) {
    	 
    	 System.out.println("Entered createUSer AuthServiceImpl");
    	 userDto.setRole(Role.USER);
    	 User user=modelMapper.map(userDto, User.class);
    	 String otp=generateOtp();
    	 System.out.println("Otp Generate Successfully "+otp);
    	 if(temporaryStorage.containsKey(user.getEmail())) {
    		 temporaryStorage.remove(user.getEmail());
    	 }
    	 if(otpStorage.containsKey(user.getEmail())) {
    		 otpStorage.remove(user.getEmail());
    	 }
    	 temporaryStorage.put(user.getEmail(), user);
    	 otpStorage.put(user.getEmail(), otp);
    	 
    	 System.out.println("Other Work Completed "+temporaryStorage+"  "+otpStorage);
    	 
    	 sendOTPEmail(user.getEmail(), otp);
    	 
    	
    	 
    	 ApiResponse apiResponse=new ApiResponse("OTP Send SuccessFully",true);
    	 
    	 return apiResponse;
    	 
     }
     
     public void sendOTPEmail(String email, String otp) {
    	 System.out.println("Send Otp to mail method called "+email+" "+otp);
         notificationService.sendMail(email, otp);
         
     }
     
     public String generateOtp() {
    	 
    	 return String.format("%06d", new Random().nextInt(999999));
     }
     
     public String sendName(String name) {
    	 notificationService.sendName(name);
    	 return "Check ";
     }
     
//     public String sendMailCheck(String email,String otp) {
//    	 
//    	 System.out.print("AuthImpl  "+email+" "+otp);
//    	 notificationService.sendMailCheck(email, otp);
//    	 
//    	 return "check "+email+" "+otp;
//     }
     
     
     @Transactional
     public ApiResponse validateOTP(String email,String otp) throws InvalidOtpException {
    	 
    	
    		 
    	 String tempOtp=otpStorage.get(email);
    	 if(tempOtp==null) {
    		 throw new InvalidOtpException("OTP is Invalid");
    	 }
    	 
    	 if(tempOtp.equals(otp)) {
    		 User user=temporaryStorage.remove(email);
    		 user.setPassword(passwordEncode.encode(user.getPassword()));
    		 user.setCreatedDate(LocalDate.now());
    		 userRepository.save(user);
    		 return new ApiResponse("User registered successfully!", true);
    	 }
    	 
    	 return new ApiResponse("Invalid OTP Exception",false);
     }
     
     public AuthResponse generateToken(String username) {
    	 
    	 System.out.println("Auth Impl to generateToken for Login request");
    	 
       User user = userRepository.findByEmail(username)
               .orElseThrow(() -> new RuntimeException("User not found with email " + username));
       if (user.getRole().equals(Role.USER)) {
           premiumService.calculateAndInsertPremiums(user.getUserId());
       }
       System.out.println("Auth Impl to generateToken for Login completed");
       
       return new AuthResponse(jwtService.generateToken(username),username);
   }
     
     
     
}
