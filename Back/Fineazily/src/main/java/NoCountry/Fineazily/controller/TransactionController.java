package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction/")
@RequiredArgsConstructor
@Validated
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDto dto,
                                               @RequestParam @NotNull(message = "user id cannot be null") Long userId,
                                               @RequestParam @NotNull(message = "box id cannot be null") Long boxId){
        transactionService.createTransaction(dto, userId, boxId);

        return ResponseEntity.ok("Transaction registered");
    }
}
