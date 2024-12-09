package in.deepak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.deepak.entities.PolicyRule;


@Repository
public interface PolicyRuleRepository extends JpaRepository<PolicyRule, Integer>{

}
