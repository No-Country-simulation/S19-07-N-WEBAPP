package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.MoveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveTypeRepository extends JpaRepository<MoveType, Long> {
}
