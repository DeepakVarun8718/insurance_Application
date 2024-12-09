package in.deepak.payloads;

import java.time.LocalDate;

import in.deepak.enums.Diseases;
import in.deepak.validation.ValidEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthDetailsDto {

	
	private int healthId;
    @NotNull(message = "AnySurgery should not be Null")
    private Boolean anySurgery;

    @NotNull(message = "Diseases should not be Null")
    @ValidEnum(enumClass = Diseases.class)
    private Diseases diseases;

    @NotNull(message = "alcoholConsumption should not be Null")
    private Boolean alcoholConsumption;

    @NotNull(message = "tobaccoConsumption should not be Null")
    private Boolean tobaccoConsumption;

    @NotNull(message = "smokingStatus should not be Null")
    private Boolean smokingStatus;
    private LocalDate createdDate;
}