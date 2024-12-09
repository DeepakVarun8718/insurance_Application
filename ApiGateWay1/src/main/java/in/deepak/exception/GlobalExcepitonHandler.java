package in.deepak.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.deepak.payload.ApiResponse;

@RestControllerAdvice
 class GlobalExceptionHandler {

    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ApiResponse> TokenException(TokenException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
}