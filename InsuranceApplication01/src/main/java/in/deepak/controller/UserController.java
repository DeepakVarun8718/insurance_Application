package in.deepak.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payloads.DependentDto;
import in.deepak.payloads.PremiumDto;
import in.deepak.payloads.UserDto;
import in.deepak.repository.UserRepository;
import in.deepak.service.DependentsService;
import in.deepak.service.PremiumService;
import in.deepak.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/insurance/user")
public class UserController {

	
	
	    private UserService userService;

	   
	    private DependentsService dependentsService;


	   
	    private PremiumService premiumService;

	    
	    private UserRepository userRepository;
;
	
	
	
	 @PostMapping("/dependents/user")
	    public ResponseEntity<DependentDto> addDependents(@Valid @RequestBody DependentDto dependentsDto,@RequestHeader String user_id) {
	        int userId = Integer.parseInt(user_id);

	        DependentDto createdDependents = this.dependentsService.addDependents(dependentsDto, userId);

	        return new ResponseEntity<>(createdDependents, HttpStatus.CREATED);
	    }

	   
	    @GetMapping("/dependents/user")
	    public ResponseEntity<List<DependentDto>> getAllDependent(@RequestHeader String user_id) {
	        int userId = Integer.parseInt(user_id);

	        List<DependentDto> dependents = this.dependentsService.getAllDependent(userId);

	        return ResponseEntity.ok(dependents);
	    }

	    @GetMapping("/dependent/{dependentId}")
	    public ResponseEntity<DependentDto> getDependentById(@PathVariable Integer dependentId) {
	    	
	        DependentDto dependent = this.dependentsService.getDependentById(dependentId);
	        
	        return ResponseEntity.ok(dependent);
	    }

	   
	    @GetMapping("/premium/user")
	    public List<PremiumDto> viewPolicies(@RequestHeader String user_id) {
	        int userId = Integer.parseInt(user_id);
	        
	        List<PremiumDto> premiums = this.premiumService.viewPremium(userId);
	        
	        return premiums;
	    }
	   
	    @GetMapping("/getPremium/user/{userId}/policy/{policyId}")
	    public ResponseEntity<PremiumDto> getPremiumByUserIdAndPolicyId(@PathVariable Integer userId, @PathVariable Integer policyId) {

	    	PremiumDto premiumDto = this.premiumService.getPremiumByUserIdAndPolicyId(userId, policyId);
	    	
	        return ResponseEntity.ok(premiumDto);
	    }
	
	
	
	
	
}
