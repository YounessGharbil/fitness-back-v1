package devti.project.fitness.entities.requests.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserAccountRequest {
	
	 private String gymid;
	 private String password;
	 private String role_name;

}
