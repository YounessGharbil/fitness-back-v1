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

import devti.project.fitness.Services.ClientService;
import devti.project.fitness.Services.ObservationService;
import devti.project.fitness.entities.Client;
import devti.project.fitness.entities.Observation;
import devti.project.fitness.entities.requests.observation.CreateObservationRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Observation")
@RequiredArgsConstructor
public class ObservationController {
	
	 private final ObservationService observationService;
	 private final ClientService clientService;
		
	 @PostMapping
	 public ResponseEntity<Observation> createObservation(@RequestBody CreateObservationRequest createObservationRequest) {
		 
		 Client client=clientService.getClient(createObservationRequest.getClientId());
		 
		 Observation observation =Observation.builder()
				 .client(client)
				 .content(createObservationRequest.getContent())
				 .observationType(createObservationRequest.getObservationType())
				 .build();
		 
		 Observation createdObservation=observationService.createObservation(observation);
		 List<Observation> observations =client.getObservations();
		 observations.add(createdObservation);
		 clientService.updateClient(client);
		 
	        return new ResponseEntity<>(createdObservation, HttpStatus.CREATED);
	        
	    }
	 
	 @PutMapping
	 public ResponseEntity<Observation> updateObservation(@RequestBody Observation observation) {
	        
	        return new ResponseEntity<>(observationService.updateObservation(observation), HttpStatus.CREATED);
	        
	    }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<Observation> getObservation(@PathVariable Long id) {
		 
		 Observation observation=observationService.getObservation(id);
		 
	        if(observation==null){
	            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	        }
	        
	        Observation observation_response=Observation.builder()
	        		.build();
	        return new ResponseEntity<>(observation_response,HttpStatus.OK);
	    }
	 
	 @GetMapping
	 public ResponseEntity<List<Observation>> getObservations() {

	        return new ResponseEntity<>(observationService.getObservations(),HttpStatus.OK);
	    }
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String> deleteObservation( @PathVariable Long id) {
		 
	        Observation observation=observationService.getObservation(id);

	        if(observation==null){
	            return new ResponseEntity<>("observation does not exist",HttpStatus.NOT_FOUND);
	        }
	        
	        observationService.deleteObservation(id);
	        
	        return new ResponseEntity<>("deleted successfully",HttpStatus.NO_CONTENT);
	    }

}
