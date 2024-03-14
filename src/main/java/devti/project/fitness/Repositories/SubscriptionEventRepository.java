package devti.project.fitness.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.SubscriptionEvent;

@Repository
public interface SubscriptionEventRepository extends JpaRepository<SubscriptionEvent,Long> {

}
