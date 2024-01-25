package devti.project.fitness.entities.requests.staff;


import devti.project.fitness.entities.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStaffRequest {
		
	private Contact contact;
	
	private String role_name;
	
}
