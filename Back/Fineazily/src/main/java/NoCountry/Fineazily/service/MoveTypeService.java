package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.MoveTypeNotFoundException;
import NoCountry.Fineazily.model.entity.MoveType;
import NoCountry.Fineazily.repostory.MoveTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoveTypeService extends AService<MoveType, Long> {
    private final MoveTypeRepository moveTypeRepository;
    private final String notFoundMessage = "There isn't a branch with that id: ";

    @Override
    public void create(MoveType entity) {
        moveTypeRepository.save(entity);
    }

    @Override
    public MoveType findById(Long id) {
        return moveTypeRepository.findById(id)
                .orElseThrow(() -> new MoveTypeNotFoundException(notFoundMessage + id));

    }

    @Override
    public List<MoveType> findAll() {
        return moveTypeRepository.findAll();
    }

    @Override
    public void update(MoveType entity) {
        Long moveId = entity.getId();
        if (moveTypeRepository.existsById(moveId)) {
            moveTypeRepository.save(entity);
        } else
            throw new MoveTypeNotFoundException(notFoundMessage + moveId);
    }

    @Override
    public void delete(Long id) {
        if (moveTypeRepository.existsById(id))
            moveTypeRepository.deleteById(id);
        else
            throw new MoveTypeNotFoundException(notFoundMessage + id);
    }
}
