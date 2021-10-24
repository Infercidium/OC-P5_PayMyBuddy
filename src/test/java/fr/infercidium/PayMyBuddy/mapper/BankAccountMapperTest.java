package fr.infercidium.PayMyBuddy.mapper;

import fr.infercidium.PayMyBuddy.dto.BankAccountDto;
import fr.infercidium.PayMyBuddy.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = {BankAccountMapperImpl.class})
class BankAccountMapperTest {

    @Autowired
    private BankAccountMapper bankAccountMapperImpl;

    BankAccount bankAccount = new BankAccount("test1", "Jean Jean", LocalDate.now().plusMonths(2L), "123456789", "123", "FR123", "C123", null);
    BankAccountDto bankAccountDto = new BankAccountDto();

    @BeforeEach
    private void setUpPerTest() {
        bankAccountDto.setName(bankAccount.getName());
        bankAccountDto.setHolder(bankAccount.getHolder());
        bankAccountDto.setExpirationDate(String.valueOf(bankAccount.getExpirationDate()));
        bankAccountDto.setCardNumber(bankAccount.getCardNumber());
        bankAccountDto.setCryptogram(bankAccount.getCryptogram());
        bankAccountDto.setIban(bankAccount.getIban());
        bankAccountDto.setBic(bankAccount.getBic());
    }

    @Test
    void dtoToModel() {
        BankAccount bankAccountResult = bankAccountMapperImpl.dtoToModel(bankAccountDto);
        assertEquals(bankAccount.getName(), bankAccountResult.getName());
        assertEquals(bankAccount.getHolder(), bankAccountResult.getHolder());
        assertEquals(bankAccount.getExpirationDate(), bankAccountResult.getExpirationDate());
        assertEquals(bankAccount.getCardNumber(), bankAccountResult.getCardNumber());
        assertEquals(bankAccount.getCryptogram(), bankAccountResult.getCryptogram());
        assertEquals(bankAccount.getIban(), bankAccountResult.getIban());
        assertEquals(bankAccount.getBic(), bankAccountResult.getBic());

        BankAccount bankAccountResult2 = bankAccountMapperImpl.dtoToModel(null);
        assertNull(bankAccountResult2);
    }
}