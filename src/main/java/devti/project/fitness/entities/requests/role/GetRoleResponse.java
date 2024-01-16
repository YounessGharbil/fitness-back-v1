package devti.project.fitness.entities.requests.role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetRoleResponse {
	
	private Long id;

	 private String roleName;
	 
}
