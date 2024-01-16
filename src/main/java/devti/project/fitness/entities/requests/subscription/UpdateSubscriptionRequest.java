package devti.project.fitness.entities.requests.subscription;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSubscriptionRequest {
	
	 private Long id;
	 	 
	 private Long package_id;
	 
	 private String status;
	 
	 private int discount;
	 

}
