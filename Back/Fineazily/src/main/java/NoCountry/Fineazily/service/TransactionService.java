package NoCountry.Fineazily.service;


import NoCountry.Fineazily.exception.transactionExceptions.IllegalMethodTypeException;
import NoCountry.Fineazily.exception.transactionExceptions.TransactionNotFoundException;
import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.enums.MethodType;
import NoCountry.Fineazily.model.enums.MoveType;
import NoCountry.Fineazily.model.mapper.TransactionMapper;
import NoCountry.Fineazily.repostory.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class TransactionService extends AService<Transaction, Long> {
    private final String transactionNotFound = "There isn't a transaction with that id:";
    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;
    private final UserService userService;
    private final BoxService boxService;
    private static final Map<MoveType, Set<MethodType>> validMethodsByMove = new HashMap<>();

    //this block will run once when the class load in memory, makes easier and legible initialize
    //validMethods map
    static {
        validMethodsByMove.put(MoveType.INCOME, EnumSet.of(MethodType.CARD, MethodType.CASH, MethodType.TRANSFER));
        validMethodsByMove.put(MoveType.EGRESS, EnumSet.of(MethodType.DIRECT, MethodType.FISCAL, MethodType.LOAN));
    }

    @Override
    public void create(Transaction entity) {
        transactionRepository.save(entity);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(transactionNotFound + id));
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void update(Transaction entity) {
        Long transactionId = entity.getId();
        if (transactionRepository.existsById(transactionId)) {
            transactionRepository.save(entity);
        } else {
            throw new TransactionNotFoundException(transactionNotFound + transactionId);
        }
    }

    @Override
    public void delete(Long id) {
        if (transactionRepository.existsById(id)) {
            transactionRepository.deleteById(id);
        } else {
            throw new TransactionNotFoundException(transactionNotFound + id);
        }
    }

    public void createTransaction(TransactionDto dto, Long userId, Long boxId) {
        Transaction t = mapper.toEntity(dto);
        validateTransactionMethodType(t.getMethodType(), t.getMoveType());
        t.setUser(userService.findById(userId));
        t.setBox(boxService.findById(boxId));
        create(t);
    }

    public void updateTransaction(TransactionDto dto){
        Transaction t = findById(dto.id());
        mapper.updateExistent(dto, t);
        update(t);
    }

    //filtered searching
    public List<Transaction> findTransactionsByUserId(Long userId) {
        return transactionRepository.findTransactionsByUser(
                userService.findById(userId));
    }

    public List<Transaction> findTransactionsByBoxId(Long boxId) {
        return transactionRepository.findTransactionsByBox(
                boxService.findById(boxId));
    }

    public List<Transaction> findTransactionsByBranchId(Long branchId) {
        return transactionRepository.findTransactionsByBranchId(branchId);
    }

    //validate method for moveType according methodType
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
