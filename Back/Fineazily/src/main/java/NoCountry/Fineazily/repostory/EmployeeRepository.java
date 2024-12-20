package NoCountry.Fineazily.repostory;

import NoCountry.Fineazily.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee> findAllByActiveIsTrueAndAreaId(Long areaId);

    public List<Employee> findAllByActiveIsTrue();
}
