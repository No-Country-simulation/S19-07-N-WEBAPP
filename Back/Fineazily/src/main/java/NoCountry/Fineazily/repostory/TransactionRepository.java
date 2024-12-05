package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
