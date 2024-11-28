package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.MethodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodTypeRepository extends JpaRepository<MethodType, Long> {
}
