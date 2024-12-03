package NoCountry.Fineazily.service;


import NoCountry.Fineazily.exception.TransactionNotFoundException;
import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Transaction;
import NoCountry.Fineazily.model.mapper.TransactionMapper;
import NoCountry.Fineazily.repostory.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TransactionService extends AService<Transaction,Long>{
    private final String transactionNotFound = "There isn't a transaction with that id:";
    private final TransactionRepository transactionRepository;
    private final TransactionMapper mapper;
    private final UserService userService;
    private final BoxService boxService;
    private final MethodTypeService methodTypeService;
    private final MoveTypeService moveTypeService;

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

    public void createTransaction(TransactionDto dto, Long userId, Long boxId, Long methodTypeId, Long moveTypeId){
        Transaction t = mapper.toEntity(dto);
        t.setUser(userService.findById(userId));
        t.setBox(boxService.findById(boxId));
        t.setMoveType(moveTypeService.findById(moveTypeId));
        //doing here validations with move type and methodType
        t.setMethodType(methodTypeService.findById(methodTypeId));
        create(t);
    }
}
