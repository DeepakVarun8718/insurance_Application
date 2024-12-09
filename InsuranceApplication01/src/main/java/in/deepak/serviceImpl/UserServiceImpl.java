package in.deepak.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import in.deepak.entities.User;
import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payloads.UserDto;
import in.deepak.repository.UserRepository;
import in.deepak.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	
	private UserRepository userRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public UserDto getUserById(Integer userId) throws ResourceNotFoundException {
		User user=userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User not Found with "+userId));
		
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
	
		List<User> userList=userRepository.findAll();
		
		return userList.stream().map(user->modelMapper.map(user, UserDto.class)).toList();
	}

}
