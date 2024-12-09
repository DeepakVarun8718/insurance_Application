package in.deepak.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import in.deepak.entities.PolicyRule;
import in.deepak.payloads.PolicyRuleDto;
import in.deepak.repository.PolicyRuleRepository;
import in.deepak.service.PolicyRuleService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PolicyRuleServiceImpl implements PolicyRuleService {
	
	  private PolicyRuleRepository policyRuleRepository;

	  private ModelMapper modelMapper;

	@Override
	public PolicyRuleDto createPolicyRule(PolicyRuleDto policyRuleDto) {
		
		  PolicyRule policyRule = modelMapper.map(policyRuleDto, PolicyRule.class);
		  
	        policyRule.setCreatedDate(LocalDate.now());
	        PolicyRule createdPolicyRule = policyRuleRepository.save(policyRule);
	        
	        return modelMapper.map(createdPolicyRule, PolicyRuleDto.class);
		
	}

	@Override
	public PolicyRuleDto getPolicyRuleByRuleId(Integer ruleId) {
		
		 PolicyRule policyRule = policyRuleRepository.findById(ruleId)
	                .orElseThrow();
	        return modelMapper.map(policyRule, PolicyRuleDto.class);
	}

	@Override
	public List<PolicyRuleDto> getAllPolicyRules() {
		
		List<PolicyRule> policyRules = policyRuleRepository.findAll();
      return policyRules.stream().map(rules -> modelMapper.map(rules, PolicyRuleDto.class)).toList();
		
	}
	  
	  
	    
	    

}
