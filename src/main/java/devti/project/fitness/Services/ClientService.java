package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.ClientRepository;
import devti.project.fitness.Repositories.UserAccountRepository;
import devti.project.fitness.entities.Client;
import devti.project.fitness.entities.Contact;
import devti.project.fitness.entities.Subscription;
import devti.project.fitness.entities.UserAccount;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
	
	  private final ClientRepository clientRepository;
	  
	  private final UserAccountRepository userRepository;


	    public Client getClient(Long id) {
	    	
	    	Client client=clientRepository.findById(id).isPresent()?clientRepository.findById(id).get():null;
	
	        return client;
	    }
	    
	    public Client getClientByGymid(String gymid) {
	    	Client client=clientRepository.findByGymid(gymid).isPresent()?clientRepository.findByGymid(gymid).get():null;

	        return client;
	    }
	    
	    public Client getClientByContact(Contact contact) {
	    	Client client=clientRepository.findByContact(contact).isPresent()?clientRepository.findByContact(contact).get():null;

	        return client;
	    }
	    
	    
	    public List<Client> getClients() {
	    	
	    	List<Client> clients =clientRepository.findAll();
	    	
	        return clients;

	    }
	    
	    
		 public Client createClient(@Valid Client client) {
			 	
		        return clientRepository.save(client);
		    }
		 
		 public Client updateClient(@Valid Client client) {	        
		       
		        return clientRepository.save(client);
		    }
		 
		 public String deleteClient(Long id) {
			 Client client =clientRepository.findById(id).get();
			 
			 client.setUserAccount(null);
			 clientRepository.save(client);
			 
			 UserAccount user= client.getUserAccount();
			 userRepository.delete(user);
			 clientRepository.deleteById(id);
		        return "deleted successfully";
		    }
		 
		 @Transactional
		 public String deleteClientBySubscription(Subscription subscription) {
			 	clientRepository.deleteBySubscription(subscription);;
		        return "deleted successfully";
		    }

}
