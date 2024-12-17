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
import java.time.LocalDate;
import java.util.List;

import java.math.BigDecimal;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    //------------------------------------------------------------------------Balance and filtered balance
    @Query("select sum(t.amount) from Transaction t where t.moveType = :moveType")
    Float sumAmountsByMoveType(MoveType moveType);

    @Query("select sum(t.amount) from Transaction t where t.moveType = :moveType and t.creationDate BETWEEN :sinceDate AND :untilDate")
    Float sumAmountsSinceAndUntilCreationDateAndMoveType(MoveType moveType, LocalDate sinceDate, LocalDate untilDate);

    @Query("select sum(t.amount) from Transaction t where t.methodType = :methodType")
    Float sumAmountsByMethodType(MethodType methodType);

    @Query("select sum(t.amount) from Transaction t where t.methodType = :methodType and t.creationDate BETWEEN :sinceDate AND :untilDate")
    Float sumAmountsSinceAndUntilCreationDateAndMethodType(MethodType methodType, LocalDate sinceDate, LocalDate untilDate);

    //-----------------------------------------------------------------------------------filtered search
    List<Transaction> findTransactionsByUserId(Long userId);

    List<Transaction> findTransactionsByBoxId(Long boxId);

    List<Transaction> findTransactionsByMethodType(MethodType methodType);

    List<Transaction> findTransactionsByMoveType(MoveType moveType);

    @Query("SELECT t FROM Transaction t WHERE t.box.branch.id = :id")
    List<Transaction> findTransactionsByBranchId(@Param("id") Long branchId);


}
