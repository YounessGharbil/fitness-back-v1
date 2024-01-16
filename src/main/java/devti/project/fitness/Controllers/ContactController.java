package devti.project.fitness.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devti.project.fitness.Services.ContactService;
import devti.project.fitness.entities.Contact;
import devti.project.fitness.entities.requests.contact.CreateContactRequest;
import devti.project.fitness.entities.requests.contact.UpdateContactRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Contact")
@RequiredArgsConstructor
public class ContactController {
	
    private final ContactService contactService;
    
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody CreateContactRequest createContactRequest) throws Exception {
    	
    	Contact contact=null;
  
        try {
        	
        	contact=Contact.builder()
            		.nom(createContactRequest.getNom())
            		.prenom(createContactRequest.getPrenom())
            		.adresse(createContactRequest.getAdresse())
            		.codePostal(createContactRequest.getCodePostal())
            		.dateNaissance(createContactRequest.getDateNaissance())
            		.email(createContactRequest.getEmail())
            		.pays(createContactRequest.getPays())
            		.tel(createContactRequest.getTel())
            		.sexe(createContactRequest.getSexe())
            		.ville(createContactRequest.getVille())
            		.build();
			
		} catch (Exception e) {
			
        	throw new Exception("error occured while creating contact");

		}
        
        return new ResponseEntity<>(contactService.createContact(contact), HttpStatus.CREATED);
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        Contact contact=contactService.getContact(id);
        if(contact==null){
        	throw new RuntimeException("Contact not found");
        }
        return new ResponseEntity<>(contact,HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<Contact>> getContacts() {

        return new ResponseEntity<>(contactService.getContacts(),HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id,@RequestBody UpdateContactRequest updatContactRequest) {
        Contact contact=contactService.getContact(id);
       
        if(contact==null){
        	throw new RuntimeException("Contact not found");

        }
        contact.setNom(updatContactRequest.getNom());
        contact.setPrenom(updatContactRequest.getPrenom());
        contact.setAdresse(updatContactRequest.getAdresse());
        contact.setCodePostal(updatContactRequest.getCodePostal());
        contact.setDateNaissance(updatContactRequest.getDateNaissance());
        contact.setEmail(updatContactRequest.getEmail());
        contact.setPays(updatContactRequest.getPays());
        contact.setTel(updatContactRequest.getTel());
        contact.setSexe(updatContactRequest.getSexe());
        contact.setVille(updatContactRequest.getVille());

        return new ResponseEntity<>(contactService.updateContact(contact),HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact( @PathVariable Long id) {
        Contact contact=contactService.getContact(id);

        if(contact==null){
        	
        	throw new RuntimeException("Contact not found");

        }
        contactService.deleteContact(id);
        return new ResponseEntity<>("deleted successfully",HttpStatus.NO_CONTENT);
    }



	
}
