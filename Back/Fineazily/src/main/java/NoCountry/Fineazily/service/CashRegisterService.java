package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.BoxNotFoundException;
import NoCountry.Fineazily.model.dto.request.CashRegisterRequest;
import NoCountry.Fineazily.model.dto.response.CashRegisterResponse;
import NoCountry.Fineazily.model.entity.CashRegister;
import NoCountry.Fineazily.model.mapper.CashRegisterMapper;
import NoCountry.Fineazily.repostory.CashRegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashRegisterService {
    private final CashRegisterRepository repository;
    private final String notFound = "There isn't a box with that id: ";
    private final AreaService areaService;
    private final CashRegisterMapper mapper;


    public void create(CashRegisterRequest dto, Long areaId) {
        CashRegister cr = mapper.toEntity(dto);
        if (cr.getCreatedAt() == null) {
            cr.setCreatedAt(LocalDateTime.now());
        }
        cr.setArea(areaService.findById(areaId));
        repository.save(cr);
    }


    public CashRegister findById(Long id) {
        return repository.findById(id).orElseThrow(()
                -> new BoxNotFoundException(notFound + id));
    }

    public CashRegisterResponse findCashRegisterById(Long id) {
        return mapper.toDto(findById(id));
    }


    public List<CashRegisterResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }

    public List<CashRegisterResponse> findAllActive() {
        return repository.findAllByActiveIsTrue().stream()
                .map(mapper::toDto).toList();
    }

    public List<CashRegisterResponse> findAllByArea(Long areaId) {
        return repository.findAllByActiveIsTrueAndAreaId(areaId).stream()
                .map(mapper::toDto).toList();
    }

    public List<CashRegisterResponse> findAllByBranchId(Long branchId) {
        return repository.findAllByBranchId(branchId).stream()
                .map(mapper::toDto).toList();
    }

    public void update(CashRegisterRequest dto, Long cashRegisterId) {
        CashRegister cr = findById(cashRegisterId);
        mapper.updateExistingBox(dto, cr);
        cr.setUpdatedAt(LocalDateTime.now());
        repository.save(cr);
    }

    public void delete(Long id) {
        CashRegister cr = findById(id);
        cr.setActive(false);
        repository.save(cr);
    }
}
