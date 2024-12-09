package in.deepak.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import in.deepak.validationImpl.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface UniqueEmail {

	String message() default "Email is Already exists";
	
	Class<?>[] groups() default {};
	 
	 Class<? extends Payload>[] payload() default {};
}
