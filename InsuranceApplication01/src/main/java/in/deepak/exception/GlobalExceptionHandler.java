package in.deepak.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import in.deepak.payloads.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	      System.out.println("-------------------MethodArgumentNotValidException -----------------"); 
		 Map<String, String> errors = new HashMap<>();
	        BindingResult bindingResult = ex.getBindingResult();
	        for (FieldError fieldError : bindingResult.getFieldErrors()) {
	            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
	        }
	        return ResponseEntity.badRequest().body(errors);
	    }
	 
	 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex){
		
		
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);		
		
	}
	
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<Map<String, String>> invalidFormatExceptionHandler(InvalidFormatException ex){
		
		System.out.println("-----------------------------------InvalidFormatException------------------");
		Map<String,String> errors=new HashMap<>();
		
		String fieldName =ex.getPath().get(0).getFieldName();
		String invalidValue=ex.getValue().toString();
		String message="Invalid "+invalidValue+" For Field Name "+fieldName+"'";
		errors.put(fieldName, message);
		
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST);
		
		
	}
	
	@ExceptionHandler(InvalidOtpException.class)
	public ResponseEntity<ApiResponse> invalidOtpExceptionHandler(InvalidOtpException ex){
		
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);		
		
	}
	
	
	
}
