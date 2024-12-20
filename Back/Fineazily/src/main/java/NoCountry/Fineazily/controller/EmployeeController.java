package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.request.EmployeeRequest;
import NoCountry.Fineazily.model.dto.response.EmployeeResponse;
import NoCountry.Fineazily.service.EmployeeService;
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

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee/")
public class EmployeeController {
    private final EmployeeService service;

    @Operation(
            summary = "create employee",
            description = "create a new employee",
            responses = @ApiResponse(responseCode = "200", description = "employee created"),
            parameters = @Parameter(name = "dto", required = true)
    )
    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeRequest dto) {
        service.createEmployee(dto);
        return new ResponseEntity<>("employee created", HttpStatus.OK);
    }

    @Operation(
            summary = "get all employees",
            description = "get all employees without discriminate",
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "a list of employees response",
                    content = @Content(schema = @Schema(implementation = EmployeeResponse.class)))
    )
    @GetMapping("/all")
    public ResponseEntity<?> findAllEmployees() {
        return new ResponseEntity<>(service.findAllEmployees(), HttpStatus.OK);
    }

    @Operation(
            summary = "get all active employees",
            description = "get only employees active",
            responses = @ApiResponse(
                    responseCode = "200",
                    description = "a list of employees response",
                    content = @Content(schema = @Schema(implementation = EmployeeResponse.class)))
    )
    @GetMapping("/all-active")
    public ResponseEntity<?> findAllActiveEmployees() {
        return new ResponseEntity<>(service.findAllActiveEmployees(), HttpStatus.OK);
    }

    @Operation(
            summary = "get employees by area",
            description = "get all active employees for an area by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list of employees response",
                            content = @Content(schema = @Schema(implementation = EmployeeResponse.class))),
                    @ApiResponse(responseCode = "404", description = "area not found for the given id")},
            parameters = @Parameter(name = "areaId", required = true)
    )
    @GetMapping("/area/{areaId}")
    public ResponseEntity<?> findAllActiveEmployeesByAreaId(@NotNull @PathVariable Long areaId) {
        return new ResponseEntity<>(service.findAllActiveEmployeesByAreaId(areaId), HttpStatus.OK);
    }

    @Operation(
            summary = "find employee",
            description = "get an employee by its id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "an employee response",
                            content = @Content(schema = @Schema(implementation = EmployeeResponse.class))),
                    @ApiResponse(responseCode = "404", description = "employee not found for the given id")},
            parameters = @Parameter(name = "employeeId", required = true)
    )
    @GetMapping("/{employeeId}")
    public ResponseEntity<?> findEmployeeById(@NotNull @PathVariable Long employeeId) {
        return new ResponseEntity<>(service.findEmployeeById(employeeId), HttpStatus.OK);
    }

    @Operation(
            summary = "update employee",
            description = "update an existing employee using a dto and id's employee",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "employee updated",
                    content = @Content(schema = @Schema(implementation = EmployeeResponse.class))),
                    @ApiResponse(responseCode = "404", description = "employee not found for the given id")},
            parameters = {
                    @Parameter(name = "employeeId", required = true),
                    @Parameter(name = "dto", required = true)}
    )
    @PatchMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeRequest dto, @NotNull @PathVariable Long employeeId) {
        service.updateEmployee(dto, employeeId);
        return new ResponseEntity<>("employee updated", HttpStatus.OK);
    }

    @Operation(
            summary = "delete employee",
            description = "deactivate an employee(logic delete/ soft delete)",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "employee deleted/deactivated",
                            content = @Content(schema = @Schema(implementation = EmployeeResponse.class))),
                    @ApiResponse(responseCode = "404", description = "employee not found for the given id")},
            parameters = @Parameter(name = "employeeId", required = true)
    )
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@NotNull @PathVariable Long employeeId) {
        service.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
