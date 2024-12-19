package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.request.CashRegisterSessionRequest;
import NoCountry.Fineazily.model.dto.response.CashRegisterSessionResponse;
import NoCountry.Fineazily.model.entity.CashRegisterSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CashRegisterSessionMapper {
    CashRegisterSession toEntity(CashRegisterSessionRequest request);

    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "cashRegister.id", target = "cashRegisterId")
    CashRegisterSessionResponse toDto(CashRegisterSession session);

    void updateExistingEntity(CashRegisterSessionRequest request, @MappingTarget CashRegisterSession session);
}
