package fr.infercidium.PayMyBuddy.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class TransferAdd extends Transfer {
    @ManyToOne
    private User credited;

    @ManyToOne
    private BankAccount bankAccount;

    public TransferAdd() {
        setDateTime(LocalDateTime.now());
    }

    public TransferAdd(final User creditedC,
                    final BigDecimal amountC,
                    final String descriptionC,
                    final BankAccount bankAccountC) {
        this.credited = creditedC;
        setAmount(amountC);
        setDescription(descriptionC);
        setDateTime(LocalDateTime.now());
        this.bankAccount = bankAccountC;
    }

    public User getCredited() {
        return new User(credited);
    }

    public void setCredited(final User creditedS) {
        this.credited = creditedS;
    }

    public BankAccount getBankAccount() {
        return new BankAccount(bankAccount);
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "Transfert{"
                + "id = " + getId()
                + ", credited = " + credited
                + ", amount = " + getAmount()
                + ", description = '" + getDescription() + '\''
                + ", dateTime = " + getDateTime()
                + ", bankAccount = " + bankAccount
                + '}';
    }
}
