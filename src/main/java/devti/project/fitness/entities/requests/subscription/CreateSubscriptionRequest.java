package devti.project.fitness.entities.requests.subscription;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubscriptionRequest {
	
	 
	 private int discount;
	 	 
	 private String status;
	 
	 private Long subscribedPackage_id;
	
	 private Long subscribedContact_id;

}
