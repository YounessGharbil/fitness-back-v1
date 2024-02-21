package devti.project.fitness.entities.requests.payment;

import devti.project.fitness.entities.PaymentTranche;
import devti.project.fitness.entities.enums.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardPaymentRequest {
	
	 private PaymentTranche paymentTranche;

	 @Enumerated(EnumType.STRING)
	 private PaymentMethod  paymentMethod;

	 private double amount;
	    
	 private String paymentDate;
	 
		private String cardNumber; 
	    private String cardExpirationDate; 
	    private String cardCVV; 
	    private int transactionNumber;
	    private Long subscriptionid;
	    
	 
}
