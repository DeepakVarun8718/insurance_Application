package in.deepak.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.deepak.entities.User;
import in.deepak.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	 Optional<User> findByEmail(String email);
	    Boolean existsByRole(Role role);
	    boolean existsByEmail(String email);
	    boolean existsByAadhaarNumber(String aadhaarNumber);
}
