package NoCountry.Fineazily.service;


import NoCountry.Fineazily.exception.transactionExceptions.IllegalMethodTypeException;
import NoCountry.Fineazily.exception.transactionExceptions.TransactionNotFoundException;
import NoCountry.Fineazily.model.dto.request.TransactionRequest;
import NoCountry.Fineazily.model.dto.response.TransactionResponse;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.enums.MethodType;
import NoCountry.Fineazily.model.enums.MoveType;
import NoCountry.Fineazily.model.mapper.TransactionMapper;
import NoCountry.Fineazily.repostory.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@Service
public class TransactionService {
    private final String transactionNotFound = "There isn't a transaction with that id:";
    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;
    private static final Map<MoveType, Set<MethodType>> validMethodsByMove = new HashMap<>();
    private final CashRegisterSessionService cashRegisterSessionService;
    private final TagService tagService;

    //this block will run once when the class load in memory, makes easier and legible initialize
    //validMethods map
    static {
        validMethodsByMove.put(MoveType.INCOME, EnumSet.of(MethodType.CARD, MethodType.CASH, MethodType.TRANSFER));
        validMethodsByMove.put(MoveType.EGRESS, EnumSet.of(MethodType.DIRECT, MethodType.FISCAL, MethodType.LOAN));
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(transactionNotFound + id));
    }

    public List<TransactionResponse> findAll() {
        return transactionRepository.findAll().stream().map(mapper ::toDto).toList();
    }


    public void update(Transaction entity) {
        Long transactionId = entity.getId();
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.save(entity);
        } else {
            throw new TransactionNotFoundException(transactionNotFound + transactionId);
        }
    }


    public void delete(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
        } else {
            throw new TransactionNotFoundException(transactionNotFound + id);
        }
    }

    public void createTransaction(TransactionRequest dto, Long sessionId, Long tagId) {
        Transaction t = mapper.toEntity(dto);
        validateTransactionMethodType(t.getMethodType(), t.getMoveType());
        if (t.getCreationDate() == null) {
            t.setCreationDate(LocalDate.now());
        }
        t.setSession(cashRegisterSessionService.findById(sessionId));
        t.setTag(tagService.findById(tagId));

        transactionRepository.save(t);
    }

    public void updateTransaction(TransactionRequest dto, Long id) {
        Transaction t = findById(id);
        mapper.updateExistent(dto, t);
        update(t);
    }

    //----------------------------filtered searching----------------------
    public List<TransactionResponse> findTransactionsByEmployeeId(Long userId) {
        return transactionRepository.findTransactionsByEmployeeId(userId)
                .stream().map(mapper::toDto).toList();
    }

    public List<TransactionResponse> findTransactionsByCashRegisterId(Long cashRegisterId) {
        return transactionRepository.findTransactionsByCashRegisterId(cashRegisterId)
                .stream().map(mapper::toDto).toList();
    }

    public List<TransactionResponse> findTransactionsByBranchId(Long branchId) {
        return transactionRepository.findTransactionsByBranchId(branchId)
                .stream().map(mapper::toDto).toList();
    }

    public List<TransactionResponse> findTransactionsByMethodType(MethodType methodType) {
        return transactionRepository.findTransactionsByMethodType(methodType)
                .stream().map(mapper::toDto).toList();
    }

    public List<TransactionResponse> findTransactionsByMoveType(MoveType moveType) {
        return transactionRepository.findTransactionsByMoveType(moveType)
                .stream().map(mapper::toDto).toList();
    }
    //--------------------------------------------Balance and filtered balance

    public Float getAmountsSinceAndUntilDateAndByMoveType(LocalDate since, LocalDate until, MoveType moveType) {
        Float result;
        if (since != null) {
            result = transactionRepository.sumAmountsSinceAndUntilCreationDateAndMoveType
                    //wow! I didn't know that you can use Objets instead of conditional when a value is null or not
                            (moveType, since, Objects.requireNonNullElseGet(until, LocalDate::now));
        } else {
            result = transactionRepository.sumAmountsByMoveType(moveType);
        }
        return result != null ? result : 0.0f;
    }

    public Float getAmountsSinceAndUntilByMethodType(MethodType methodType, LocalDate since, LocalDate until) {
        Float result;
        if (since != null) {
            result = transactionRepository.sumAmountsSinceAndUntilCreationDateAndMethodType
                    (methodType, since, Objects.requireNonNullElseGet(until, LocalDate::now));
        } else {
            result = transactionRepository.sumAmountsByMethodType(methodType);
        }
        return result != null ? result : 0.0f;
    }


    public Float getTotalEarnings(LocalDate since, LocalDate until) {
        if (since != null) {
            return transactionRepository.sumAmountsSinceAndUntilCreationDateAndMoveType(MoveType.INCOME, since, Objects.requireNonNullElseGet(until, LocalDate::now)) -
                    transactionRepository.sumAmountsSinceAndUntilCreationDateAndMoveType(MoveType.EGRESS, since, Objects.requireNonNullElseGet(until, LocalDate::now));
        }
        //add here a validation to show earnings of all time if dates are null
        return transactionRepository.sumAmountsByMoveType(MoveType.INCOME) - transactionRepository.sumAmountsByMoveType(MoveType.EGRESS);
    }


    //-------------------------------------------------------------------

    //-validate method for moveType according methodType
    private void validateTransactionMethodType(MethodType method, MoveType move) {
        Set<MethodType> validMethods = validMethodsByMove.get(move);
        if (validMethods == null || !validMethods.contains(method)) {
            throw new IllegalMethodTypeException(
                    validMethods == null ?
                            "there isn't any method type for that kind of movement: " + move :
                            "The current method type is invalid for the move: " + move
            );
        }
    }
}
