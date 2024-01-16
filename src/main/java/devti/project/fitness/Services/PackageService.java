package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.PackageRepository;
import devti.project.fitness.entities.Package;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PackageService {
	
	 private final PackageRepository packageRepository;

	    public Package getPackage(Long id) {
	    	Package pack=packageRepository.findById(id).isPresent()?packageRepository.findById(id).get():null;

	        return pack;
	    }
	    
	    public List<Package> getPackages() {

	        return packageRepository.findAll();

	    }
	    
		 public Package createPackage(@Valid Package pack) {
			 	
		        return packageRepository.save(pack);
		    }
		 
		 public Package updatePackage(@Valid Package pack) {	        
		       
		        return packageRepository.save(pack);
		    }
		 
		 public String deletePackage(Long id) {
			 packageRepository.deleteById(id);
		        return "deleted successfully";
		    }

}
