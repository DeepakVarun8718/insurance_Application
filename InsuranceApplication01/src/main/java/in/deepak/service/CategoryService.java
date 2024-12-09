package in.deepak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payloads.CategoryDto;

@Service
public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId)throws ResourceNotFoundException;
    
    void deleteCategory(Integer categoryId) throws ResourceNotFoundException;
    
    CategoryDto getCategory(Integer categoryId) throws ResourceNotFoundException;
    
    List<CategoryDto> getCategories()throws ResourceNotFoundException;

}
