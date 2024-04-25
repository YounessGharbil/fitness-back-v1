package devti.project.fitness.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping
    public ResponseEntity<String> getTest() {
	 
        return new ResponseEntity<>("this is the test controller",HttpStatus.OK);
    }

}
