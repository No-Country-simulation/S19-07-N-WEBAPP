package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.BoxDto;
import NoCountry.Fineazily.model.entity.CashRegister;
import NoCountry.Fineazily.model.mapper.BoxMapper;
import NoCountry.Fineazily.service.CashRegisterService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/box")
@RequiredArgsConstructor
@Validated
public class BoxController {
    private final CashRegisterService service;
    private final BoxMapper mapper;
    private final String boxIdNotNullMessage = "The box id cannot be null";

    @PostMapping("/{branchId}")
    public ResponseEntity<?> createBox(@PathVariable @NotNull(message = "The branch id cannot be null") Long branchId,
                                       @Valid @RequestBody BoxDto dto) {
        service.create(mapper.toEntity(dto), branchId);
        return ResponseEntity.ok("box created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBoxById(@PathVariable @NotNull(message = boxIdNotNullMessage) Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBoxes() {
        List<CashRegister> cashRegisters = service.findAll();
        if (cashRegisters != null && !cashRegisters.isEmpty()) {
            return ResponseEntity.ok(cashRegisters);
        } else {
            return new ResponseEntity<>("No boxes to show", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public ResponseEntity<?> findBoxPaged(@PathVariable int pageNumber, @PathVariable int pageSize) {
        //needs to be implemented
        return ResponseEntity.ok("");
    }

    @PatchMapping("/")
    public ResponseEntity<?> updateBox(@RequestBody BoxDto dto) {
        service.update(dto);
        return ResponseEntity.ok("box updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBox(@PathVariable @NotNull(message = boxIdNotNullMessage) Long id) {
        service.delete(id);
        return ResponseEntity.ok("box deleted");
    }

}
