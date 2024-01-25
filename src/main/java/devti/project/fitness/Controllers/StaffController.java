package devti.project.fitness.Controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devti.project.fitness.Services.ContactService;
import devti.project.fitness.Services.RoleService;
import devti.project.fitness.Services.StaffService;
import devti.project.fitness.Services.UserAccountService;
import devti.project.fitness.entities.Contact;
import devti.project.fitness.entities.Role;
import devti.project.fitness.entities.Staff;
import devti.project.fitness.entities.UserAccount;
import devti.project.fitness.entities.requests.staff.CreateStaffRequest;
import devti.project.fitness.entities.requests.staff.GetStaffResponse;
import devti.project.fitness.entities.requests.staff.UpdateStaffRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Staff")
@RequiredArgsConstructor
public class StaffController {
	
    private final StaffService staffService;
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    
    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody CreateStaffRequest createStaffRequest) {
    	
        SimpleDateFormat gymIdDateFormat = new SimpleDateFormat("ddMMyyyyHHmmssSSS");
        String gymId = gymIdDateFormat.format(new Date());

    	Contact contact=createStaffRequest.getContact();
    	
    	Role role=roleService.getRoleByRole_name(createStaffRequest.getRole_name());
    	
    	UserAccount account=userAccountService.createUserAccount(UserAccount.builder()
    			.password(passwordEncoder.encode("123456"))
    			.role(role)   
    			.email(contact.getEmail())
    			.contact(contact)
    			.build());

        Staff staff=Staff.builder()
        		.contact(contact)
        		.gymId(gymId)
        		.userAccount(account)
        		.build();
        
        staffService.createStaff(staff);
        
        return new ResponseEntity<>(staff, HttpStatus.CREATED);
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GetStaffResponse> getStaff(@PathVariable Long id) {
        Staff staff=staffService.getStaff(id);
     
        if(staff==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        
        	GetStaffResponse response=GetStaffResponse.builder()
        		.id(staff.getId())
        		.contact(staff.getContact())	
        		.gymId(staff.getGymId())
        		.build();
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<GetStaffResponse>> getStaffs() {
    		
    	List<Staff> staffs =staffService.getStaffs();
    	
    	List<GetStaffResponse> response =new ArrayList<GetStaffResponse>();
    	
    	
    	for(Staff staff:staffs) {
    		response.add(
    				GetStaffResponse.builder()
    				.id(staff.getId())
            		.contact(staff.getContact())	
            		.gymId(staff.getGymId())
    				.build()
    				);
    		
    	}
    	
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff( @PathVariable Long id) {
        Staff staff=staffService.getStaff(id);

        if(staff==null){
            return new ResponseEntity<>("staff does not exist",HttpStatus.NOT_FOUND);
        }
        staffService.deleteStaff(id);
        return new ResponseEntity<>("deleted successfully",HttpStatus.NO_CONTENT);
    }
    
    @PutMapping
    public ResponseEntity<Staff> updateStaff(@RequestBody UpdateStaffRequest updateStaffRequest){
    	
    	Staff staff=staffService.getStaff(updateStaffRequest.getId());
    	
    	UserAccount userAccount=staff.getUserAccount();
    	
    	Role role=roleService.getRoleByRole_name(updateStaffRequest.getRole_name());

    	
    	userAccount.setRole(role);
    	
    	staff.setUserAccount(userAccount);
    	
        return new ResponseEntity<>(staffService.updateStaff(staff),HttpStatus.OK);

    }

	

}
