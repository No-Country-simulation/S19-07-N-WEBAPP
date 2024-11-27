package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private TransactionService transactionService;


    @PostMapping("/{userId}/{boxId}")
    public ResponseEntity<?> createTransaction(@Valid TransactionDto dto,
                                               @NotNull(message = "user id cannot be null") @PathVariable Long userId,
                                               @NotNull(message = "box id cannot be null") @PathVariable Long boxId) {
        transactionService.create(dto, userId, boxId);
        return ResponseEntity.ok("transaction created");

    }

}
