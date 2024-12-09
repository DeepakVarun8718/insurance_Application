package in.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.enums.PolicyStatus;
import in.deepak.payload.ApiResponse;
import in.deepak.payload.InsuranceApplicationDto;

@Service
public interface ManualReviewService {
    
	
    List<InsuranceApplicationDto> getAllApplicationForReview();
    
    ApiResponse updateStatus(Integer insuranceId, PolicyStatus status);
}
