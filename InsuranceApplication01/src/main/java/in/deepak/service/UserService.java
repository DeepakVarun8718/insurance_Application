package in.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payloads.UserDto;

@Service
public interface UserService {

	 UserDto getUserById(Integer userId)throws ResourceNotFoundException ;
	    List<UserDto> getAllUsers();
}
