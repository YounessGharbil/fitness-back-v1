package devti.project.fitness.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.Client;
import devti.project.fitness.entities.Contact;
import devti.project.fitness.entities.Subscription;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	void deleteBySubscription(Subscription subscription);
	
	Optional<Client> findByGymid(String gymid);
	
	Optional<Client> findByContact(Contact contact);
	

}
