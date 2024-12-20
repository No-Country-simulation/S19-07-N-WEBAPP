package NoCountry.Fineazily.exception;

import NoCountry.Fineazily.exception.cashRegisterSessionExceptions.CashRegisterSessionNotFoundException;
import NoCountry.Fineazily.exception.cashRegisterSessionExceptions.LastSessionNotEndedException;
import NoCountry.Fineazily.exception.roleExceptions.RoleNotFoundException;
import NoCountry.Fineazily.exception.transactionExceptions.IllegalMethodTypeException;
import NoCountry.Fineazily.exception.transactionExceptions.TransactionNotFoundException;
import NoCountry.Fineazily.exception.userExceptions.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalAdviceController {

    public record ErrorResponse(String message, HttpStatus status, List<String> errors) {
        public ErrorResponse(String message, HttpStatus status) {
            this(message, status, null);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse("Validación fallida", HttpStatus.BAD_REQUEST, errores);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String message = Objects.requireNonNull(ex.getRootCause()).getMessage();
        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<?> handlePropertyReferenceException(PropertyReferenceException ex) {
        ErrorResponse apiResponse = new ErrorResponse("Invalid parameter: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<ErrorResponse> handleMailSendException(MailSendException ex) {
        String message = ex.getMostSpecificCause() != null ? ex.getMostSpecificCause().getMessage() : "Unknown error occurred while sending email.";
        ErrorResponse errorResponse = new ErrorResponse("Error sending email: " + message, HttpStatus.SERVICE_UNAVAILABLE);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleSignatureException(SignatureException ex) {
        String message = "Token inválido o la firma del token no coincide.";
        ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> parameterNullException(ConstraintViolationException ex) {
        List<String> errors = List.of(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("Validación fallida", HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleServiceException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }

    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<?> handleServiceException(BranchNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<?> handleServiceException(RoleNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<?> transactionNotFoundException(TransactionNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, errorResponse.status());
    }

    @ExceptionHandler(IllegalMethodTypeException.class)
    public ResponseEntity<?> illegalMethodTypeException(IllegalMethodTypeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CashRegisterSessionNotFoundException.class)
    public ResponseEntity<?> sessionNotFoundException(CashRegisterSessionNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LastSessionNotEndedException.class)
    public ResponseEntity<?> sessionAlreadyActiveException(LastSessionNotEndedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> employeeNotFoundException(EmployeeNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}

}
