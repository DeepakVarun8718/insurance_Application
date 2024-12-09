package in.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.payloads.PolicyRuleDto;

@Service
public interface PolicyRuleService {

	 PolicyRuleDto createPolicyRule(PolicyRuleDto policyRuleDto);
	 
	    PolicyRuleDto getPolicyRuleByRuleId(Integer ruleId);
	    
	    List<PolicyRuleDto> getAllPolicyRules();
}
