package fr.infercidium.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Bank_Account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "name cannot be null or empty.")
    private String name;

    @NotBlank(message = "holder cannot be null or empty.")
    private String holder;

    @ManyToOne
    private User user;

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

    //2 last card number
    @NotBlank(message = "deer cannot be null or empty.")
    private String deer;

    public BankAccount() {

    }

    public BankAccount(final String nameC, final String holderC, final LocalDate expirationDateC,
                       final String cardNumberC, final String cryptogramC, final String ibanC,
                       final String bicC, final String deerC) {
        this.name = nameC;
        this.holder = holderC;
        this.expirationDate = expirationDateC;
        this.cardNumber = cardNumberC;
        this.cryptogram = cryptogramC;
        this.iban = ibanC;
        this.bic = bicC;
        this.deer = deerC;
    }

    public BankAccount(BankAccount bankAccountC) {
        this.name = bankAccountC.getName();
        this.holder = bankAccountC.getHolder();
        this.expirationDate = bankAccountC.getExpirationDate();
        this.cardNumber = bankAccountC.getCardNumber();
        this.cryptogram = bankAccountC.getCryptogram();
        this.iban = bankAccountC.getIban();
        this.bic = bankAccountC.getBic();
        this.deer = bankAccountC.getDeer();
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

    public String getHolder() {
        return holder;
    }

    public void setHolder(final String holderS) {
        this.holder = holderS;
    }

    public User getUser() {
        return new User(user);
    }

    public void setUser(User userS) {
        this.user = userS;
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

    public String getDeer() {
        return deer;
    }

    public void setDeer(String deer) {
        this.deer = deer;
    }

    @Override
    public String toString() {
        return "BankAccount{"
                + "id = " + id
                + ", name = '" + name + '\''
                + ", holder = '" + holder + '\''
                + ", user = " + user.getUserName() + '\''
                + ", expirationDate = " + expirationDate
                + ", cardNumber = '" + cardNumber + '\''
                + ", cryptogram = '" + cryptogram + '\''
                + ", iban = '" + iban + '\''
                + ", bic = '" + bic + '\''
                + ", deer = '" + deer + '\''
                + '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankAccount)) {
            return false;
        }
        BankAccount that = (BankAccount) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
