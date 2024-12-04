package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction/")
@RequiredArgsConstructor
@Validated
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDto dto,
                                               @RequestParam @NotNull(message = "user id cannot be null") Long userId,
                                               @RequestParam @NotNull(message = "box id cannot be null") Long boxId) {
        transactionService.createTransaction(dto, userId, boxId);

        return ResponseEntity.ok("Transaction registered");
    }

    @GetMapping
    public ResponseEntity<?> findAllTransactions() {
        return getResponse(transactionService.findAll());
    }
//-------------------------filtered search-----------------
    @GetMapping("{userId}")
    public ResponseEntity<?> findTransactionsByUser(@PathVariable Long userId) {
        return getResponse(transactionService.findTransactionsByUser(userId));
    }

    @GetMapping("{boxId}")
    public ResponseEntity<?> findTransactionsByBox(@PathVariable Long boxId) {
        return getResponse(transactionService.findTransactionsByBox(boxId));
    }

    @GetMapping("{branchId}")
    public ResponseEntity<?> findTransactionsByBranch(@PathVariable Long branchId) {
        return getResponse(transactionService.findTransactionsByBranch(branchId));
    }

    @GetMapping("{transactionId}")
    public ResponseEntity<?> findTransactionById(@PathVariable Long transactionId) {
        return ResponseEntity.ok(transactionService.findById(transactionId));
    }

    @PatchMapping
    public ResponseEntity<?> updateTransaction(@RequestBody TransactionDto dto){
        transactionService.updateTransaction(dto);
        return ResponseEntity.ok("transaction updated");
    }

    @DeleteMapping("{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId){
        transactionService.delete(transactionId);
        return ResponseEntity.ok("Transaction deleted successfully");
    }

    //---------------------------------aids methods-------------------------------
    private ResponseEntity<?> getResponse(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            return new ResponseEntity<>("There isn't transactions to show", HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(transactions);
    }
}
