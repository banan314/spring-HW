package hw.spring.model.repositories;

import hw.spring.model.Finance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinanceRepository extends CrudRepository<Finance, Integer> {
    Optional<Finance> findFirstByName(String name);
}
