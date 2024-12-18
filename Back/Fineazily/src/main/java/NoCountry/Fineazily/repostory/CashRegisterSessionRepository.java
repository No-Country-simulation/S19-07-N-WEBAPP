package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.CashRegisterSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashRegisterSessionRepository extends JpaRepository<Long, CashRegisterSession> {
}
