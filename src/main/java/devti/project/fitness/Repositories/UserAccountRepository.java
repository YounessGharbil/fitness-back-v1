package devti.project.fitness.Repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import devti.project.fitness.entities.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	
    Optional<UserAccount>  findByEmail(String email);

}
