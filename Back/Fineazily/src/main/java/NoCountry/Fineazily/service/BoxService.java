package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.BoxNotFoundException;
import NoCountry.Fineazily.model.dto.BoxDto;
import NoCountry.Fineazily.model.entity.Box;
import NoCountry.Fineazily.model.mapper.BoxMapper;
import NoCountry.Fineazily.repostory.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoxService extends AService<Box, Long> {
    private final BoxRepository boxRepository;
    private final String notFound = "There isn't a box with that id: ";
    private final BranchService branchService;
    private final BoxMapper boxMapper;

    @Override
    public void create(Box entity) {
        boxRepository.save(entity);
    }

    public void create(Box entity, Long branchId) {
        entity.setBranch(
                branchService.findById(branchId));
        boxRepository.save(entity);
    }

    @Override
    public Box findById(Long id) {
        return boxRepository.findById(id).orElseThrow(()
                -> new BoxNotFoundException(notFound + id));
    }

    @Override
    public List<Box> findAll() {
        return boxRepository.findAll();
    }

    @Override
    public void update(Box entity) {
        if (boxRepository.existsById(entity.getId())) {
            boxRepository.save(entity);
        } else {
            throw new BoxNotFoundException(notFound + entity.getId());
        }
    }

    public void update(BoxDto dto){
        Box existingBox = findById(dto.id());
        boxMapper.updateExistingBox(dto, existingBox);
        boxRepository.save(existingBox);
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
