package fr.infercidium.PayMyBuddy.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TransferUser extends Transfer {
    @ManyToOne
    private User credited;

    @ManyToOne
    private User debited;

    public TransferUser() {
        setDateTime(LocalDateTime.now());
    }

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

    public User getCredited() {
        return new User(credited);
    }

    public void setCredited(final User creditedS) {
        this.credited = creditedS;
    }

    public User getDebited() {
        return new User(debited);
    }

    public void setDebited(final User debitedS) {
        this.debited = debitedS;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId());
    }
}
