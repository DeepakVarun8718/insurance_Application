package in.deepak.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import feign.FeignException;
import in.deepak.entity.InsuranceApplication;
import in.deepak.enums.PolicyStatus;
import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payload.ApiResponse;
import in.deepak.payload.InsuranceApplicationDto;
import in.deepak.repo.InsuranceApplicationRepository;
import in.deepak.service.ApplicationClient;
import in.deepak.service.ManualReviewService;
import in.deepak.service.NotificationClient;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ManualReviewServiceImpl implements ManualReviewService{
	
	
	private InsuranceApplicationRepository insuranceApplicationRepository;

    private ModelMapper modelMapper;

    private NotificationClient notificationClient;

    private ApplicationClient applicationClient;

   

    @Override
    public List<InsuranceApplicationDto> getAllApplicationForReview() {
    	
        List<InsuranceApplication> insuranceApplications=this.insuranceApplicationRepository.findByPolicyStatus(PolicyStatus.IN_REVIEW);
        
        return insuranceApplications.stream().map(insuranceApplication -> modelMapper.map(insuranceApplication,InsuranceApplicationDto.class)).toList();
    }

    @Override
    public ApiResponse updateStatus(Integer insuranceId, PolicyStatus status) {
    	
        InsuranceApplication insuranceApplication = this.insuranceApplicationRepository.findById(insuranceId).orElseThrow(()->new ResourceNotFoundException("Insurance application","Insurance Id",insuranceId));
        insuranceApplication.setPolicyStatus(status);
        insuranceApplication.setUpdatedDate(LocalDate.now());
        this.insuranceApplicationRepository.save(insuranceApplication);
        String email = null;
        try {
         email = applicationClient.getUserByUserId(insuranceApplication.getUserId()).getEmail();
        } catch (FeignException.NotFound e) {
        	
                throw new ResourceNotFoundException("insurance application ","Id",insuranceId);
        } catch (FeignException e) {
        	
        } catch (Exception e) {
        	
        }
        notificationClient.sendEmail(email,"your application is "+status);
        
        return new ApiResponse("your application is "+status, true);
    }

}
