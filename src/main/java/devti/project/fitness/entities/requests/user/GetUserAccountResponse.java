package devti.project.fitness.entities.requests.user;

import devti.project.fitness.entities.Contact;
import devti.project.fitness.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserAccountResponse {

	   private Long Id;

	   private Contact contact;
	   	   
	   private String email;
	   
	   private String password;
	   
	   private Role role;

}
