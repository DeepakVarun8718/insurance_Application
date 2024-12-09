package in.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.payloads.PremiumDto;

@Service
public interface PremiumService {

	void calculateAndInsertPremiums(int userId);
	
    PremiumDto getPremiumByUserIdAndPolicyId(int userId,int policyId);
    
    List<PremiumDto> viewPremium(int userId);
}

