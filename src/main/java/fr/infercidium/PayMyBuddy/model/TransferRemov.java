package fr.infercidium.PayMyBuddy.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TransferRemov extends Transfer {
    @ManyToOne
    private User debited;

    @ManyToOne
    private BankAccount bankAccount;

    public TransferRemov() {
        setDateTime(LocalDateTime.now());
    }

    public TransferRemov(final BigDecimal amountC,
                    final String descriptionC,
                    final User debitedC,
                    final BankAccount bankAccountC) {
        setAmount(amountC);
        setDescription(descriptionC);
        setDateTime(LocalDateTime.now());
        this.debited = debitedC;
        this.bankAccount = bankAccountC;
    }

    public User getDebited() {
        return new User(debited);
    }

    public void setDebited(final User debitedS) {
        this.debited = debitedS;
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
                + ", amount = " + getAmount()
                + ", description = '" + getDescription() + '\''
                + ", dateTime = " + getDateTime()
                + ", debited = " + debited
                + ", bankAccount = " + bankAccount
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransferRemov)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TransferRemov that = (TransferRemov) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
