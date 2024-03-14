package devti.project.fitness.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {
	Optional<Authority> findByName(String name);
}
