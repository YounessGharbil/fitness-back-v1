package devti.project.fitness.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import devti.project.fitness.entities.Authority;
import devti.project.fitness.entities.Role;
import devti.project.fitness.entities.requests.role.CreateRoleRequest;
import devti.project.fitness.entities.requests.role.GetRoleResponse;
import devti.project.fitness.entities.requests.role.UpdateRoleRequest;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/Role")
@RequiredArgsConstructor
public class RoleController {
	
    private final RoleService roleService;
	
	 @PostMapping
	    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequest createRoleRequest) {

	        Role role =roleService.createRole(createRoleRequest);
		 
	        return new ResponseEntity<>(role, HttpStatus.CREATED);
	        
	    }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Role> updateRole(@PathVariable Long id,@RequestBody UpdateRoleRequest roleRequest) {
		 
	        return new ResponseEntity<>(roleService.updateRole(roleRequest), HttpStatus.OK);
	        
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<GetRoleResponse> getRole(@PathVariable Long id) {
		 
	        Role role=roleService.getRole(id);
	        if(role==null){
	            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	        }
	        
	        List<String> authorityNames = role.getAuthorities().stream()
	                .map(Authority::getName)
	                .collect(Collectors.toList());
	        
	        GetRoleResponse response=GetRoleResponse.builder()
	        		.id(role.getId())
	        		.rolename(role.getRolename())
	        		.authorities(authorityNames)
	        		.build();
	        return new ResponseEntity<>(response,HttpStatus.OK);
	    }
	 
	 @GetMapping
	    public ResponseEntity<List<GetRoleResponse>> getRoles() {
		 
		 	List<Role> roles=roleService.getRoles();
		 	List<GetRoleResponse> response=new ArrayList<GetRoleResponse>();	
		 	
		 	for(Role role:roles) {
		 		response.add(
		 				GetRoleResponse.builder()
		 				.authorities(role.getAuthorities().stream().map(Authority::getName)
		 				.collect(Collectors.toList()))
		 				.rolename(role.getRolename())
		 				.id(role.getId())
		 				.build()
		 					);
		 	}
		 	
		 
	        return new ResponseEntity<>(response,HttpStatus.OK);
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
