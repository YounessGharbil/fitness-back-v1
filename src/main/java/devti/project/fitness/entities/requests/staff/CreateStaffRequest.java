package devti.project.fitness.entities.requests.staff;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStaffRequest {
		
	private Long contact_id;
	
	private String role_name;
	
}
