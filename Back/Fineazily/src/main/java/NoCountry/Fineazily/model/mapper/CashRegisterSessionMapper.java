package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.request.CashRegisterSessionRequest;
import NoCountry.Fineazily.model.dto.response.CashRegisterSessionResponse;
import NoCountry.Fineazily.model.entity.CashRegisterSession;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CashRegisterSessionMapper {
    CashRegisterSession toEntity(CashRegisterSessionRequest request);

    CashRegisterSessionResponse toDto(CashRegisterSession session);

    void updateExistingEntity(CashRegisterSessionRequest request, @MappingTarget CashRegisterSession session);
}
