package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.BranchDTO;
import NoCountry.Fineazily.model.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {
    Branch toEntity(BranchDTO dto);

    BranchDTO toDto(Branch branch);
}
