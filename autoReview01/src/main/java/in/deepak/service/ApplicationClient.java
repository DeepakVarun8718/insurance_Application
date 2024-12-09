package in.deepak.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import in.deepak.payload.DependentDto;
import in.deepak.payload.PolicyDto;
import in.deepak.payload.PolicyRuleDto;
import in.deepak.payload.PremiumDto;
import in.deepak.payload.UserDto;

@Service
@FeignClient(name="INSURANCE-APPLICATION01")
public interface ApplicationClient {
	
	    @GetMapping("/insurance/user/getPremium/user/{userId}/policy/{policyId}")
	    PremiumDto getPremiumByUserIdAndPolicyId(@PathVariable int userId,@PathVariable int policyId);


	   
	    @GetMapping("/insurance/admin/policy/{policyId}")
	    PolicyDto getPolicyById(@PathVariable Integer policyId);
	    
	    
	    @GetMapping("/insurance/admin/policyRule/{ruleId}")
	    PolicyRuleDto getPolicyRuleById(@PathVariable Integer ruleId);
	    
	    @GetMapping("/insurance/admin/user/{userId}")
	    UserDto getUserByUserId(@PathVariable Integer userId);
	    
	    
	    @GetMapping("/insurance/user/dependent/{dependentId}")
	    DependentDto getDependentById(@PathVariable Integer dependentId);


	
}
