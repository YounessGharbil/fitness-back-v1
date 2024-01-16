package devti.project.fitness.entities.requests.subscription;

import devti.project.fitness.entities.Contact;
import devti.project.fitness.entities.requests.pack.GetPackageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetSubscriptionResponse {
	
	 private Long id;
	 
	 private int discount;
	 
	 private double priceAfterDiscount;
	 
	 private String startDate;
	 
	 private String endDate;
	 
	 private String status;
	
	 private GetPackageResponse subscribedPackage;

	 private Contact subscribedContact;
	 

}
