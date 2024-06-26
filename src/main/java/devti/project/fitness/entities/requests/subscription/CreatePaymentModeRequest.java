package devti.project.fitness.entities.requests.subscription;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentModeRequest {

    private List<CreatePaymentTrancheRequest> paymentTranches;

}
