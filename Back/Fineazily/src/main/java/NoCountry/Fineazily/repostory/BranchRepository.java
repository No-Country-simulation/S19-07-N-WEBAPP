package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

}
