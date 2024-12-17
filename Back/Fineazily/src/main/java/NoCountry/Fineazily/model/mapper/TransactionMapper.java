package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper{
    Transaction toEntity(TransactionDto dto);

    TransactionDto toDto(Transaction transaction);

    void updateExistent(TransactionDto dto, @MappingTarget Transaction transaction);
}
