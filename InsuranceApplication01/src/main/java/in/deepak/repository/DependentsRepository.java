package in.deepak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.deepak.entities.Dependents;

@Repository
public interface DependentsRepository extends JpaRepository<Dependents, Integer> {

}
