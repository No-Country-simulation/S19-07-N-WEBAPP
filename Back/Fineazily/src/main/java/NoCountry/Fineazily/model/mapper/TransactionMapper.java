package NoCountry.Fineazily.model.mapper;

import NoCountry.Fineazily.model.dto.TransactionDto;
import NoCountry.Fineazily.model.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends BasicMapper<Transaction, TransactionDto> {
}
