package in.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payloads.PolicyDto;

@Service
public interface PolicyService {
	
    PolicyDto createPolicy(PolicyDto policyDto,Integer ruleId,Integer categoryId)throws ResourceNotFoundException ;
    
    PolicyDto getPolicyById(Integer policyId)throws ResourceNotFoundException ;
    
    List<PolicyDto> getAllPolicies()throws ResourceNotFoundException ;
    
}

