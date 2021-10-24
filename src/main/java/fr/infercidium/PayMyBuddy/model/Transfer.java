package fr.infercidium.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Transfers")
public class Transfer {

    /**
     * Attribute id corresponding to id generate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    /**
     * Attribute credited corresponding to credited user.
     */
    @ManyToOne
    private User credited;

    /**
     * Attribute amount corresponding to amount transact.
     */
    private BigDecimal amount;

    /**
     * Attribute description corresponding to description.
     */
    private String description;

    /**
     * Attribute dateTime corresponding to date Time.
     */
    @PastOrPresent
    private LocalDateTime dateTime;

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
    public Transfer() {
        this.dateTime = LocalDateTime.now();
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
     * Getter of description.
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter of description.
     * @param descriptionS : new description.
     */
    public void setDescription(final String descriptionS) {
        this.description = descriptionS;
    }

    /**
     * Getter of dateTime.
     * @return dateTime.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Setter of dateTime.
     * @param dateTimeS : new dateTime.
     */
    public void setDateTime(final LocalDateTime dateTimeS) {
        this.dateTime = dateTimeS;
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
     * Equals method.
     * @param o : element to compare.
     * @return result of the comparison.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transfer)) {
            return false;
        }
        Transfer transfer = (Transfer) o;
        return getId().equals(transfer.getId());
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
