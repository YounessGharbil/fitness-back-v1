package devti.project.fitness.Services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import devti.project.fitness.Repositories.ClientRepository;
import devti.project.fitness.Repositories.SubscriptionEventRepository;
import devti.project.fitness.Repositories.SubscriptionRepository;
import devti.project.fitness.entities.Client;
import devti.project.fitness.entities.Subscription;
import devti.project.fitness.entities.SubscriptionEvent;
import devti.project.fitness.entities.enums.SubscriptionEventType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
	
	 private final SubscriptionRepository subscriptionRepository;
	
	 private final ClientRepository clientRepository;
	 
	 private final SubscriptionEventRepository subscriptionEventRepository;

	    public Subscription getSubscription(Long id) {
	    	
	    	Subscription sub=subscriptionRepository.findById(id).isPresent()?subscriptionRepository.findById(id).get():null;

	        return sub;
	    }
	    
	    public List<Subscription> getSubscriptions() {

	        return subscriptionRepository.findAll();

	    }
	    
		 public Subscription createSubscription(@Valid Subscription sub) {
			 
		        Subscription createdSubscription = subscriptionRepository.save(sub);
		        
		        SubscriptionEvent creationEvent = SubscriptionEvent.builder()
		                .subscription(createdSubscription)
		                .eventType(SubscriptionEventType.CREATION)
		                .eventTimestamp(LocalDate.now())
		                .startDate(sub.getStartDate())
		                .endDate(sub.getEndDate())     
		                .build();
		            
		            subscriptionEventRepository.save(creationEvent);

		        return createdSubscription;
		    }
		 
		 public Subscription updateSubscription(@Valid Subscription sub) {	
			 
		        Subscription updatedSubscription = subscriptionRepository.save(sub);
		        
		        SubscriptionEvent updateEvent = SubscriptionEvent.builder()
		                .subscription(updatedSubscription)
		                .eventType(SubscriptionEventType.RENEWAL)
		                .eventTimestamp(LocalDate.now())
		                .startDate(sub.getStartDate())
		                .endDate(sub.getEndDate())
		                .build();
		            
		            subscriptionEventRepository.save(updateEvent);

		       
		        return updatedSubscription;
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
