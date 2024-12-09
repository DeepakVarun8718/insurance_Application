package in.deepak.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PremiumDto {
	
	private int premiumId;
    private int monthlyPremium;
    

}
