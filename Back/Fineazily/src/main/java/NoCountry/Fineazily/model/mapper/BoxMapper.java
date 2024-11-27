package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.BoxDto;
import NoCountry.Fineazily.model.entity.Box;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BoxMapper extends BasicMapper<Box, BoxDto>{
}
