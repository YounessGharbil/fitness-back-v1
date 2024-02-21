package devti.project.fitness.entities.requests.subscription;

import devti.project.fitness.entities.PaymentTranche;
import devti.project.fitness.entities.Subscription;
import devti.project.fitness.entities.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentResponse {
	
	    private Long id;

	    private PaymentTranche paymentTranche;

	    private PaymentMethod paymentMethod;

	    private double amount;

	    private String paymentDate;
	   
	    private Subscription subscription;
	    
		private String checkNumber; 

		private String cardNumber; 
	    private String cardExpirationDate; 
	    private String cardCVV; 
	    private int transactionNumber;

}
