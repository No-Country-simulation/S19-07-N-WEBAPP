package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.BoxDto;
import NoCountry.Fineazily.model.entity.CashRegister;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BoxMapper {
    CashRegister toEntity(BoxDto dto);

    BoxDto toDto(CashRegister cashRegister);

    //instead of creating a new box it will replace the new values
    void updateExistingBox(BoxDto dto, @MappingTarget CashRegister cashRegister);
}
