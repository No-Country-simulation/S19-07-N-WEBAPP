package NoCountry.Fineazily.service;

import NoCountry.Fineazily.model.entity.MethodType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MethodTypeService extends AService<MethodType, Long> {

    @Override
    public void create(MethodType entity) {

    }

    @Override
    public MethodType findById(Long id) {
        return null;
    }

    @Override
    public List<MethodType> findAll() {
        return List.of();
    }

    @Override
    public void update(MethodType entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
