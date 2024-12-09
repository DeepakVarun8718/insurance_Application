package in.deepak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import in.deepak.entities.Policy;

//
@Repository
public interface PolicyRepository extends JpaRepository<Policy,Integer> {

}
