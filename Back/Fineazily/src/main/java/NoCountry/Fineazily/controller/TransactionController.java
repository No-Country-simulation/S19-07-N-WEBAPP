package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.enums.MethodType;
import NoCountry.Fineazily.model.enums.MoveType;
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
    public ResponseEntity<?> findAllTransactions(@RequestParam(required = false)MethodType method) {
        if(method != null){
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

    @GetMapping("income")
    public ResponseEntity<?> getIncomeTransactions(){
        return getResponse(transactionService.findTransactionsByMoveType(MoveType.INCOME));
    }

    @GetMapping("egress")
    public ResponseEntity<?> getEgressTransactions(){
        return getResponse(transactionService.findTransactionsByMoveType(MoveType.EGRESS));
    }

//-------------------------------------------------------------------------------------------------
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
