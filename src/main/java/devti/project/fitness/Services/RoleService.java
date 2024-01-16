package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.RoleRepository;
import devti.project.fitness.entities.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	
	 private final RoleRepository roleRepository;
	 
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
	    
	    public Role createRole(@Valid Role role) {
		 	
	        return roleRepository.save(role);
	    }
	 
	    public Role updateRole(@Valid Role role) {	        
	       
	        return roleRepository.save(role);
	    }
	 
	    public String deleteRole(Long id) {
	    	roleRepository.deleteById(id);
	        return "deleted successfully";
	    }


}
