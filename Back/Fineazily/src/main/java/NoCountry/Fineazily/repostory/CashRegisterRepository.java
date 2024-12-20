package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CashRegisterRepository extends JpaRepository<CashRegister, Long> {
    List<CashRegister> findAllByActiveIsTrue();

    List<CashRegister> findAllByActiveIsTrueAndAreaId(Long areaId);

    @Query("SELECT c FROM CashRegister  c where c.area.branch.id = : branchId and c.isActive = true")
    List<CashRegister> findAllByBranchId(Long branchId);
}
