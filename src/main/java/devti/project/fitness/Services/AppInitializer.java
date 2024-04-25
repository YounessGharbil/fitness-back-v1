package devti.project.fitness.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import devti.project.fitness.Repositories.AuthorityRepository;
import devti.project.fitness.Repositories.ContactRepository;
import devti.project.fitness.Repositories.RoleRepository;
import devti.project.fitness.Repositories.UserAccountRepository;
import devti.project.fitness.entities.Authority;
import devti.project.fitness.entities.Contact;
import devti.project.fitness.entities.Role;
import devti.project.fitness.entities.UserAccount;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppInitializer implements CommandLineRunner {
	
    private final RoleRepository roleRepository;
    private final UserAccountRepository userRepository;
    private final ContactRepository contactRepository;
    private final PasswordEncoder passwordEncoder;
	 private final AuthorityRepository authorityRepository;



    

	@Override
	public void run(String... args) throws Exception {

        initializeRoles();
        createAdminAccount();
		
	}
	
	private void initializeRoles() {
        createRoleIfNotExists("ROLE_CLIENT");
        createRoleIfNotExists("ROLE_ADMIN");
    }
	
	private void createRoleIfNotExists(String roleName) {
		
        if (!roleRepository.findByRolename(roleName).isPresent()) {

            Role role = new Role();
            
            role.setRolename(roleName);
            
            if(roleName.equals("ROLE_ADMIN")) {
            	
            	 ArrayList<String> authoritiesList = new ArrayList<String>(
            			 Arrays.asList("CONTACTS_MANAGEMENT","CLIENTS_MANAGEMENT","SUBSCRIPTIONS_MANAGEMENT",
            					 	   "PAYMENTS_MANAGEMENT","STAFFS_MANAGEMENT","ROLES_MANAGEMENT","USERS_MANAGEMENT"
            					 	  ,"PACKAGES_MANAGEMENT","OBSERVATIONS_MANAGEMENT")
            			 );
            	 
             	List<Authority> authorities =new ArrayList<Authority>();
            	 
            	for(String authorityName:authoritiesList) {
            		 
            		 Authority authority=createAuthority(authorityName);
            		 
                 	 authorities.add(authority);
            		 
            	 }
            	
                role.setAuthorities(authorities);

            }
            
            roleRepository.save(role);
            
        }
        
    }
	private Authority createAuthority(String authorityName) {
		
		Authority newAuthority = new Authority();
		
        newAuthority.setName(authorityName);

		return authorityRepository.save(newAuthority);
	}

	private void createAdminAccount() {
		Contact adminContact=Contact.builder()
				.nom("admin")
				.prenom("admin")
				.adresse("admin address")
				.dateNaissance("12/12/12")
				.codePostal("90000")
				.sexe("HOMME")
				.tel("06115018418")
				.ville("tanger")
				.email("admin@mail.com")
				.build();
		Contact createdContact =contactRepository.save(adminContact);
		Role roleAdmin=roleRepository.findByRolename("ROLE_ADMIN").get();
		UserAccount adminAccount=UserAccount.builder()
				.role(roleAdmin)
				.contact(createdContact)
				.password(passwordEncoder.encode("123456"))
				.email(createdContact.getEmail())
				.build();
		this.userRepository.save(adminAccount);
	}

}
