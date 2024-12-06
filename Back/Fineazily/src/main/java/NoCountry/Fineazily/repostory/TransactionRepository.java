package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.Box;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.entity.User;
import NoCountry.Fineazily.model.enums.MethodType;
import NoCountry.Fineazily.model.enums.MoveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Method;
import java.util.List;

import java.math.BigDecimal;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select sum(t.amount) from Transaction t where t.moveType = :moveType")
    float sumAmountsByMoveType(MoveType moveType);

    //float getTotalIncomeRangeOfDates();

    List<Transaction> findTransactionsByUserId(Long userId);

    List<Transaction> findTransactionsByBoxId(Long boxId);

    List<Transaction> findTransactionsByMethodType(MethodType methodType);

    List<Transaction> findTransactionsByMoveType(MoveType moveType);

    @Query("SELECT t FROM Transaction t WHERE t.box.branch.id = :id")
    List<Transaction> findTransactionsByBranchId(@Param("id") Long branchId);


}
