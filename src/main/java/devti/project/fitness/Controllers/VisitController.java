package devti.project.fitness.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devti.project.fitness.Services.VisitService;
import devti.project.fitness.entities.Visit;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Visit")
@RequiredArgsConstructor
public class VisitController {
	
	
    private final VisitService visitService;
	
	 @PostMapping
	    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {

		 	Visit visite =Visit.builder()
	        		.build();
		 
	        return new ResponseEntity<>(visitService.createVisit(visite), HttpStatus.CREATED);
	        
	    }
	 
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<Visit> getVisit(@PathVariable Long id) {
		 
	        Visit visit=visitService.getVisit(id);
	        if(visit==null){
	            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	        }
	        
	        Visit visite=Visit.builder()	        		
	        		.build();
	        return new ResponseEntity<>(visite,HttpStatus.OK);
	    }
	 
	 @GetMapping
	    public ResponseEntity<List<Visit>> getVisits() {

	        return new ResponseEntity<>(visitService.getVisits(),HttpStatus.OK);
	    }
	 

}
