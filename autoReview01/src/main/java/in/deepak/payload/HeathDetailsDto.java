package in.deepak.payload;

import java.time.LocalDate;

import in.deepak.enums.Disease;
import in.deepak.validator.ValidEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HeathDetailsDto {

	
	 private int healthId;
	    @NotNull(message = "AnySurgery should not be Null")
	    private Boolean anySurgery;

	    @NotNull(message = "Diseases should not be Null")
	    @ValidEnum(enumClass = Disease.class)
	    private Disease diseases;

	    @NotNull(message = "alcoholConsumption should not be Null")
	    private Boolean alcoholConsumption;

	    @NotNull(message = "tobaccoConsumption should not be Null")
	    private Boolean tobaccoConsumption;

	    @NotNull(message = "smokingStatus should not be Null")
	    private Boolean smokingStatus;
	    private LocalDate createdDate;
}
