package fr.infercidium.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "Bank_Account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "name cannot be null or empty.")
    private String name;

    @ManyToOne
    private User holder;

    @FutureOrPresent
    private LocalDate expirationDate;

    @NotBlank(message = "cardNumber cannot be null or empty.")
    @Column(unique = true)
    private String cardNumber;

    @NotBlank(message = "cryptogram cannot be null or empty.")
    private String cryptogram;

    @NotBlank(message = "iban cannot be null or empty.")
    private String iban;

    @NotBlank(message = "bic cannot be null or empty.")
    private String bic;

    public BankAccount() {
    }

    public BankAccount(final String nameC, final User holderC, final LocalDate expirationDateC, final String cardNumberC, final String cryptogramC, final String ibanC, final String bicC) {
        this.name = nameC;
        this.holder = holderC;
        this.expirationDate = expirationDateC;
        this.cardNumber = cardNumberC;
        this.cryptogram = cryptogramC;
        this.iban = ibanC;
        this.bic = bicC;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long idS) {
        this.id = idS;
    }

    public String getName() {
        return name;
    }

    public void setName(final String nameS) {
        this.name = nameS;
    }

    public User getHolder() {
        return new User(holder);
    }

    public void setHolder(final User holderS) {
        this.holder = holderS;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final LocalDate expirationDateS) {
        this.expirationDate = expirationDateS;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(final String cardNumberS) {
        this.cardNumber = cardNumberS;
    }

    public String getCryptogram() {
        return cryptogram;
    }

    public void setCryptogram(final String cryptogramS) {
        this.cryptogram = cryptogramS;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(final String ibanS) {
        this.iban = ibanS;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(final String bicS) {
        this.bic = bicS;
    }

    @Override
    public String toString() {
        return "BankAccount{"
                + "id = " + id
                + ", name = '" + name + '\''
                + ", holder = " + holder.getUserName()
                + ", expirationDate = " + expirationDate
                + ", cardNumber = '" + cardNumber + '\''
                + ", cryptogram = '" + cryptogram + '\''
                + ", iban = '" + iban + '\''
                + ", bic = '" + bic + '\''
                + '}';
    }
}
