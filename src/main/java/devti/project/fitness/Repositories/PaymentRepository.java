package devti.project.fitness.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
