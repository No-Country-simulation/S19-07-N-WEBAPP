package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.enums.MethodType;
import NoCountry.Fineazily.model.enums.MoveType;
import NoCountry.Fineazily.repostory.TransactionRepository;
import NoCountry.Fineazily.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions/")
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

    //-------------------------filtered search-----------------------------------------------------------
    @GetMapping
    public ResponseEntity<?> findAllTransactions(@RequestParam(required = false) MethodType method) {
        if (method != null) {
            return getResponse(transactionService.findTransactionsByMethodType(method));
        }
        return getResponse(transactionService.findAll());
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<?> findTransactionsByUser(@PathVariable Long userId) {
        return getResponse(transactionService.findTransactionsByUserId(userId));
    }

    @GetMapping("box/{boxId}")
    public ResponseEntity<?> findTransactionsByBox(@PathVariable Long boxId) {
        return getResponse(transactionService.findTransactionsByBoxId(boxId));
    }

    @GetMapping("branch/{branchId}")
    public ResponseEntity<?> findTransactionsByBranch(@PathVariable Long branchId) {
        return getResponse(transactionService.findTransactionsByBranchId(branchId));
    }

    @GetMapping("{transactionId}")
    public ResponseEntity<?> findTransactionById(@PathVariable Long transactionId) {
        return ResponseEntity.ok(transactionService.findById(transactionId));
    }

    //these two methods below can be replaced with one MoveType method parametrized
    @GetMapping("moveType")
    public ResponseEntity<?> getIncomeTransactions(@RequestParam MoveType moveType) {
        return getResponse(transactionService.findTransactionsByMoveType(moveType));
    }

    @GetMapping("amount/moveType")
    public ResponseEntity<?> getTotalAmountByMoveType(@RequestParam MoveType moveType,
                                                      @RequestParam(required = false) LocalDate since,
                                                      @RequestParam(required = false) LocalDate until) {
        return ResponseEntity.ok(transactionService.getAmountsSinceAndUntilDateAndByMoveType(since, until, moveType));
    }

    @GetMapping("amount/methodType")
    public ResponseEntity<?> getTotalAmountByMethodType(@RequestParam MethodType methodType,
                                                        @RequestParam(required = false) LocalDate since,
                                                        @RequestParam(required = false) LocalDate until) {
        return ResponseEntity.ok(transactionService.getAmountsSinceAndUntilByMethodType(methodType, since, until));
    }

    @GetMapping("earnings")
    public ResponseEntity<?> getEarnings(@RequestParam(required = false) LocalDate since,
                                         @RequestParam(required = false) LocalDate until) {
        return ResponseEntity.ok(transactionService.getTotalEarnings(since, until));
    }

    //-------------------------------------------------------------------------------------------------
    @PatchMapping
    public ResponseEntity<?> updateTransaction(@RequestBody TransactionDto dto) {
        transactionService.updateTransaction(dto);
        return ResponseEntity.ok("transaction updated");
    }

    @DeleteMapping("{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
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
