package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.request.CashRegisterRequest;
import NoCountry.Fineazily.model.dto.response.CashRegisterResponse;
import NoCountry.Fineazily.model.entity.CashRegister;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CashRegisterMapper {
    CashRegister toEntity(CashRegisterRequest dto);

    CashRegisterResponse toDto(CashRegister cashRegister);

    //instead of creating a new box it will replace the new values
    void updateExistingBox(CashRegisterRequest dto, @MappingTarget CashRegister cashRegister);
}
