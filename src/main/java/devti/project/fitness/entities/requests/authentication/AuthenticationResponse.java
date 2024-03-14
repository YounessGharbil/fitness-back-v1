package devti.project.fitness.entities.requests.authentication;

import devti.project.fitness.entities.requests.user.GetUserAccountResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
	 	private String token;
	    private GetUserAccountResponse userAccount;
}
