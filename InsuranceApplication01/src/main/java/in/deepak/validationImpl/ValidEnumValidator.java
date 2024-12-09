package in.deepak.validationImpl;

import java.time.LocalDate;
import java.time.Period;

import org.aspectj.weaver.ast.Instanceof;

import in.deepak.validation.AdultAge;
import in.deepak.validation.ValidEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidEnumValidator implements ConstraintValidator<ValidEnum, Enum<?>>{
	
	private Class<? extends Enum<?>> enumclass;
	
	@Override
	public void initialize(ValidEnum consValidEnum) {
		
		this.enumclass=consValidEnum.enumClass();
	}

	@Override
	public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
		
		
		if (value == null) {
            return false; // Null values are considered invalid
        }
        for (Enum<?> enumConstant : enumclass.getEnumConstants()) {
            if (enumConstant.name().equals(value.name())) {
                return true;
            }
        }
        return false;
    }
	}

	


