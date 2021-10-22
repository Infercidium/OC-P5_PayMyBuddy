package fr.infercidium.PayMyBuddy.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TransferAdd extends Transfer {

    /**
     * Attribute credited corresponding to credited user.
     */
    @ManyToOne
    private User credited;

    /**
     * Attribute bankAccount corresponding to bankAccount user.
     */
    @ManyToOne
    private BankAccount bankAccount;

    /**
     * Basic builder.
     */
    public TransferAdd() {
        setDateTime(LocalDateTime.now());
    }

    /**
     * Complete builder.
     * @param creditedC attribute.
     * @param amountC attribute.
     * @param descriptionC attribute.
     * @param bankAccountC attribute.
     */
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

    /**
     * Getter of credited.
     * @return credited.
     */
    public User getCredited() {
        return new User(credited);
    }

    /**
     * Setter of credited.
     * @param creditedS : new credited.
     */
    public void setCredited(final User creditedS) {
        this.credited = creditedS;
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
                + ", credited = " + credited
                + ", amount = " + getAmount()
                + ", description = '" + getDescription() + '\''
                + ", dateTime = " + getDateTime()
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
        if (!(o instanceof TransferAdd)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TransferAdd that = (TransferAdd) o;
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
