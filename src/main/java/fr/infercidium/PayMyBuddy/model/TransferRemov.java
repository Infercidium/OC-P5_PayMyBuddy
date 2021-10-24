package fr.infercidium.PayMyBuddy.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TransferRemov extends Transfer {

    /**
     * Attribute debited corresponding to debited user.
     */
    @ManyToOne
    private User debited;

    /**
     * Attribute bankAccount corresponding to bankAccount user.
     */
    @ManyToOne
    private BankAccount bankAccount;

    /**
     * Basic builder.
     */
    public TransferRemov() {
        setDateTime(LocalDateTime.now());
    }

    /**
     * Complete builder.
     * @param amountC attribute.
     * @param descriptionC attribute.
     * @param debitedC attribute.
     * @param bankAccountC attribute.
     */
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

    /**
     * Getter of debited.
     * @return debited.
     */
    public User getDebited() {
        return new User(debited);
    }

    /**
     * Setter of debited.
     * @param debitedS : new debited.
     */
    public void setDebited(final User debitedS) {
        this.debited = debitedS;
    }

    /**
     * Getter of bankAccount.
     * @return bankAccount.
     */
    public BankAccount getBankAccount() {
        return new BankAccount(bankAccount);
    }

    /**
     * Setter of bankAccount.
     * @param bankAccountS : new bankAccount.
     */
    public void setBankAccount(final BankAccount bankAccountS) {
        this.bankAccount = bankAccountS;
    }

    /**
     * ToString method.
     * @return toString.
     */
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

    /**
     * Equals method.
     * @param o : element to compare.
     * @return result of the comparison.
     */
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

    /**
     * HashCode method.
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
