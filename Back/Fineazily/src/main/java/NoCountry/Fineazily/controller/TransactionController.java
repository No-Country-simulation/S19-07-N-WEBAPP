package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.enums.MethodType;
import NoCountry.Fineazily.model.enums.MoveType;
import NoCountry.Fineazily.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions/")
@RequiredArgsConstructor
@Validated
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(
            summary = "Create a new transaction",
            description = "Allows you to register a new transaction, income and egress use the same endpoint for register",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "transaction registered",
                            content = @Content()),
                    @ApiResponse(
                            responseCode = "400",
                            description = "the given methodType is invalid for that moveType",
                            content = @Content)
            },
            parameters = {
                    @Parameter(name = "dto", description = "a dto carrying transaction info", required = true),
                    @Parameter(name = "userId", description = "the user id who registered the transaction", required = true),
                    @Parameter(name = "boxId", description = "the box id where the transaction was made")

            })
    @PostMapping
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDto dto,
                                               @RequestParam @NotNull(message = "user id cannot be null") Long userId,
                                               @RequestParam @NotNull(message = "box id cannot be null") Long boxId) {
        transactionService.createTransaction(dto, userId, boxId);

        return ResponseEntity.ok("Transaction registered");
    }

    //-------------------------filtered search-----------------------------------------------------------
    @Operation(
            summary = "get all transactions",
            description = "shows you all transactions registered, allowing to filter by method if you send the parameter",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list with all transactions",
                            content = @Content(schema = @Schema(implementation = Transaction.class))),
                    @ApiResponse(
                            responseCode = "204",
                            description = "There isn't transactions to show",
                            content = @Content
                    )
            },
            parameters = {
                    @Parameter(name = "method", description = "the method type if you want to get transactions filtered by method", required = false)
            }
    )
    @GetMapping
    public ResponseEntity<?> findAllTransactions(@RequestParam(required = false) MethodType method) {
        if (method != null) {
            return getResponse(transactionService.findTransactionsByMethodType(method));
        }
        return getResponse(transactionService.findAll());
    }

    @Operation(
            summary = "get transactions by userId",
            description = "shows you all transactions registered filtering by userId who registered them",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a transaction",
                            content = @Content(schema = @Schema(implementation = Transaction.class))),
                    @ApiResponse(
                            responseCode = "204",
                            description = "There isn't transactions to show",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "There isn't an user with the given id",
                            content = @Content
                    )
            },
            parameters = {
                    @Parameter(name = "userId", description = "user id the filter transaction with it", required = true)
            }
    )
    @GetMapping("user/{userId}")
    public ResponseEntity<?> findTransactionsByUser(@PathVariable Long userId) {
        return getResponse(transactionService.findTransactionsByUserId(userId));
    }

    @Operation(
            summary = "get transactions by boxId",
            description = "shows you all transactions registered filtering by user id where the transaction was registered",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list with transactions",
                            content = @Content(schema = @Schema(implementation = Transaction.class))),
                    @ApiResponse(
                            responseCode = "204",
                            description = "There isn't transactions to show",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "There isn't a box with the given id",
                            content = @Content
                    )
            },
            parameters = {
                    @Parameter(name = "boxId", description = "boxId the filter transaction with it", required = true)
            })
    @GetMapping("box/{boxId}")
    public ResponseEntity<?> findTransactionsByBox(@PathVariable Long boxId) {
        return getResponse(transactionService.findTransactionsByBoxId(boxId));
    }

    @Operation(
            summary = "get transactions by branch id",
            description = "shows you all transactions registered filtering by branch id where the transaction was registered",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list with transactions",
                            content = @Content(schema = @Schema(implementation = Transaction.class))),
                    @ApiResponse(
                            responseCode = "204",
                            description = "There isn't transactions to show",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "There isn't a branch with the given id",
                            content = @Content
                    )
            },
            parameters = {
                    @Parameter(name = "branchId", description = "branchId the filter transaction with it", required = true)
            })

    @GetMapping("branch/{branchId}")
    public ResponseEntity<?> findTransactionsByBranch(@PathVariable Long branchId) {
        return getResponse(transactionService.findTransactionsByBranchId(branchId));
    }

    @Operation(
            summary = "get transaction by id",
            description = "Allows to find an specific transaction for its id ",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "the transaction for the given id",
                            content = @Content(schema = @Schema(implementation = Transaction.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "there isn't transactions for the given id",
                            content = @Content
                    )},
            parameters = {@Parameter(name = "transactionId", description = "id to search the transaction", required = true)}
    )
    @GetMapping("{transactionId}")
    public ResponseEntity<?> findTransactionById(@PathVariable Long transactionId) {
        return ResponseEntity.ok(transactionService.findById(transactionId));
    }

    @Operation(
            summary = "get transaction by move type",
            description = "find all transactions for the move type (INCOME/EGRESS)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "transactions for the given move type",
                            content = @Content(schema = @Schema(implementation = Transaction.class))),
                    @ApiResponse(
                            responseCode = "204",
                            description = "no transactions to show for that move type",
                            content = @Content)
            },
            parameters = {@Parameter(name = "moveType", description = "move type for filter transactions", required = true)}
    )
    @GetMapping("moveType")
    public ResponseEntity<?> getTransactionsByMoveType(@RequestParam MoveType moveType) {
        return getResponse(transactionService.findTransactionsByMoveType(moveType));
    }

    @Operation(
            summary = "get total amount by moveType",
            description = "Allows to get total cash amount for a specific moveType, also can get since and until dates to filter the results." +
                    "sending a null since and not null until doesn't filter",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "total amount for the moveType and possible dates given",
                            content = @Content
                    )
            },
            parameters = {
                    @Parameter(name = "moveType", description = "move type to get total amount", required = true),
                    @Parameter(name = "since", description = "date from to start adding amounts"),
                    @Parameter(name = "until", description = "date until to finish adding amounts")}
    )
    @GetMapping("amount/moveType")
    public ResponseEntity<?> getTotalAmountByMoveType(@RequestParam MoveType moveType,
                                                      @RequestParam(required = false) LocalDate since,
                                                      @RequestParam(required = false) LocalDate until) {
        return ResponseEntity.ok(transactionService.getAmountsSinceAndUntilDateAndByMoveType(since, until, moveType));
    }

    @Operation(
            summary = "get total amount by methodType",
            description = "Allows to get total cash amount for a specific methodType, also can get since and until dates to filter the results." +
                    "sending a null since and not null until doesn't filter",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "total amount for the methodType and possible dates given",
                            content = @Content
                    )
            },
            parameters = {
                    @Parameter(name = "methodType", description = "method type to get total amount", required = true),
                    @Parameter(name = "since", description = "date from to start adding amounts"),
                    @Parameter(name = "until", description = "date until to finish adding amounts")}
    )
    @GetMapping("amount/methodType")
    public ResponseEntity<?> getTotalAmountByMethodType(@RequestParam MethodType methodType,
                                                        @RequestParam(required = false) LocalDate since,
                                                        @RequestParam(required = false) LocalDate until) {
        return ResponseEntity.ok(transactionService.getAmountsSinceAndUntilByMethodType(methodType, since, until));
    }

    @Operation(
            summary = "get total earnings",
            description = "get total earnings with the possibility of send since and until date to filter",
            responses = @ApiResponse(responseCode = "200", description = "the total earning", content = @Content),
            parameters = {
                    @Parameter(name = "since", description = "date from to start calculating earnings"),
                    @Parameter(name = "until", description = "date until to finish calculating earnings")
            }
    )
    @GetMapping("earnings")
    public ResponseEntity<?> getEarnings(@RequestParam(required = false) LocalDate since,
                                         @RequestParam(required = false) LocalDate until) {
        return ResponseEntity.ok(transactionService.getTotalEarnings(since, until));
    }

    //-------------------------------------------------------------------------------------------------
    @Operation(
            summary = "update transaction",
            description = "update an transaction",
            responses = {
                    @ApiResponse(responseCode = "200", description = "transaction updated", content = @Content),
                    @ApiResponse(responseCode = "404", description = "no transaction found for the given id", content = @Content)},
            parameters = @Parameter(name = "dto", description = "a dto carrying transaction info ", required = true)
    )
    @PatchMapping
    public ResponseEntity<?> updateTransaction(@RequestBody TransactionDto dto) {
        transactionService.updateTransaction(dto);
        return ResponseEntity.ok("transaction updated");
    }

    @Operation(
            summary = "delete transaction",
            description = "delete the transaction with the given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "transaction deleted"),
                    @ApiResponse(responseCode = "404", description = "no transaction found for the given id")},
            parameters = @Parameter(name = "transactionId", description = "transaction id", required = true)

    )
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
