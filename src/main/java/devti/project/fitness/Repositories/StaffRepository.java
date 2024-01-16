package devti.project.fitness.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.Staff;



@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}
