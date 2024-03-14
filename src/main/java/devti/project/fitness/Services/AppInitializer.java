package devti.project.fitness.Services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import devti.project.fitness.Repositories.ContactRepository;
import devti.project.fitness.Repositories.RoleRepository;
import devti.project.fitness.Repositories.UserAccountRepository;
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
            roleRepository.save(role);
        }
    }
	private void createAdminAccount() {
		Contact adminContact=Contact.builder()
				.nom("admin")
				.prenom("admin")
				.adresse("admin adress")
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
