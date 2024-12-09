package in.deepak.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import in.deepak.entities.Policy;
import in.deepak.entities.User;
import in.deepak.enums.EmploymentType;
import in.deepak.payloads.PremiumDto;
import in.deepak.repository.PolicyRepository;
import in.deepak.repository.UserRepository;
import in.deepak.service.PolicyService;
import in.deepak.service.PremiumService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PremiumServiceImpl implements PremiumService{
	
	    private PolicyService policyService;
	    
	    private PolicyRepository policyRepository;
	    
	    private UserRepository userRepository;
	    
	    private ModelMapper modelMapper;

	    
	    private Map<Integer, Map<Integer, Double>> userPremiumsMap = new HashMap<>();
	    
		@Override
		public void calculateAndInsertPremiums(int userId) {
		
			userPremiumsMap.remove(userId);
			List<Policy> policies=policyRepository.findAll();
			
			User user=userRepository.findById(userId).orElseThrow();
			
			Map<Integer, Double> premiumsMap=userPremiumsMap.computeIfAbsent(userId, k-> new HashMap<>());
			
			for (Policy policy : policies) {
	            int policyId = policy.getPolicyId();
	            double premium = calculatePremium(user,policy);
	            premiumsMap.put(policyId, premium);
	        }
			
		}
		
		public Double calculatePremium(User user,Policy policy) {
			
			double basePremium = 200;
	        int numberOfDependents= user.getDependents().size();
	        int age = calculateAge(user.getDob());
	        
	        if (age < 30) {
	            if(numberOfDependents==1){
	                basePremium *= 0.9;
	            }
	            else if (numberOfDependents==2){
	                basePremium *= 0.6;
	            }
	            else {
	                basePremium *= 0.3;
	            }

	        } else if (age >= 30 ) {
	            if(numberOfDependents==1){
	                basePremium *=1.1;
	            }
	            else if (numberOfDependents==2){
	                basePremium *= 1.5;
	            }
	            else {
	                basePremium *=1.9;
	            }
	        }

	        if (user.getEmploymentType().equals(EmploymentType.SALARIED)) {
	            basePremium *= 1.2;
	        } else if (user.getEmploymentType().equals(EmploymentType.SELF_EMPLOYED)) {
	            basePremium *= 1.5;
	        }
	        return basePremium;
		}

		 private int calculateAge(LocalDate dob) {
		        LocalDate now = LocalDate.now();
		        return Period.between(dob, now).getYears();
		    }
		 
		@Override
		public PremiumDto getPremiumByUserIdAndPolicyId(int userId, int policyId) {
			 Policy policy = this.policyRepository.findById(policyId).orElseThrow();

		        Map<Integer, Double> premiumsMap = userPremiumsMap.get(userId);
		        PremiumDto premiumDto = new PremiumDto();
		        if (premiumsMap != null) {
		            Double premium = premiumsMap.get(policyId);
		            if (premium != null) {
		                premiumDto.setPolicyId(policyId);
		                premiumDto.setPolicyName(policy.getPolicyName());
		                premiumDto.setMonthlyPremium(premium);
		                premiumDto.setDescription(policy.getDescription());
		                premiumDto.setCoverageAmount(policy.getCoverageAmount());
		            }
		        }
		        return premiumDto;
		}

		@Override
		public List<PremiumDto> viewPremium(int userId) {
			 Map<Integer, Double> premiumsMap = userPremiumsMap.get(userId);
		        List<PremiumDto> premiumDtoList = new ArrayList<>();

		        if (premiumsMap != null) {
		            for (Map.Entry<Integer, Double> entry : premiumsMap.entrySet()) {
		                PremiumDto premiumDto = new PremiumDto();
		                int policyId = entry.getKey();
		                Policy policy = this.policyRepository.findById(policyId).orElseThrow();
		                premiumDto.setPolicyId(policyId);
		                premiumDto.setPolicyName(policy.getPolicyName());
		                premiumDto.setMonthlyPremium(entry.getValue());
		                premiumDto.setDescription(policy.getDescription());
		                premiumDto.setCoverageAmount(policy.getCoverageAmount());
		                premiumDtoList.add(premiumDto);
		            }
		        }

		        return premiumDtoList;
		}

}
