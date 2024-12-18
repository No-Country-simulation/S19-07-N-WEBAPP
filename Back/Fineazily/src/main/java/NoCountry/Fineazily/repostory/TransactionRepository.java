package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.enums.MethodType;
import NoCountry.Fineazily.model.enums.MoveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

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
    @Query("select t FROM Transaction t where t.session.employee.id = :employeeId")
    List<Transaction> findTransactionsByEmployeeId(Long employeeId);

    @Query("select t from Transaction t where t.session.cashRegister.id =  :cashRegisterId")
    List<Transaction> findTransactionsByCashRegisterId(Long cashRegisterId);

    List<Transaction> findTransactionsByMethodType(MethodType methodType);

    List<Transaction> findTransactionsByMoveType(MoveType moveType);

    @Query("SELECT t FROM Transaction t WHERE t.session.cashRegister.area.branch.id = :id")
    List<Transaction> findTransactionsByBranchId(@Param("id") Long branchId);


}
