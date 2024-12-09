package in.deepak.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {
	
	    private String message;
	    private boolean success;
	    
	    public ApiResponse(String message, Boolean success) {
	        this.message = message;
	        this.success = success;
	    }

}
