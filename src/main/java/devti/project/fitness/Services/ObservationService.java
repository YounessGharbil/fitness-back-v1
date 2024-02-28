package devti.project.fitness.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import devti.project.fitness.Repositories.ObservationRepository;
import devti.project.fitness.entities.Observation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ObservationService {
	
	 private final ObservationRepository observationRepository;
	 
	  public Observation getObservation(Long id) {
		  
		  Observation observation=observationRepository.findById(id).isPresent()?observationRepository.findById(id).get():null;

	        return observation;
	    }
	    
	    public List<Observation> getObservations() {

	        return observationRepository.findAll();

	    }
	    
		 public Observation createObservation(@Valid Observation observation) {
			 	
		        return observationRepository.save(observation);
		    }
		 
		 public Observation updateObservation(@Valid Observation Observation) {	        
		       
		        return observationRepository.save(Observation);
		    }
		 
		 public String deleteObservation(Long id) {
			 
			 	observationRepository.deleteById(id);
			 	
		        return "deleted successfully";
		    }

}
