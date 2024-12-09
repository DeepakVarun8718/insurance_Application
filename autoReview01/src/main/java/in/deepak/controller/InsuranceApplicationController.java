package in.deepak.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.deepak.payload.ApiResponse;
import in.deepak.payload.HeathDetailsDto;
import in.deepak.payload.InsuranceApplicationDto;
import in.deepak.repo.InsuranceApplicationRepository;
import in.deepak.service.ApplicationClient;
import in.deepak.service.InsuranceApplicationService;
import in.deepak.service.NotificationClient;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("policy")
public class InsuranceApplicationController {

	 private final InsuranceApplicationService insuranceApplicationService;
	    
	    
	 @PostMapping("/buypolicy/policy/{policyId}/dependent/{dependentId}/buy")
	    public ResponseEntity<ApiResponse> buypolicy(@Valid @RequestBody HeathDetailsDto healthDetailsDto, @RequestHeader String user_id, @PathVariable Integer policyId, @PathVariable Integer dependentId){
	        Integer userId = Integer.parseInt(user_id);
	        System.out.println(userId);
	        
	        String str = this.insuranceApplicationService.buyPolicy(healthDetailsDto,userId,policyId,dependentId);
	        
	        return new ResponseEntity<>(new ApiResponse(str, true), HttpStatus.OK);
	    }
	 
	    @GetMapping("/applications")
	    public ResponseEntity<List<InsuranceApplicationDto>> getAllPolicies(@RequestHeader String user_id){
	        Integer id = Integer.parseInt(user_id);
	        System.out.println(id);
	        
	        List<InsuranceApplicationDto> policies = this.insuranceApplicationService.getAllPolicies();
	        
	        return ResponseEntity.ok(policies);

	    }
	    
}
