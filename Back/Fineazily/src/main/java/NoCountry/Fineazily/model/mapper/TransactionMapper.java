package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.request.TransactionRequest;
import NoCountry.Fineazily.model.dto.response.TransactionResponse;
import NoCountry.Fineazily.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper{
    Transaction toEntity(TransactionRequest dto);

    TransactionResponse toDto(Transaction transaction);

    void updateExistent(TransactionRequest dto, @MappingTarget Transaction transaction);
}
