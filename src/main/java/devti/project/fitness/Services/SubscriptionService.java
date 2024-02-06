package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import devti.project.fitness.Repositories.ClientRepository;
import devti.project.fitness.Repositories.SubscriptionRepository;
import devti.project.fitness.entities.Client;
import devti.project.fitness.entities.Subscription;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
	
	 private final SubscriptionRepository subscriptionRepository;
	 
	 private final ClientRepository clientRepository;
	 



	    public Subscription getSubscription(Long id) {
	    	
	    	Subscription sub=subscriptionRepository.findById(id).isPresent()?subscriptionRepository.findById(id).get():null;

	        return sub;
	    }
	    
	    public List<Subscription> getSubscriptions() {

	        return subscriptionRepository.findAll();

	    }
	    
		 public Subscription createSubscription(@Valid Subscription sub) {
			 	
		        return subscriptionRepository.save(sub);
		    }
		 
		 public Subscription updateSubscription(@Valid Subscription sub) {	        
		       
		        return subscriptionRepository.save(sub);
		    }
		 
		 public String deleteSubscription(Long id) {
			 
			 Subscription  sub=subscriptionRepository.findById(id).get();
			 
			 
		        Client  client =clientRepository.findByContact(sub.getSubscribedContact()).get();
		        
		        client.setSubscription(null);
		        
		        clientRepository.save(client);
		        
			 
			    subscriptionRepository.deleteById(id);
			    
			 
		        return "deleted successfully";
		        
		    }

}
