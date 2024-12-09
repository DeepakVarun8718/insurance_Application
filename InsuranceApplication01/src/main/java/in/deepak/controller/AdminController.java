package in.deepak.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.deepak.entities.Policy;
import in.deepak.exception.ResourceNotFoundException;
import in.deepak.payloads.ApiResponse;
import in.deepak.payloads.CategoryDto;
import in.deepak.payloads.PolicyDto;
import in.deepak.payloads.PolicyRuleDto;
import in.deepak.payloads.UserDto;
import in.deepak.service.CategoryService;
import in.deepak.service.PolicyRuleService;
import in.deepak.service.PolicyService;
import in.deepak.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/insurance/admin")
@Validated
@AllArgsConstructor
public class AdminController {
	
	
	
	 
	    private UserService userService;
	  
	 
	    private CategoryService categoryService;
	   
	    
	    private PolicyService policyService;
	    
	    
	    private PolicyRuleService policyRuleService;
	    
	    @GetMapping("/user")
	    public ResponseEntity<List<UserDto>> getAllUsers(){
	    	
	        List<UserDto> users = this.userService.getAllUsers();

	        return ResponseEntity.ok(users);
	    }

	    
	    @GetMapping("/user/{userId}")
	    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) throws ResourceNotFoundException{
	    	
	        UserDto user = this.userService.getUserById(userId);

	        return ResponseEntity.ok(user);
	    }
	    
	    
	    @PostMapping("/category")
	    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
	    	
	        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
	        
	        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
	    }
	    
	    
	    
	    @PutMapping("/category/{catId}")
	    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId) throws ResourceNotFoundException{
	    	
	        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,catId);
	        
	        return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	    }

	    @DeleteMapping("/category/{catId}")
	    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) throws ResourceNotFoundException{
	    	
	        this.categoryService.deleteCategory(catId);
	        
	        return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully!!", false), HttpStatus.OK);
	    }

	    @GetMapping("/category/{catId}")
	    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) throws ResourceNotFoundException{
	    	
	        CategoryDto categoryDto = this.categoryService.getCategory(catId);
	        
	        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	    }
	   
	    @GetMapping("/category")
	    public ResponseEntity<List<CategoryDto>> getCategories() throws ResourceNotFoundException{
	    	
	        List<CategoryDto> categoryDtos = this.categoryService.getCategories();
	        
	        return  ResponseEntity.ok(categoryDtos);
	    }

	    
	    @PostMapping("/rule/{ruleId}/category/{categoryId}")
	    public ResponseEntity<PolicyDto> createPolicy(@Valid @RequestBody PolicyDto policyDto, @PathVariable Integer ruleId, @PathVariable Integer categoryId) throws ResourceNotFoundException{
	       
	    	System.out.println("Entered In CreationPolicy Api");
	    	PolicyDto createPolicy = policyService.createPolicy(policyDto,ruleId,categoryId);
	        return new ResponseEntity<PolicyDto>(createPolicy, HttpStatus.CREATED);
	    }

	   
	    @GetMapping("/policy")
	    public ResponseEntity<List<PolicyDto>> getAllPolicies() throws ResourceNotFoundException{
	    	
	        List<PolicyDto> postDtoList = this.policyService.getAllPolicies();
	        
	        return new ResponseEntity<List<PolicyDto>>(postDtoList, HttpStatus.OK);
	    }

	    @GetMapping("/policy/{policyId}")
	    public ResponseEntity<PolicyDto> getPolicyById(@PathVariable Integer policyId) throws ResourceNotFoundException{
	    	
	        PolicyDto policyDto = this.policyService.getPolicyById(policyId);
	        
	        return new ResponseEntity<PolicyDto>(policyDto,HttpStatus.OK);
	    }
	    
	    @PostMapping("/policyRule")
	    public ResponseEntity<PolicyRuleDto> createPolicyRule(@Valid @RequestBody PolicyRuleDto policyRuleDto){
	    	
	        PolicyRuleDto createdPolicyRuleDto = this.policyRuleService.createPolicyRule(policyRuleDto);
	        
	        return new ResponseEntity<>(createdPolicyRuleDto, HttpStatus.CREATED);
	    }

	    @GetMapping("/policyRule")
	    public ResponseEntity<List<PolicyRuleDto>> getAllPolicyRules(){
	    	
	        List<PolicyRuleDto> policyRules = this.policyRuleService.getAllPolicyRules();
	        
	        return ResponseEntity.ok(policyRules);
	    }

	    @GetMapping("/policyRule/{ruleId}")
	    public ResponseEntity<PolicyRuleDto> getPolicyRuleByRuleId(@PathVariable Integer ruleId){
	    	
	        PolicyRuleDto policyRuleDto = this.policyRuleService.getPolicyRuleByRuleId(ruleId);
	        
	        return ResponseEntity.ok(policyRuleDto);
	    }

}
