package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.TransactionNotFoundException;
import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Box;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.entity.User;
import NoCountry.Fineazily.model.mapper.TransactionMapper;
import NoCountry.Fineazily.repostory.TransactionRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService extends AService<Transaction, Long> {
    private final TransactionRepository repository;
    private final String transactionNotFoundMessage = "There isn't a transaction with that id: ";
    private final TransactionMapper mapper;
    private final UserService userService;
    private final BoxService boxService;


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

    public void update(TransactionDto dto) {
        Transaction transaction = findById(dto.id());
        mapper.updateEntity(dto, transaction);
        repository.save(transaction);
    }

    @Override
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new TransactionNotFoundException(transactionNotFoundMessage + id);
        }
    }

    public void create( TransactionDto dto, Long userId, Long boxId, Long methodTypeId) {
        Box box = boxService.findById(boxId);
        User user = userService.findById(userId);
        Transaction transaction = mapper.toEntity(dto);
        transaction.setUser(user);
        transaction.setBox(box);
        transaction.setMethodType();

        repository.save(transaction)

    }
}
