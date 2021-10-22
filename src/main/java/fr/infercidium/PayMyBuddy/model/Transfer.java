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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    private User credited;

    private BigDecimal amount;

    private String description;

    @PastOrPresent
    private LocalDateTime dateTime;

    @ManyToOne
    private User debited;

    @ManyToOne
    private BankAccount bankAccount;

    public Transfer() {
        this.dateTime = LocalDateTime.now();
    }

    public Transfer(final User creditedC,
                    final BigDecimal amountC,
                    final String descriptionC,
                    final User debitedC,
                    final BankAccount bankAccountC) {
        this.credited = creditedC;
        this.amount = amountC;
        this.description = descriptionC;
        this.dateTime = LocalDateTime.now();
        this.debited = debitedC;
        this.bankAccount = bankAccountC;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long idS) {
        this.id = idS;
    }

    public User getCredited() {
        return new User(credited);
    }

    public void setCredited(final User creditedS) {
        this.credited = creditedS;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amountS) {
        this.amount = amountS;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String descriptionS) {
        this.description = descriptionS;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(final LocalDateTime dateTimeS) {
        this.dateTime = dateTimeS;
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

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
