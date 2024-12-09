package in.deepak.payloads;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {
	
	 private Integer categoryId;

	    @NotBlank(message = "Category Title is Mandatory")
	    @Pattern(regexp = "^[\\p{L}\\s]+$", message ="Title can only contain letters and spaces")
	    @Size(min = 2, max = 50, message ="Title must be between 2 and 50 characters")
	    private String categoryTitle;


	    @NotBlank(message = "Category Description is Mandatory")
	    @Size(min = 10,message = "Description must be of minimum 10 characters")
	    private String categoryDescription;

	    private List<PolicyDto> policies = new ArrayList<>();
	    private LocalDate createdDate;
	    

}
