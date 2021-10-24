package fr.infercidium.PayMyBuddy.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TransferUser extends Transfer {

    /**
     * Attribute credited corresponding to credited user.
     */
    @ManyToOne
    private User credited;

    /**
     * Attribute debited corresponding to debited user.
     */
    @ManyToOne
    private User debited;

    /**
     * Basic builder.
     */
    public TransferUser() {
        setDateTime(LocalDateTime.now());
    }

    /**
     * Complete builder.
     * @param creditedC attribute.
     * @param amountC attribute.
     * @param descriptionC attribute.
     * @param debitedC attribute.
     */
    public TransferUser(final User creditedC,
                    final BigDecimal amountC,
                    final String descriptionC,
                    final User debitedC) {
        this.credited = creditedC;
        setAmount(amountC);
        setDescription(descriptionC);
        setDateTime(LocalDateTime.now());
        this.debited = debitedC;
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
                + ", debited = " + debited
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
        if (!(o instanceof TransferUser)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TransferUser that = (TransferUser) o;
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
