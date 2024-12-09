package in.deepak.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.deepak.enums.PolicyStatus;
import in.deepak.payload.ApiResponse;
import in.deepak.payload.InsuranceApplicationDto;
import in.deepak.service.ManualReviewService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("policy/underwriter")
@AllArgsConstructor
public class UnderWriterController {
	
	 private final ManualReviewService insuranceApplicationService;
	 
	 @PutMapping("changeStatus/insuranceApplication/{insuranceId}")
	    public ResponseEntity<ApiResponse> updateStatus(@PathVariable Integer insuranceId, @RequestParam("status") PolicyStatus status){

		 ApiResponse updatedStatus = this.insuranceApplicationService.updateStatus(insuranceId,status);

		 return new ResponseEntity<>(updatedStatus,HttpStatus.OK);
	    }
	 
	 
	    @GetMapping("/application/toReview")
	    public ResponseEntity<List<InsuranceApplicationDto>> getApplicationsToReview(){
	    	
	          List<InsuranceApplicationDto> insuranceApplicationDto=this.insuranceApplicationService.getAllApplicationForReview();

	          return  new ResponseEntity<>(insuranceApplicationDto, HttpStatus.OK);
	    }
	 
	 

}
