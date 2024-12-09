package in.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.payload.HeathDetailsDto;
import in.deepak.payload.InsuranceApplicationDto;

@Service
public interface InsuranceApplicationService {
	
	
    String buyPolicy(HeathDetailsDto healthDetailsDto, int userId, int policyId,int dependentId);
    
    List<InsuranceApplicationDto> getAllPolicies();
}
