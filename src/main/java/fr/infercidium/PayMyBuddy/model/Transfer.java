package fr.infercidium.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinTable(name = "HistoryCredited")
    private User credited;

    private BigDecimal amount;

    private String description;

    @PastOrPresent
    private LocalDateTime dateTime;

    @ManyToOne(targetEntity = User.class)
    @JoinTable(name = "HistoryDebited")
    private User debited;

    public Transfer() {
    }

    public Transfer(final User creditedC,
                    final BigDecimal amountC,
                    final String descriptionC,
                    final User debitedC) {
        this.credited = creditedC;
        this.amount = amountC;
        this.description = descriptionC;
        this.dateTime = LocalDateTime.now();
        this.debited = debitedC;
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

    @Override
    public String toString() {
        return "Transfert{"
                + "id = " + id
                + ", credited = " + credited
                + ", amount = " + amount
                + ", description = '" + description + '\''
                + ", dateTime = " + dateTime
                + ", debited = " + debited
                + '}';
    }
}
