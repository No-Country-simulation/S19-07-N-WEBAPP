package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.BoxDto;
import NoCountry.Fineazily.model.entity.Box;
import NoCountry.Fineazily.model.mapper.BoxMapper;
import NoCountry.Fineazily.service.BoxService;
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
    private final BoxService service;
    private final BoxMapper mapper;
    private final String boxIdNotNullMessage = "The box id cannot be null";

    @PostMapping("/")
    public ResponseEntity<?> createBox(@Valid @RequestBody BoxDto dto,
                                       @NotNull(message = "The branch id cannot be null") @RequestParam Long branchId ) {
        service.create(mapper.toEntity(dto), branchId);
        return ResponseEntity.ok("box created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBoxById(@PathVariable @NotNull(message = boxIdNotNullMessage) Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllBoxes() {
        List<Box> boxes = service.findAll();
        if (boxes != null && !boxes.isEmpty()) {
            return ResponseEntity.ok(boxes);
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
