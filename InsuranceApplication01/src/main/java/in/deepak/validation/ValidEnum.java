package in.deepak.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import in.deepak.validationImpl.ValidEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Target({ElementType.FIELD , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEnumValidator.class)
public @interface ValidEnum {

	String message() default "Invalid Enum Value";
	
	Class<?>[] groups() default {};
	
	 Class<? extends Enum<?>> enumClass();
	 
	 Class<? extends Payload>[] payload() default {};
}
