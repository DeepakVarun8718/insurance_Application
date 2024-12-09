package in.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.payloads.DependentDto;

@Service
public interface DependentsService {

	 DependentDto addDependents(DependentDto dependentsDto,int userId);
	    DependentDto getDependentById(Integer dependentId);
	    List<DependentDto> getAllDependent(Integer userId);
}
