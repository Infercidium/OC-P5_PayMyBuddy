package fr.infercidium.PayMyBuddy.mapper;

import fr.infercidium.PayMyBuddy.dto.BankAccountDto;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    /**
     * Transform a dto into a model.
     * @param bankAccountDto : the dto.
     * @return BankAccount model.
     */
    BankAccount dtoToModel(BankAccountDto bankAccountDto);
}
