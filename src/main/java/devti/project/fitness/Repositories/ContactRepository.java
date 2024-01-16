package devti.project.fitness.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
