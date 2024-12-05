package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    BigDecimal getTotalIncome();

    BigDecimal getTotal
}
