package devti.project.fitness.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import devti.project.fitness.Services.ClientService;
import devti.project.fitness.Services.RoleService;
import devti.project.fitness.Services.UserAccountService;
import devti.project.fitness.entities.Client;
import devti.project.fitness.entities.Contact;
import devti.project.fitness.entities.Role;
import devti.project.fitness.entities.UserAccount;
import devti.project.fitness.entities.requests.user.CreateUserAccountRequest;
import devti.project.fitness.entities.requests.user.GetUserAccountResponse;
import devti.project.fitness.entities.requests.user.UpdateUserAccountRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserAccountController {

	private final UserAccountService userAccountService;
	
	private final RoleService roleService;

    private final ClientService clientService;
    
    private final PasswordEncoder passwordEncoder;
	
	@PostMapping
    public ResponseEntity<UserAccount> createUserAccount(@RequestBody CreateUserAccountRequest createUserAccountRequest) {
		
        Client client=clientService.getClientByGymid(createUserAccountRequest.getGymid());
        
        Contact contact=client.getContact();
        
        Role role=roleService.getRoleByRole_name(createUserAccountRequest.getRole_name());
        
        var user=UserAccount.builder()
        		.password(passwordEncoder.encode(createUserAccountRequest.getPassword()))
        		.email(contact.getEmail())
        		.contact(contact)
        		.role(role)
                .build();

        return new ResponseEntity<>(userAccountService.createUserAccount(user), HttpStatus.CREATED);
    }
	
	@PutMapping
	 public ResponseEntity<UserAccount> updateUserAccount(@RequestBody UpdateUserAccountRequest updateUserAccountRequest) {
				
		UserAccount user=userAccountService.getUserAccount(updateUserAccountRequest.getId());
		
		  if(user==null){
	            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	        }
		  
		  if(updateUserAccountRequest.getPassword()!=null) {
			  user.setPassword(passwordEncoder.encode(updateUserAccountRequest.getPassword()));
//			  user.setPassword(updateUserAccountRequest.getPassword());

		  }
		  
		  if(updateUserAccountRequest.getRole_name()!=null) {
			  
		      Role role=roleService.getRoleByRole_name(updateUserAccountRequest.getRole_name());

			  user.setRole(role);
		  }

		return  new ResponseEntity<>(userAccountService.updateUserAccount(user), HttpStatus.OK);
	}
	
	
	 @GetMapping("/{id}")
	 public ResponseEntity<GetUserAccountResponse> getUserAccount(@PathVariable Long id) {
		 UserAccount user=userAccountService.getUserAccount(id);
	        if(user==null){
	            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	        }
	        
	        GetUserAccountResponse response=GetUserAccountResponse.builder()
	        		.Id(user.getId())
	        		.contact(user.getContact())
	        		.email(user.getUsername())
	        		.password(user.getPassword())
	        		.role(user.getRole())
	        		.build();
	        
	        return new ResponseEntity<>(response,HttpStatus.OK);
	    }
	 
	 @GetMapping
	 public ResponseEntity<List<GetUserAccountResponse>> getUserAccounts() {
		 
		 List<UserAccount> userAccounts=userAccountService.getAllUserAccounts();
		 
		 List<GetUserAccountResponse> response=new ArrayList<GetUserAccountResponse>();
	    	
	    	for(UserAccount user:userAccounts) {
	    		response.add(
	    				GetUserAccountResponse.builder()
	    				.Id(user.getId())
	    				.contact(user.getContact())
	    				.email(user.getEmail())
	    				.password(user.getPassword())
	    				.role(user.getRole())
	    				.build()
	    				);
	    		
	    	}

	        return new ResponseEntity<>(response,HttpStatus.OK);
	    }
	
	
}
