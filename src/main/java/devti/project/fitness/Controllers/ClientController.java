package devti.project.fitness.Controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import devti.project.fitness.Services.ClientService;
import devti.project.fitness.entities.Client;
import devti.project.fitness.entities.Package;
import devti.project.fitness.entities.PaymentMode;
import devti.project.fitness.entities.Subscription;
import devti.project.fitness.entities.UserAccount;
import devti.project.fitness.entities.requests.client.GetClientResponse;
import devti.project.fitness.entities.requests.pack.GetPackageResponse;
import devti.project.fitness.entities.requests.subscription.GetPaymentModeResponse;
import devti.project.fitness.entities.requests.subscription.GetSubscriptionResponse;
import devti.project.fitness.entities.requests.user.GetUserAccountResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Client")
@RequiredArgsConstructor
public class ClientController {
	
    private final ClientService clientService;
	
	 @GetMapping("/{id}")
	    public ResponseEntity<GetClientResponse> getClient(@PathVariable Long id) throws Exception {
		 
		 GetClientResponse response=null;
		 
		 Client client=clientService.getClient(id);
	        if(client==null){
	        	throw new RuntimeException("Client not found");
	        }
	        
	        try {
				
	        	Subscription sub=client.getSubscription();
	        	
	        	 PaymentMode paymentMode = sub.getPaymentMode();
	             
	             GetPaymentModeResponse paymentModeResponse=GetPaymentModeResponse.builder()
	             		.id(paymentMode.getId())
	             		.paymentTranches(paymentMode.getPaymentTranches())
	             		.build();
	 	        
	 	        UserAccount user=client.getUserAccount();
	 	        
	 	        Package pack=sub.getSubscribedPackage();
	 	        
	 	        GetPackageResponse package_response=
	 	        		GetPackageResponse.builder()
	 	        		.id(pack.getId())
	 	        		.packageName(pack.getPackageName())
	 	        		.price(pack.getPrice())
	 	        		.description(pack.getDescription())
	 	        		.durationInMonths(pack.getDurationInMonths())
	 	        		.build();
	 	        
	 	        GetSubscriptionResponse subscriptionResponse=GetSubscriptionResponse.builder()
	 	        		.id(sub.getId())
	 	        		.discount(sub.getDiscount())
	 	        		.priceAfterDiscount(sub.getPriceAfterDiscount())
	 	        		.startDate(sub.getStartDate())
	 	        		.endDate(sub.getEndDate())
	 	        		.status(sub.getStatus())
	 	        		.subscribedContact(sub.getSubscribedContact())
	 	        		.subscribedPackage(package_response)
	 	        		.paymentMode(paymentModeResponse)
	 	        		.payments(sub.getPayments())
	 	        		.build();
	 	        
	 	        GetUserAccountResponse getUserAccountResponse=GetUserAccountResponse.builder()
	 	        		.Id(user.getId())
	 	        		.contact(user.getContact())
	 	        		.email(user.getUsername())
	 	        		.password(user.getPassword())
	 	        		.role(user.getRole())
	 	        		.build();
	 	        
	 	        response=GetClientResponse.builder()
	 	        		.id(client.getId())
	 	        		.contact(client.getContact())
	 	        		.subscription(subscriptionResponse)
	 	        		.gymid(client.getGymid())
	 	        		.userAccount(getUserAccountResponse)
	 	        		.build();
	        	
			} catch (Exception e) {
				throw new Exception("An error occurred while getting client");
			}
	        
	        return new ResponseEntity<>(response,HttpStatus.OK);
	        
	    }
	    
	    @GetMapping
	    public ResponseEntity<List<GetClientResponse>> getClients() throws Exception {
	    	
	    	List<GetClientResponse> response=null;
	    	
	    	try {
	    		
	    		List<Client> clients=clientService.getClients();
		    	
		    	
		    	response=new ArrayList<GetClientResponse>();
		    	
		    	for(Client client:clients) {
		    		
		    		 Subscription sub=client.getSubscription();
		    		
		             PaymentMode paymentMode = sub.getPaymentMode();
		             
		             GetPaymentModeResponse paymentModeResponse=GetPaymentModeResponse.builder()
		             		.id(paymentMode.getId())
		             		.paymentTranches(paymentMode.getPaymentTranches())
		             		.build();
		             
		 	        
		 	        UserAccount user=client.getUserAccount();
		 	        
		 	        Package pack=sub.getSubscribedPackage();
		 	        
		 	        GetPackageResponse package_response=
		 	        		GetPackageResponse.builder()
		 	        		.id(pack.getId())
		 	        		.packageName(pack.getPackageName())
		 	        		.price(pack.getPrice())
		 	        		.description(pack.getDescription())
		 	        		.durationInMonths(pack.getDurationInMonths())
		 	        		.build();
		 	        
		 	        GetSubscriptionResponse subscriptionResponse=GetSubscriptionResponse.builder()
		 	        		.id(sub.getId())
		 	        		.discount(sub.getDiscount())
		 	        		.priceAfterDiscount(sub.getPriceAfterDiscount())
		 	        		.startDate(sub.getStartDate())
		 	        		.endDate(sub.getEndDate())
		 	        		.status(sub.getStatus())
		 	        		.subscribedContact(sub.getSubscribedContact())
		 	        		.subscribedPackage(package_response)
		 	        		.paymentMode(paymentModeResponse)
		 	        		.payments(sub.getPayments())
		 	        		.build();
		 	        
		 	        GetUserAccountResponse getUserAccountResponse=GetUserAccountResponse.builder()
		 	        		.Id(user.getId())
		 	        		.contact(user.getContact())
		 	        		.email(user.getUsername())
		 	        		.password(user.getPassword())
		 	        		.role(user.getRole())
		 	        		.build();
		    		
		    		response.add(
		    				GetClientResponse.builder()
		    				.id(client.getId())
		    				.contact(client.getContact())
		    				.gymid(client.getGymid())
		    				.subscription(subscriptionResponse)
		    				.userAccount(getUserAccountResponse)
		    				.build()
		    				);
		    	}
				
			} catch (Exception e) {
				
				throw new Exception("An error occurred while getting clients");
				
			}
	    	
	    	

	        return new ResponseEntity<>(response,HttpStatus.OK);
	    }
	    
}
