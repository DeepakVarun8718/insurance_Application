package in.deepak.validationImpl;

import java.time.LocalDate;
import java.time.Period;

import org.modelmapper.internal.bytebuddy.asm.Advice.Local;

import in.deepak.validation.AdultAge;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdultAgeValidator implements ConstraintValidator<AdultAge, LocalDate>{
	
	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		
		if(value==null || !(value instanceof LocalDate)) {
			
			return false;
		}
	
			LocalDate curr=LocalDate.now();
			Period period=Period.between(value, curr);
			int age=period.getYears();
			if(age>18) {
				return true;
			}
			
	
			return false;
	}

}
