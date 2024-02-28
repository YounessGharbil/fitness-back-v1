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

import devti.project.fitness.Services.RoleService;
import devti.project.fitness.entities.Role;
import devti.project.fitness.entities.requests.role.CreateRoleRequest;
import devti.project.fitness.entities.requests.role.GetRoleResponse;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/Role")
@RequiredArgsConstructor
public class RoleController {
	
    private final RoleService roleService;
	
	 @PostMapping
	    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequest createRoleRequest) {

	        Role role =Role.builder()
	        		.rolename(createRoleRequest.getRolename())
	        		.build();
		 
	        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
	        
	    }
	 
	 @PutMapping
	    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
	        
	        return new ResponseEntity<>(roleService.updateRole(role), HttpStatus.CREATED);
	        
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<GetRoleResponse> getRole(@PathVariable Long id) {
		 
	        Role role=roleService.getRole(id);
	        if(role==null){
	            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	        }
	        
	        GetRoleResponse response=GetRoleResponse.builder()
	        		.id(role.getId())
	        		.rolename(role.getRolename())
	        		.build();
	        return new ResponseEntity<>(response,HttpStatus.OK);
	    }
	 
	 @GetMapping
	    public ResponseEntity<List<Role>> getRoles() {

	        return new ResponseEntity<>(roleService.getRoles(),HttpStatus.OK);
	    }
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteRole( @PathVariable Long id) {
	        Role role=roleService.getRole(id);

	        if(role==null){
	            return new ResponseEntity<>("role does not exist",HttpStatus.NOT_FOUND);
	        }
	        roleService.deleteRole(id);
	        return new ResponseEntity<>("deleted successfully",HttpStatus.NO_CONTENT);
	    }
	 

}
