package fr.infercidium.PayMyBuddy.mapper;

import fr.infercidium.PayMyBuddy.dto.BankAccountDto;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    BankAccount dtoToModel(BankAccountDto bankAccountDto);
    BankAccountDto modelToDto(BankAccount bankAccount);
}
