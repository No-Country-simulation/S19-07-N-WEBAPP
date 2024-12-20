package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.request.EmployeeRequest;
import NoCountry.Fineazily.model.dto.response.EmployeeResponse;
import NoCountry.Fineazily.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(EmployeeRequest dto);

    @Mapping(source = "area.id", target = "areaId")
    EmployeeResponse toDto(Employee employee);

    void updateExisting(EmployeeRequest dto, @MappingTarget Employee employee);


}
