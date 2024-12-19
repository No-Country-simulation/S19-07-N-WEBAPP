package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.CashRegisterSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashRegisterSessionRepository extends JpaRepository<CashRegisterSession, Long> {
    public List<CashRegisterSession> findAllByEndDateTimeIsNull();
}
