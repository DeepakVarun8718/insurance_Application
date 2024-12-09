package in.deepak.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import in.deepak.entities.Category;
import in.deepak.entities.Policy;
import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payloads.PolicyDto;
import in.deepak.repository.CategoryRepository;
import in.deepak.repository.PolicyRepository;
import in.deepak.service.PolicyService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PolicyServiceImpl implements PolicyService {
	
	  private ModelMapper modelMapper;

	    private PolicyRepository policyRepository;

	    private CategoryRepository categoryRepository;
	

	@Override
	public PolicyDto createPolicy(PolicyDto policyDto, Integer ruleId, Integer categoryId) throws ResourceNotFoundException {
		
		System.out.println("Entered in PolicyService");
		Policy policy=modelMapper.map(policyDto, Policy.class);
		Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(""));
	   
		policy.setCreatedDate(LocalDate.now());
		policy.setRuleId(ruleId);
		policy.setCategory(category);
		
		Policy createPolicy=policyRepository.save(policy);
		
		return modelMapper.map(createPolicy, PolicyDto.class);
		
	}

	@Override
	public PolicyDto getPolicyById(Integer policyId)throws ResourceNotFoundException  {
		
		 Policy policy = policyRepository.findById(policyId).orElseThrow();
		 
	     return modelMapper.map(policy, PolicyDto.class);
	}

	@Override
	public List<PolicyDto> getAllPolicies()throws ResourceNotFoundException  {
		
		
		 List<Policy> policies = policyRepository.findAll();
		 
         return policies.stream().map(policy -> modelMapper.map(policy, PolicyDto.class)).toList();
	}
	
	
	
	
	
	

}
