package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.BranchDTO;
import NoCountry.Fineazily.model.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface BranchMapper extends BasicMapper<Branch, BranchDTO> {

}
