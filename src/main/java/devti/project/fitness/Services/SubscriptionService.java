package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import devti.project.fitness.Repositories.ClientRepository;
import devti.project.fitness.Repositories.PackageRepository;
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
	 
	 private final PackageRepository packageRepository;



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
			 
//			 Package pack=sub.getSubscribedPackage();
			 
//			 List<Subscription> subscribtions=pack.getSubscriptions();
//			 System.out.print("--------------------------------------------");
//			 System.out.print(subscribtions);
//			 System.out.print("--------------------------------------------");
			 
//			 pack.setSubscriptions(subscribtions);
			 
		        Client  client =clientRepository.findByContact(sub.getSubscribedContact()).get();
		        
		        client.setSubscription(null);
		        
		        clientRepository.save(client);
		        
//		        packageRepository.save(pack);
			 
			    subscriptionRepository.deleteById(id);
			    
//			    subscribtions.remove(sub);
			 
		        return "deleted successfully";
		        
		    }

}
