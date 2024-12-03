package NoCountry.Fineazily.service;


import NoCountry.Fineazily.exception.TransactionNotFoundException;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.repostory.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionService extends AService<Transaction,Long>{
    private final String transactionNotFound = "";
    private final TransactionRepository transactionRepository;

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
        if(transactionRepository.existsById(transactionId)){
            transactionRepository.save(entity);
        }else {
            throw new TransactionNotFoundException(transactionNotFound + transactionId);
        }
    }

    @Override
    public void delete(Long id) {
        if(transactionRepository.existsById(id)){
            transactionRepository.deleteById(id);
        }else {
            throw new TransactionNotFoundException(transactionNotFound + id);
        }
    }
}
