package in.deepak.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import in.deepak.entities.Dependents;
import in.deepak.entities.User;
import in.deepak.payloads.DependentDto;
import in.deepak.repository.DependentsRepository;
import in.deepak.repository.UserRepository;
import in.deepak.service.DependentsService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DependentServiceImpl implements DependentsService {
	
	  private DependentsRepository dependentsRepository;

	  private ModelMapper modelMapper;

	  private UserRepository userRepository;

	@Override
	public DependentDto addDependents(DependentDto dependentsDto, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DependentDto getDependentById(Integer dependentId) {
		 Dependents dependents = dependentsRepository.findById(dependentId)
	                .orElseThrow();
	        return modelMapper.map(dependents, DependentDto.class);
	}

	@Override
	public List<DependentDto> getAllDependent(Integer userId) {
		
		
		 User user = userRepository.findById(userId)
	                .orElseThrow();
	        List<Dependents> dependents = user.getDependents();
	        List<DependentDto> dependentsDtos = dependents.stream()
	                .map(dependy -> modelMapper.map(dependy, DependentDto.class))
	                .toList();
	        
	        return dependentsDtos;
	}
	  
	  

}
