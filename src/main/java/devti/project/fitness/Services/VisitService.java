package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.VisitRepository;
import devti.project.fitness.entities.Visit;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisitService {
	
	private final VisitRepository visitRepository;
	 
	  public Visit getVisit(Long id) {
		  Visit visit=visitRepository.findById(id).isPresent()?visitRepository.findById(id).get():null;

	        return visit;
	    }
	    
	    public List<Visit> getVisits() {

	        return visitRepository.findAll();

	    }
	    
		 public Visit createVisit(@Valid Visit visite) {
			 	
		        return visitRepository.save(visite);
		    }
		 
		
}
