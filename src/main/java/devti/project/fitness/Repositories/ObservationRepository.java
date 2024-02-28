package devti.project.fitness.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.Observation;

@Repository
public interface ObservationRepository extends JpaRepository<Observation, Long> {

}
