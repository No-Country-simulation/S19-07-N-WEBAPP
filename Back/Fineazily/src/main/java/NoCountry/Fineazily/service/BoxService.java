package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.BoxNotFoundException;
import NoCountry.Fineazily.model.dto.BoxDto;
import NoCountry.Fineazily.model.entity.CashRegister;
import NoCountry.Fineazily.model.mapper.BoxMapper;
import NoCountry.Fineazily.repostory.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoxService extends AService<CashRegister, Long> {
    private final BoxRepository boxRepository;
    private final String notFound = "There isn't a box with that id: ";
    private final BranchService branchService;
    private final BoxMapper boxMapper;

    @Override
    public void create(CashRegister entity) {
        boxRepository.save(entity);
    }

    public void create(CashRegister entity, Long branchId) {
        entity.setBranch(
                branchService.findById(branchId));
        boxRepository.save(entity);
    }

    @Override
    public CashRegister findById(Long id) {
        return boxRepository.findById(id).orElseThrow(()
                -> new BoxNotFoundException(notFound + id));
    }

    @Override
    public List<CashRegister> findAll() {
        return boxRepository.findAll();
    }

    @Override
    public void update(CashRegister entity) {
        if (boxRepository.existsById(entity.getId())) {
            boxRepository.save(entity);
        } else {
            throw new BoxNotFoundException(notFound + entity.getId());
        }
    }

    public void update(BoxDto dto){
        CashRegister existingCashRegister = findById(dto.id());
        boxMapper.updateExistingBox(dto, existingCashRegister);
        boxRepository.save(existingCashRegister);
    }

    @Override
    public void delete(Long id) {
        if (boxRepository.existsById(id)) {
            boxRepository.deleteById(id);
        } else {
            throw new BoxNotFoundException(notFound + id);
        }
    }
}
