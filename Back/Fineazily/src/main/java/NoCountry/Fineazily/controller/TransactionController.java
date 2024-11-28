package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
@Validated
public class TransactionController {
    private final TransactionService transactionService;
    private final String notNullTransactionIdMessage = "the id of the transaction cannot be null";


    @PostMapping("/")
    public ResponseEntity<?> createTransaction(@Valid TransactionDto dto,
                                               @RequestParam @NotNull(message = "box id cannot be null") Long boxId,
                                               @RequestParam @NotNull(message = "user id cannot be null") Long userId,
                                               @RequestParam @NotNull(message = "method type cannot be null") Long methodTypeId,
                                               @RequestParam @NotNull(message = "move type id cannot be null") Long moveTypeId) {
        transactionService.create(dto, boxId, userId, methodTypeId, moveTypeId);
        return ResponseEntity.ok("transaction created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable @NotNull(message = notNullTransactionIdMessage) Long id) {
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        if (transactions != null && !transactions.isEmpty()) {
            return ResponseEntity.ok(transactions);
        } else
            return new ResponseEntity<>("there isn't transactions to show", HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/")
    public ResponseEntity<?> updateTransaction(@Valid @RequestBody TransactionDto dto) {
        return ResponseEntity.ok(transactionService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable @NotNull Long id) {
        transactionService.delete(id);
        return ResponseEntity.ok("transaction deleted");
    }


    //needs to be implemented
    @GetMapping("/paged")
    public ResponseEntity<?> getTransactionsPaged() {
        return ResponseEntity.ok("");
    }


    //search transactions by filter

    @GetMapping("/transaction-by-branch/{branchId}")

    public ResponseEntity<?> getTransactionsByBranch(@PathVariable @NotNull(message = "") Long branchId) {
        return ResponseEntity.ok("");
    }

    @GetMapping("/transaction-by-box/{boxId}")
    public ResponseEntity<?> getTransactionsByBox(@PathVariable @NotNull(message = "") Long boxId) {
        return ResponseEntity.ok("");
    }

    @GetMapping("/transaction-by-user/{userId}")
    public ResponseEntity<?> getTransactionsByUser(@PathVariable @NotNull(message = "") Long userId) {
        return ResponseEntity.ok("");
    }

}
