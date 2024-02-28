package devti.project.fitness.entities.requests.observation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetObservationResponse {
	
    private String observationType;
    
    private String content;
    
}
