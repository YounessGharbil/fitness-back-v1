package devti.project.fitness.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

}
