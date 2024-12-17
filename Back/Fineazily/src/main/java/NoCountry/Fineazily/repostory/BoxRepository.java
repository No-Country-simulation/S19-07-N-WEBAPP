package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxRepository extends JpaRepository<CashRegister,Long> {
}
