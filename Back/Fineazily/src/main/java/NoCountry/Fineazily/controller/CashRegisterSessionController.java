package NoCountry.Fineazily.controller;

import NoCountry.Fineazily.model.dto.request.CashRegisterSessionRequest;
import NoCountry.Fineazily.model.dto.response.CashRegisterSessionResponse;
import NoCountry.Fineazily.service.CashRegisterSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cash-register-session")
@Validated
public class CashRegisterSessionController {
    private final CashRegisterSessionService service;

    @Operation(summary = "new cash register session",
            description = "start a new session with its employee and cashRegister",
            responses = {
                    @ApiResponse(responseCode = "200", description = "session started successfully"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "employee or cash register not found with the given id"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "employee or cash register already had an active session"
                    )
            },
            parameters = @Parameter(name = "dto", description = "a dto request carrying session information", required = true))
    @PostMapping
    public ResponseEntity<?> createSession(@Valid @RequestBody CashRegisterSessionRequest dto) {
        service.createSession(dto);
        return new ResponseEntity<>("session created successfully", HttpStatus.OK);
    }

    @Operation(summary = "find session by id",
            description = "find the session for the given id if exists",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a response with the session values",
                            content = @Content(schema = @Schema(implementation = CashRegisterSessionResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "there isn't a session with the given id"
                    )
            },
            parameters = @Parameter(name = "sessionId", required = true))
    @GetMapping("/{sessionId}")
    public ResponseEntity<?> findById(@NotNull @PathVariable Long sessionId) {
        return new ResponseEntity<>(service.findSessionById(sessionId), HttpStatus.OK);
    }

    @Operation(summary = "find all sessions",
            description = "get all session without discriminate",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list with cashRegisterSession responses",
                            content = @Content(schema = @Schema(implementation = CashRegisterSessionResponse.class))
                    )
            })
    @GetMapping("/all")
    public ResponseEntity<?> findAllSessions() {
        return new ResponseEntity<>(service.findAllSessions(), HttpStatus.OK);
    }

    @Operation(summary = "find all active sessions",
            description = "get all session that still active",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "a list with cashRegisterSession responses",
                            content = @Content(schema = @Schema(implementation = CashRegisterSessionResponse.class))
                    )
            })
    @GetMapping("/all-active")
    public ResponseEntity<?> findAllActiveSessions() {
        return new ResponseEntity<>(service.findAllActiveSessions(), HttpStatus.OK);
    }

    @Operation(summary = "update session",
            description = "update a session with the given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "session updated"),
                    @ApiResponse(responseCode = "404", description = "session not found with the given id")
            },
            parameters = {
                    @Parameter(name = "sessionId", required = true),
                    @Parameter(name = "dto", description = "an dto carrying session info to be updated", required = true)
            })
    @PatchMapping("/{sessionId}")
    public ResponseEntity<?> updateSession(@NotNull @PathVariable Long sessionId,
                                           @Valid @RequestBody CashRegisterSessionRequest dto) {
        service.updateSession(dto, sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "end session",
            description = "mark the session as ended",
            responses = {
                    @ApiResponse(responseCode = "200", description = "session mark as ended"),
                    @ApiResponse(responseCode = "404", description = "session not found with the given id")

            },
            parameters = @Parameter(name = "sessionId", required = true))
    @PatchMapping("/end/{sessionId}")
    public ResponseEntity<?> endSession(@NotNull @PathVariable Long sessionId) {
        service.deleteSession(sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "delete session",
            description = "delete the session with the given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "session deleted"),
                    @ApiResponse(responseCode = "404", description = "session not found with the given id")
            },
            parameters = @Parameter(name = "sessionId", required = true))
    @DeleteMapping("/{sessionId}")
    public ResponseEntity<?> deleteSession(@NotNull @PathVariable Long sessionId) {
        service.endSession(sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
