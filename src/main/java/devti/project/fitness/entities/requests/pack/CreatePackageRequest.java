package devti.project.fitness.entities.requests.pack;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePackageRequest {
	
	 	private String packageName;
	    
	    private double price;
	    
	    private String description;
	    
	    private int durationInMonths;
	    
}
