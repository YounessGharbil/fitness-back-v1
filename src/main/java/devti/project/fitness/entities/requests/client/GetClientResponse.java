package devti.project.fitness.entities.requests.client;


import devti.project.fitness.entities.Contact;
import devti.project.fitness.entities.requests.subscription.GetSubscriptionResponse;
import devti.project.fitness.entities.requests.user.GetUserAccountResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetClientResponse {

	private  Long id;
	
	private Contact contact;
	
	private String gymid;
	
	private GetSubscriptionResponse subscription;
	
	private GetUserAccountResponse userAccount;

}
