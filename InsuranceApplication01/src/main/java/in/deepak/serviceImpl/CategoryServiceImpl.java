package in.deepak.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import in.deepak.entities.Category;
import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payloads.CategoryDto;
import in.deepak.repository.CategoryRepository;
import in.deepak.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	
	    private ModelMapper modelMapper;


	    private CategoryRepository categoryRepository;
	    
	    

		@Override
		@Transactional
		public CategoryDto createCategory(CategoryDto categoryDto) {
			
			Category category=modelMapper.map(categoryDto, Category.class);
			category.setCreatedDate(LocalDate.now());
			Category addCategory=categoryRepository.save(category);
			
			return modelMapper.map(addCategory, CategoryDto.class);
		}

		@Override
		public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) throws ResourceNotFoundException {
			
			Category category=getCategoryConstant(categoryId);
			
			category.setCategoryTitle(categoryDto.getCategoryTitle());
			category.setCategoryTitle(categoryDto.getCategoryTitle());
			
			Category updateCategory=categoryRepository.save(category);
			
			return modelMapper.map(updateCategory, CategoryDto.class);
		}

		@Override
		public void deleteCategory(Integer categoryId) throws ResourceNotFoundException {
			
			Category category=getCategoryConstant(categoryId);
			categoryRepository.delete(category);
			
			
			
		}

		@Override
		public CategoryDto getCategory(Integer categoryId) throws ResourceNotFoundException {
	 
			Category category=getCategoryConstant(categoryId);
			
			return modelMapper.map(category, CategoryDto.class);
		}

		@Override
		public List<CategoryDto> getCategories()throws ResourceNotFoundException {
			
			List<Category> allCategories=categoryRepository.findAll();
			
			List<CategoryDto> listCategoriesDto=allCategories.stream()
					.map(cat->modelMapper.map(cat, CategoryDto.class)).toList();
			
			return listCategoriesDto;
		}
	    
	    
		private Category getCategoryConstant(int categoryId) throws ResourceNotFoundException{
	        return  categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
	    }
	    
	    
}
