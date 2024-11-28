package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.MethodTypeNotFoundException;
import NoCountry.Fineazily.model.entity.MethodType;
import NoCountry.Fineazily.repostory.MethodTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MethodTypeService extends AService<MethodType, Long> {
    private final MethodTypeRepository methodTypeRepository;
    private final String notFoundMessage = "There isn't a method type with that id: ";

    @Override
    public void create(MethodType entity) {
        methodTypeRepository.save(entity);
    }

    @Override
    public MethodType findById(Long id) {
        return methodTypeRepository.findById(id)
                .orElseThrow(() -> new MethodTypeNotFoundException(notFoundMessage +id));
    }

    @Override
    public List<MethodType> findAll() {
        return methodTypeRepository.findAll();
    }

    @Override
    public void update(MethodType entity) {
        Long id = entity.getId();
        if(methodTypeRepository.existsById(id)){
            methodTypeRepository.save(entity);
        }else{
            throw new MethodTypeNotFoundException(notFoundMessage +id);
        }

    }

    @Override
    public void delete(Long id) {

        if(methodTypeRepository.existsById(id)){
            methodTypeRepository.deleteById(id);
        }else{
            throw new MethodTypeNotFoundException(notFoundMessage +id);
        }
    }
}
