package devti.project.fitness.Services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import devti.project.fitness.Repositories.RoleRepository;
import devti.project.fitness.entities.Role;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {
	
    private final RoleRepository roleRepository;
    

	@Override
	public void run(String... args) throws Exception {

        initializeRoles();
		
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

}
