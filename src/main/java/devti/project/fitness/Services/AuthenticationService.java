package devti.project.fitness.Services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import devti.project.fitness.Repositories.UserAccountRepository;
import devti.project.fitness.entities.requests.authentication.AuthenticationRequest;
import devti.project.fitness.entities.requests.authentication.AuthenticationResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    
    private final UserAccountRepository userAccountRepository;
    
    private final JWTService jwtService;

    
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user=userAccountRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        return AuthenticationResponse.builder()
        		.token(jwtToken)
        		.userEmail(user.getEmail())
        		.userRole(user.getRole().toString())
        		.build();
    }


}
