package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.ContactRepository;
import devti.project.fitness.entities.Contact;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactService {
	
    private final ContactRepository contactRepository;

    public Contact getContact(Long id) {
    	Contact contact=contactRepository.findById(id).isPresent()?contactRepository.findById(id).get():null;

        return contact;
    }
    
    public List<Contact> getContacts() {

        return contactRepository.findAll();

    }
    
	 public Contact createContact(@Valid Contact contact) {
		 	
	        return contactRepository.save(contact);
	    }
	 
	 public Contact updateContact(@Valid Contact contact) {	        
	       
	        return contactRepository.save(contact);
	    }
	 
	 public String deleteContact(Long id) {
		 	contactRepository.deleteById(id);
	        return "deleted successfully";
	    }
	 
	 public String createContacts(List<Contact> contacts) {
	        try{
	            for(Contact contact:contacts){
	                createContact(contact);
	            }
	        }
	        catch(Exception exception){
	            exception.printStackTrace();
	        }
	        return "salaries uploaded successfully";
	    }

}
