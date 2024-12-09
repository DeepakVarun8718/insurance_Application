package in.deepak.repo;


import in.deepak.enums.PolicyStatus;
import in.deepak.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


	public interface InsuranceApplicationRepository extends JpaRepository<InsuranceApplication,Integer> {
	   
	    List<InsuranceApplication> findByUserIdAndPolicyId(Integer userId, Integer policyId);
	    
	    List<InsuranceApplication> findByPolicyStatus(PolicyStatus policyStatus);

	}

