package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.Box;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionsByUser(User user);

    List<Transaction> findTransactionsByBox(Box box);

    @Query("SELECT t FROM Transaction t WHERE t.box.branch.id = :id")
    List<Transaction> findTransactionsByBranchId(@Param("id") Long branchId);

}
