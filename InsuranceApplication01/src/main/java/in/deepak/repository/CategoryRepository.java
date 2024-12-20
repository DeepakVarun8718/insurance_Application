package in.deepak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.deepak.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
