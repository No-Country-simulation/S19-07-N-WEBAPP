package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.TransactionNotFoundException;
import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Box;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.mapper.TransactionMapper;
import NoCountry.Fineazily.repostory.TransactionRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService extends AService<Transaction, Long> {
    private final TransactionRepository repository;
    private final String transactionNotFoundMessage = "There isn't a transaction with that id: ";
    private final TransactionMapper mapper;
    private final BoxService boxService;
    private final MethodTypeService methodTypeService;
    private final MoveTypeService moveTypeService;
    private final UserService userService;


    @Override
    public void create(Transaction entity) {
        repository.save(entity);
    }

    @Override
    public Transaction findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(transactionNotFoundMessage + id));
    }

    @Override
    public List<Transaction> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Transaction entity) {
        if (repository.existsById(entity.getId())) {
            repository.save(entity);
        } else {
            throw new TransactionNotFoundException(transactionNotFoundMessage + entity.getId());
        }
    }

    public Transaction update(TransactionDto dto) {
        Transaction transaction = findById(dto.id());
        mapper.updateEntity(dto, transaction);
        return repository.save(transaction);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new TransactionNotFoundException(transactionNotFoundMessage + id);
        }
    }

    public void create( TransactionDto dto, Long boxId,Long userId, Long methodTypeId, Long moveTypeId) {


        Transaction transaction = mapper.toEntity(dto);
        transaction.setBox(boxService.findById(boxId));
        transaction.setUser(userService.findById(userId));
        transaction.setMethodType(methodTypeService.findById(methodTypeId));
        transaction.setMoveType(moveTypeService.findById(moveTypeId));
        transaction.setCreationDate(LocalDate.now());

        repository.save(transaction);

    }
}
