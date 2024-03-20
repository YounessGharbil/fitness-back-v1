package devti.project.fitness.entities.requests.observation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateObservationRequest {
	
	private Long clientId;
    private String observationType;
    private String content;
    private String createdBy;


}
