package devti.project.fitness.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import devti.project.fitness.entities.Package;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {

}
