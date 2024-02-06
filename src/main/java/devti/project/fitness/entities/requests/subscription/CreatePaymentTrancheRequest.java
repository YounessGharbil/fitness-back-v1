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
public class CreatePaymentTrancheRequest {
	
	private double amount;
	private String dueDate;
	private boolean isTranchePaid;
	private PaymentMode paymentMode;

}
