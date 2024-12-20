package NoCountry.Fineazily.service;

import NoCountry.Fineazily.exception.EmployeeNotFoundException;
import NoCountry.Fineazily.model.dto.request.EmployeeRequest;
import NoCountry.Fineazily.model.dto.response.EmployeeResponse;
import NoCountry.Fineazily.model.entity.Employee;
import NoCountry.Fineazily.model.mapper.EmployeeMapper;
import NoCountry.Fineazily.repostory.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final AreaService areaService;
    private final EmployeeMapper mapper;

    public void createEmployee(EmployeeRequest dto) {
        Employee employee = mapper.toEntity(dto);
        employee.setArea(areaService.findById(dto.areaId()));

        if(employee.getCreatedAt() == null){
            employee.setCreatedAt(LocalDateTime.now());
        }
        if(employee.getStartDate() == null){
            employee.setStartDate(LocalDate.now());
        }
        repository.save(employee);
    }

    public Employee findById(Long employeeId) {
        return repository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("there isn't an employee with the given id:" + employeeId));

    }

    public EmployeeResponse findEmployeeById(Long employeeId) {
        return mapper.toDto(findById(employeeId));
    }

    public List<EmployeeResponse> findAllEmployees() {
        return repository.findAll().stream()
                .map(mapper::toDto).toList();
    }

    public List<EmployeeResponse> findAllActiveEmployees() {
        return repository.findAllByActiveIsTrue().stream()
                .map(mapper::toDto).toList();
    }

    public List<EmployeeResponse> findAllActiveEmployeesByAreaId(Long areaId) {
        return repository.findAllByActiveIsTrueAndAreaId(areaId).stream()
                .map(mapper::toDto).toList();
    }

    public void updateEmployee(EmployeeRequest dto, Long employeeId) {
        Employee emp = findById(employeeId);
        mapper.updateExisting(dto, emp);
        repository.save(emp);
    }

    public void deleteEmployee(Long employeeId) {
        Employee emp = findById(employeeId);
        emp.setActive(false);
        repository.save(emp);
    }

}
