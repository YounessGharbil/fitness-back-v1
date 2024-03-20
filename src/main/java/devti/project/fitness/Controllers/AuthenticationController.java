package devti.project.fitness.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devti.project.fitness.Services.AuthenticationService;
import devti.project.fitness.entities.requests.authentication.AuthenticationRequest;
import devti.project.fitness.entities.requests.authentication.AuthenticationResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    
    @PostMapping
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception{
    	
    	AuthenticationResponse response=null;
    	
    	try {
    		
    		response=authenticationService.authenticate(request);
    		
		} catch (Exception e) {
			
			throw new Exception("Authentication failed");
			
		}
    	
    	
        return new ResponseEntity<>(response, HttpStatus.OK);
        
    }


}
