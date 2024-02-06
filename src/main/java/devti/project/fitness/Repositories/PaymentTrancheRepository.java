package devti.project.fitness.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.PaymentTranche;

@Repository
public interface PaymentTrancheRepository extends JpaRepository<PaymentTranche, Long> {

}
