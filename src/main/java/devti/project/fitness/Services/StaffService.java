package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.StaffRepository;
import devti.project.fitness.entities.Staff;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffService {
	
	 private final StaffRepository staffRepository;
	 
	  public Staff getStaff(Long id) {
		  Staff staff=staffRepository.findById(id).isPresent()?staffRepository.findById(id).get():null;

	        return staff;
	    }
	    
	    public List<Staff> getStaffs() {

	        return staffRepository.findAll();

	    }
	    
		 public Staff createStaff(@Valid Staff staff) {
			 	
		        return staffRepository.save(staff);
		    }
		 
		 public Staff updateStaff(@Valid Staff staff) {	        
		       
		        return staffRepository.save(staff);
		    }
		 
		 public String deleteStaff(Long id) {
			 	staffRepository.deleteById(id);
		        return "deleted successfully";
		    }


}
