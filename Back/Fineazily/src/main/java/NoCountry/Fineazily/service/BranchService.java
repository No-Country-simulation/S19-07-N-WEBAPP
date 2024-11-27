package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.BranchNotFoundException;
import NoCountry.Fineazily.model.dto.BranchDTO;
import NoCountry.Fineazily.model.entity.Branch;
import NoCountry.Fineazily.model.mapper.BranchMapper;
import NoCountry.Fineazily.repostory.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService extends AService<Branch, Long> {
    private final BranchRepository repository;
    private final String notFound = "There isn't a branch with that id: ";
    private final BranchMapper branchMapper;
    private final BranchRepository branchRepository;

    @Override
    public void create(Branch entity) {
        repository.save(entity);
    }

    @Override
    public Branch findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BranchNotFoundException(notFound + id));
    }

    @Override
    public List<Branch> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Branch entity) {
        if (repository.existsById(entity.getId())) {
            repository.save(entity);
        } else {
            throw new BranchNotFoundException(notFound + entity.getId());
        }
    }

    public void update(BranchDTO dto){
        Branch branch = findById(dto.id());
        branchMapper.updateBranch(dto, branch);
        branchRepository.save(branch);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
