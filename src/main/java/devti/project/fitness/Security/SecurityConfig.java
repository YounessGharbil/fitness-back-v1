package devti.project.fitness.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
    private final  AuthenticationProvider authenticationProvider;

	
    private final  JWTAuthenticationFilter jwtAuthenticationFilter;

	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                		authorize->authorize
                		.requestMatchers("/Subscription").hasAnyAuthority("SUBSCRIPTIONS_MANAGEMENT")
                		.requestMatchers("/Payment").hasAuthority("PAYMENTS_MANAGEMENT")
                		.requestMatchers("/Observation").hasAuthority("OBSERVATIONS_MANAGEMENT")
                		.requestMatchers("/Client").hasAuthority("CLIENTS_MANAGEMENT")
                		.requestMatchers("/User").hasAuthority("USERS_MANAGEMENT")
                		.requestMatchers("/Package").hasAuthority("PACKAGES_MANAGEMENT")
                		.requestMatchers("/Contact").hasAuthority("CONTACTS_MANAGEMENT")
                		.requestMatchers("/Role").hasAuthority("ROLES_MANAGEMENT")
                		.requestMatchers("/Staff").hasAuthority("STAFFS_MANAGEMENT")


                		.anyRequest().permitAll()
                		)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter,  UsernamePasswordAuthenticationFilter.class)
                ;
                
                
        return http.build();
    }

}














