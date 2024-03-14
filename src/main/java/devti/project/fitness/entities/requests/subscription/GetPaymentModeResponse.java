package devti.project.fitness.entities.requests.subscription;

import java.util.List;

import devti.project.fitness.entities.PaymentTranche;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentModeResponse {
	
	    private Long id;
	    
	    private List<PaymentTranche> paymentTranches;
	    
	    private boolean isSubscriptionPaid;
	    

}
