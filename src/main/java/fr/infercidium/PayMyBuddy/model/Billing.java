package fr.infercidium.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Billing {
    /**
     * Attribute id corresponding to id generate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    /**
     * User attribute corresponds to the billed user.
     */
    @ManyToOne
    private User user;

    /**
     * Transfer attribute corresponds to the transfer of the billing.
     */
    @ManyToOne
    private TransferUser transfer;

    /**
     * Attribute amount corresponds to the sum of the billing.
     */
    private BigDecimal amount;

    /**
     * Basic builder.
     */
    public Billing() { }

    /**
     * Complete builder.
     * @param userC attribute.
     * @param transferC attribute.
     * @param amountC attribute.
     */
    public Billing(final User userC,
                   final TransferUser transferC,
                   final BigDecimal amountC) {
        this.user = userC;
        this.transfer = transferC;
        this.amount = amountC;
    }

    /**
     * Getter of id.
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter of id.
     * @param idS : new id.
     */
    public void setId(final Long idS) {
        this.id = idS;
    }

    /**
     * Getter of user.
     * @return user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter of user.
     * @param userS : new user.
     */
    public void setUser(final User userS) {
        this.user = userS;
    }

    /**
     * Getter of transfer.
     * @return transfer.
     */
    public TransferUser getTransfer() {
        return transfer;
    }

    /**
     * Setter of transfer.
     * @param transferS : new transfer.
     */
    public void setTransfer(final TransferUser transferS) {
        this.transfer = transferS;
    }

    /**
     * Getter of amount.
     * @return amount.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Setter of amount.
     * @param amountS : new amount.
     */
    public void setAmount(final BigDecimal amountS) {
        this.amount = amountS;
    }

    /**
     * ToString method.
     * @return toString.
     */
    @Override
    public String toString() {
        return "Billing{"
                + "id = " + id
                + ", user = " + user
                + ", transfer = " + transfer
                + ", amount = " + amount
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
        if (!(o instanceof Billing)) {
            return false;
        }
        Billing billing = (Billing) o;
        return getId().equals(billing.getId());
    }

    /**
     * HashCode method.
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
