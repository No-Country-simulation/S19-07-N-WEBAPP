package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.request.CashRegisterRequest;
import NoCountry.Fineazily.model.dto.response.CashRegisterResponse;
import NoCountry.Fineazily.model.entity.CashRegister;
import NoCountry.Fineazily.service.CashRegisterService;
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

import java.util.List;

@RestController
@RequestMapping("/api/cash-register")
@RequiredArgsConstructor
@Validated
public class CashRegisterController {
    private final CashRegisterService service;
    private final String cashRegisterIdNotNullMessage = "The box id cannot be null";

    @Operation(
            summary = "create cash register",
            description = "create a new cash register",
            responses = {
                    @ApiResponse(responseCode = "200", description = "cash register created"),
                    @ApiResponse(responseCode = "404", description = "there isn't any area with the given id")
            },
            parameters = {
                    @Parameter(name = "areaId", required = true),
                    @Parameter(name = "dto", description = "the dto that carry all info for the new cash register", required = true)
            }
    )

    @PostMapping("/{areaId}")
    public ResponseEntity<?> createCashRegister(@PathVariable @NotNull(message = "The area id cannot be null") Long areaId,
                                                @Valid @RequestBody CashRegisterRequest dto) {
        service.create(dto, areaId);
        return ResponseEntity.ok("cash register created");
    }

    @Operation(
            summary = "find cash register",
            description = "find the cash register for the given id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a cash register response",
                            content = @Content(schema = @Schema(implementation = CashRegisterResponse.class))),
                    @ApiResponse(responseCode = "404", description = "there isn't any cash register with the given id")
            },
            parameters = @Parameter(name = "id", description = "cash register id", required = true)
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> findCashRegisterById(@PathVariable @NotNull(message = cashRegisterIdNotNullMessage) Long id) {
        return new ResponseEntity<>(service.findCashRegisterById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "get all cash register",
            description = "get all cash register without discriminate",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list with cash register responses",
                            content = @Content(schema = @Schema(implementation = CashRegisterResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "202",
                            description = "no results to show"
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllCashRegister() {
        List<CashRegisterResponse> cashRegisters = service.findAll();
        if (cashRegisters != null && !cashRegisters.isEmpty()) {
            return ResponseEntity.ok(cashRegisters);
        } else {
            return new ResponseEntity<>("No cash register to show", HttpStatus.NO_CONTENT);
        }
    }

    @Operation(
            summary = "get all active cash register",
            description = "find all active cash register",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list with all active cash register",
                            content = @Content(schema = @Schema(implementation = CashRegisterResponse.class))
                    ),

            }
    )
    @GetMapping("/all-active")
    public ResponseEntity<?> findAllActiveCashRegister() {
        return new ResponseEntity<>(service.findAllActive(), HttpStatus.OK);
    }

    @Operation(
            summary = "find cash register by area",
            description = "find all active cash register for the given area id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of cash register response",
                            content = @Content(schema = @Schema(implementation = CashRegisterResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "no area found for the given id"

                    )},
            parameters = @Parameter(name = "areaId", required = true)
    )
    @GetMapping("/area/{areaId}")
    public ResponseEntity<?> findAllCashRegisterByArea(@NotNull @PathVariable Long areaId) {
        return new ResponseEntity<>(service.findAllByArea(areaId), HttpStatus.OK);
    }

    @Operation(
            summary = "find cash register by branch",
            description = "find all active cash register for the given branch id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of cash register response",
                            content = @Content(schema = @Schema(implementation = CashRegisterResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "no branch found for the given id"

                    )},
            parameters = @Parameter(name = "branchId", required = true)
    )
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<?> findAllCashRegisterByBranch(@NotNull @PathVariable Long branchId) {
        return new ResponseEntity<>(service.findAllByBranchId(branchId), HttpStatus.OK);
    }

    @Operation(
            summary = "update cash register",
            description = "update cash register with for the given id and dto",
            responses = {
                    @ApiResponse(responseCode = "200", description = "cash register updated"),
                    @ApiResponse(responseCode = "404", description = "no cash register found with the given id")}
            ,
            parameters = {
                    @Parameter(name = "cashRegisterId", required = true),
                    @Parameter(name = "dto", required = true)}
    )
    @PatchMapping("/{cashRegisterId}")
    public ResponseEntity<?> updateCashRegister(@RequestBody CashRegisterRequest dto, @NotNull @PathVariable Long cashRegisterId) {
        service.update(dto, cashRegisterId);
        return ResponseEntity.ok("cash register updated");
    }

    @Operation(
            summary = "delete cash register",
            description = "deactivate a cash register(logic deleted/ soft deleted) for the given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "cash register deactivated"),
                    @ApiResponse(responseCode = "404", description = "no cash register found with the given id")}
            ,
            parameters = @Parameter(name = "cashRegisterId", required = true)
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCashRegister(@PathVariable @NotNull(message = cashRegisterIdNotNullMessage) Long id) {
        service.delete(id);
        return ResponseEntity.ok("cash register deactivated");
    }

}
