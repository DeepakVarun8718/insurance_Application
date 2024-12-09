package in.deepak.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.deepak.entities.AuthRequest;
import in.deepak.entities.AuthResponse;
import in.deepak.entities.OTPValidationRequest;
import in.deepak.exception.InvalidOtpException;
import in.deepak.payloads.ApiResponse;
import in.deepak.payloads.ReadBody;
import in.deepak.payloads.UserDto;
import in.deepak.serviceImpl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	
	   private AuthServiceImpl authService;
	   
	   private AuthenticationManager authenticationManager;
	   
	  
	   
	   @PostMapping("/welcome")
	   public String welcome(@RequestBody ReadBody readBody) {
		   System.out.println(readBody);
		   
		   return readBody.getName()+" "+readBody.getEmail()+" "+readBody.getNumber();
	   }
	
	   @GetMapping("/name/{name}")
	   public String welcome1(@PathVariable String name) {
		  return  authService.sendName(name);
		   
		   
	   }
	   
//	   @PostMapping("/hello")
//	   public String welcome2() {
//		   System.out.print("Auth controller welcome");
//		   authService.sendMailCheck("captian@gmail.com", "091234");
//		   return "Hello Welcome";
//	   }
//	

	   @PostMapping("/register")
	    public ResponseEntity<ApiResponse> createUser(@RequestBody @Valid UserDto userDto) {
		   
		   System.out.println("Entered Register Auth Controller");
		   
		   ApiResponse createdUserDto = this.authService.createUser(userDto);
	        
	        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	    }
	   
	   
	   @PostMapping("/login")
	    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthRequest authRequest) {
		   
		   
	    	System.out.println("------------- Entered in Login Controller ------------");
	    	
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

	        System.out.println("------------ Authentication Manager Check Complete-------------");
	        AuthResponse authResponse = authService.generateToken(authRequest.getEmail());
	        
	        return new ResponseEntity<>(authResponse,HttpStatus.OK);

	    }
	   @PostMapping("/validateOtp")
	   public ResponseEntity<ApiResponse> validateOtpHandler(@RequestBody OTPValidationRequest otpValidationRequest) throws InvalidOtpException{
		   
		   String email=otpValidationRequest.getEmail();
		   String otp=otpValidationRequest.getOtp();
		   ApiResponse api=authService.validateOTP(email, otp);
		   
		   return new ResponseEntity<ApiResponse>(api,HttpStatus.ACCEPTED);
	   }
   
	   
}
