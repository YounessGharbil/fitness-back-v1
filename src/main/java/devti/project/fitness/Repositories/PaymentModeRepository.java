package devti.project.fitness.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.PaymentMode;

@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode, Long> {

}
