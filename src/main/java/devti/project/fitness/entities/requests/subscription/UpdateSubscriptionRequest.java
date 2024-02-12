package devti.project.fitness.entities.requests.subscription;


import devti.project.fitness.entities.PaymentMode;
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
	 	 
	 private Long subscribedPackage_id;
	 
	 private String status;
	 
	 private int discount;
	 
	 private PaymentMode paymentMode; 
	 

}
