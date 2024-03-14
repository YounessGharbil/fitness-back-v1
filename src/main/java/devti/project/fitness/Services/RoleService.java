package devti.project.fitness.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.AuthorityRepository;
import devti.project.fitness.Repositories.RoleRepository;
import devti.project.fitness.entities.Authority;
import devti.project.fitness.entities.Role;
import devti.project.fitness.entities.requests.role.CreateRoleRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	
	 private final RoleRepository roleRepository;
	 private final AuthorityRepository authorityRepository;

	 
	 public Role getRole(Long id) {
	    	Role role=roleRepository.findById(id).isPresent()?roleRepository.findById(id).get():null;

	        return role;
	    }
	 
	 public Role getRoleByRole_name(String role_name) {
	    	Role role=roleRepository.findByRolename(role_name).isPresent()?roleRepository.findByRolename(role_name).get():null;

	        return role;
	    }
	    
	    public List<Role> getRoles() {

	        return roleRepository.findAll();

	    }
	    
	    public Role createRole(CreateRoleRequest createRoleRequest) {
	        List<Authority> authorities = new ArrayList<>();
	        for (String authorityName : createRoleRequest.getAuthorities()) {
	            Authority authority = createOrGetAuthority(authorityName);
	            authorities.add(authority);
	        }

	    	 Role role=Role.builder()
	    			 .rolename(createRoleRequest.getRolename())
	    			 .authorities(authorities)
	    			 .build();
		 	
	        return roleRepository.save(role);
	    }
	 
	    public Role updateRole(@Valid Role role) {	        
	       
	        return roleRepository.save(role);
	    }
	 
	    public String deleteRole(Long id) {
	    	roleRepository.deleteById(id);
	        return "deleted successfully";
	    }
	    
	    private Authority createOrGetAuthority(String authorityName) {
	    	
	        Optional<Authority> optionalAuthority = authorityRepository.findByName(authorityName);
	        return optionalAuthority.orElseGet(() -> {
	            Authority newAuthority = new Authority();
	            newAuthority.setName(authorityName);
	            return authorityRepository.save(newAuthority);
	        });
	        
	    }
	    
}
