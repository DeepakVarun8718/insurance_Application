package in.deepak.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import feign.FeignException;
import in.deepak.entity.InsuranceApplication;
import in.deepak.enums.PolicyStatus;
import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payload.PolicyDto;
import in.deepak.payload.PolicyRuleDto;
import in.deepak.repo.InsuranceApplicationRepository;
import in.deepak.service.ApplicationClient;
import in.deepak.service.NotificationClient;
import in.deepak.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewServiceimpl implements ReviewService {

	
	    private InsuranceApplicationRepository insuranceApplicationRepository;
	    private ApplicationClient applicationClient;
	    private NotificationClient notificationClient;
	    
	    
	    @Scheduled(fixedDelayString = "PT60S")
	    @Override
	    @Transactional
	    public void autoApproveInsuranceApplications() {
	    	
	        List<InsuranceApplication> applications = insuranceApplicationRepository.findByPolicyStatus(PolicyStatus.SUBMITED);
	        applications.forEach(application -> {
	                    if (eligibleForAutoApproval(application)) {
	                        application.setPolicyStatus(PolicyStatus.APPROVED);
	                    } else {
	                        application.setPolicyStatus(PolicyStatus.IN_REVIEW);
	                    }
	                    application.setUpdatedDate(LocalDate.now());
	                    insuranceApplicationRepository.save(application);
	            String email =null;
	            try {
	                email = applicationClient.getUserByUserId(application.getUserId()).getEmail();
	            } catch (FeignException.NotFound e) {
	                throw new ResourceNotFoundException("user","Id", application.getUserId());
	            } catch (FeignException e) {
	            	
	            } catch (Exception e) {
	            }
	            notificationClient.sendEmail(email,"your policy is "+application.getPolicyStatus());
	        });

	    }
	    
	    private boolean eligibleForAutoApproval(InsuranceApplication application) {

	    	PolicyDto policyDto = applicationClient.getPolicyById(application.getPolicyId());
	        PolicyRuleDto policyRuleDto = applicationClient.getPolicyRuleById(policyDto.getRuleId());
	        
	        return application.getHealthStatus() >= policyRuleDto.getHealthStatus();
	    }
}
