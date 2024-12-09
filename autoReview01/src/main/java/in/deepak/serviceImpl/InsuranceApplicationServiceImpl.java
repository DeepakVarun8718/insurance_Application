package in.deepak.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import feign.FeignException;
import in.deepak.entity.HealthDetails;
import in.deepak.entity.InsuranceApplication;
import in.deepak.enums.Disease;
import in.deepak.enums.PolicyStatus;
import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payload.*;
import in.deepak.repo.HealthDetailsRepository;
import in.deepak.repo.InsuranceApplicationRepository;
import in.deepak.service.ApplicationClient;
import in.deepak.service.InsuranceApplicationService;

@Service
public class InsuranceApplicationServiceImpl implements InsuranceApplicationService {

	
	
	  private final InsuranceApplicationRepository insuranceApplicationRepository;

	    private final HealthDetailsRepository healthDetailsRepository;

	    private final ModelMapper modelMapper;

	    private final ApplicationClient applicationClient;
	    
	    
	    public InsuranceApplicationServiceImpl(InsuranceApplicationRepository insuranceApplicationRepository, HealthDetailsRepository healthDetailsRepository, ModelMapper modelMapper, ApplicationClient applicationClient) {
	        this.insuranceApplicationRepository = insuranceApplicationRepository;
	        this.healthDetailsRepository = healthDetailsRepository;
	        this.modelMapper = modelMapper;
	        this.applicationClient = applicationClient;
	    }
	    
	    
	    


	    @Override
	    public List<InsuranceApplicationDto> getAllPolicies() {
	    	
	        List<InsuranceApplication> insuranceApplications = this.insuranceApplicationRepository.findAll();
	        
	        return insuranceApplications.stream().map(insuranceApplication -> modelMapper.map(insuranceApplication,InsuranceApplicationDto.class)).toList();
	    }

	    public double updateHealthStatus(HealthDetails healthDetails) {
	    	
	        double healthScore = 100.0;

	        if (Boolean.TRUE.equals(healthDetails.getAlcoholConsumption())) {
	            healthScore -= 10.0;
	        }
	        if (Boolean.TRUE.equals(healthDetails.getTobaccoConsumption())) {
	            healthScore -= 15.0;
	        }
	        if (Boolean.TRUE.equals(healthDetails.getSmokingStatus())) {
	            healthScore -= 20.0;
	        }
	        if(Boolean.TRUE.equals(healthDetails.getAnySurgery())){
	        	
	            healthScore = 0;
	        }
	        if (healthDetails.getDiseases() != Disease.NONE){
	        	
	            healthScore = 0;
	        }
	        return healthScore;
	    }



		@Override
		public String buyPolicy(HeathDetailsDto healthDetailsDto, int userId, int policyId, int dependentId) {
			HealthDetails healthDetails = modelMapper.map(healthDetailsDto,HealthDetails.class);
	        healthDetails.setCreatedDate(LocalDate.now());
	        HealthDetails savedHealthDetails = healthDetailsRepository.save(healthDetails);
	        PremiumDto premium = null;
	        PolicyDto policyDto = null;
	        DependentDto dependentsDto = null;
	        try {
	            premium = applicationClient.getPremiumByUserIdAndPolicyId(userId,policyId);
	            policyDto = applicationClient.getPolicyById(policyId);
	            dependentsDto = applicationClient.getDependentById(dependentId);
	        } catch (FeignException.NotFound e) {
	            if (e.getMessage().contains("dependent")) {
	            	
	                throw new ResourceNotFoundException("dependent","Id",dependentId);
	            } else if (e.getMessage().contains("premium")) {
	            	
	                throw new ResourceNotFoundException("currently no policy found for user ","id",userId);
	            } else if (e.getMessage().contains("policy")) {
	            	
	                throw new ResourceNotFoundException("policy","id",policyId);
	            }
	        } catch (FeignException e) {
	        	
	        } catch (Exception e) {

	        }

	        List<InsuranceApplication> existingApplications = insuranceApplicationRepository.findByUserIdAndPolicyId(userId, policyId);
	        if (!existingApplications.isEmpty()) {
	        	
	            return "user already has the specified policy.";
	        }
	        InsuranceApplication insuranceApplication = new InsuranceApplication();
	        insuranceApplication.setPolicyName(policyDto.getPolicyName());
	        insuranceApplication.setCoverageAmount(policyDto.getCoverageAmount());
	        insuranceApplication.setMonthlyPremium(premium.getMonthlyPremium());
	        insuranceApplication.setPolicyId(policyId);
	        insuranceApplication.setUserId(userId);
	        insuranceApplication.setPolicyStatus(PolicyStatus.SUBMITED);
	        insuranceApplication.setHealthStatus(updateHealthStatus(healthDetails));
	        insuranceApplication.setHealthDetails(savedHealthDetails);
	        insuranceApplication.setNominee(dependentsDto.getDependentName());
	        insuranceApplication.setCreatedDate(LocalDate.now());
	        insuranceApplication.setUpdatedDate(LocalDate.now());
	        insuranceApplicationRepository.save(insuranceApplication);
	        
	        return "application for buying policy successfully submitted!!";
		}
}
