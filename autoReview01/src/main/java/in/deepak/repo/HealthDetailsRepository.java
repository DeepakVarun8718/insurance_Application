package in.deepak.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.deepak.entity.HealthDetails;

@Repository
public interface HealthDetailsRepository extends JpaRepository<HealthDetails,Integer> {
}