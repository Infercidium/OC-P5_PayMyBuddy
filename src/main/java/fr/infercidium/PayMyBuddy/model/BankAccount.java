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

    /**
     * Attribute id corresponding to id generate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    /**
     * Attribute name corresponding to name choice.
     */
    @NotBlank(message = "name cannot be null or empty.")
    private String name;

    /**
     * Attribute holder corresponding to holder card.
     */
    @NotBlank(message = "holder cannot be null or empty.")
    private String holder;

    /**
     * Attribute user corresponding to user create.
     */
    @ManyToOne
    private User user;

    /**
     * Attribute expirationDate corresponding to expiration Date card.
     */
    @FutureOrPresent
    private LocalDate expirationDate;

    /**
     * Attribute cardNumber corresponding to card Number.
     */
    @NotBlank(message = "cardNumber cannot be null or empty.")
    @Column(unique = true)
    private String cardNumber;

    /**
     * Attribute cryptogram corresponding to cryptogram card.
     */
    @NotBlank(message = "cryptogram cannot be null or empty.")
    private String cryptogram;

    /**
     * Attribute iban corresponding to iban account.
     */
    @NotBlank(message = "iban cannot be null or empty.")
    private String iban;

    /**
     * Attribute bic corresponding to bic account.
     */
    @NotBlank(message = "bic cannot be null or empty.")
    private String bic;

    /**
     * Attribute deer corresponding to a nice deer.
     */
    //2 last card number
    @NotBlank(message = "deer cannot be null or empty.")
    private String deer;

    /**
     * Basic builder.
     */
    public BankAccount() {
    }

    /**
     * Complete builder.
     * @param nameC attribute.
     * @param holderC attribute.
     * @param expirationDateC attribute.
     * @param cardNumberC attribute.
     * @param cryptogramC attribute.
     * @param ibanC attribute.
     * @param bicC attribute.
     * @param deerC attribute.
     */
    public BankAccount(final String nameC, final String holderC,
                       final LocalDate expirationDateC,
                       final String cardNumberC,
                       final String cryptogramC, final String ibanC,
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

    /**
     * Copy constructor.
     * @param bankAccountC to copy.
     */
    public BankAccount(final BankAccount bankAccountC) {
        this.name = bankAccountC.getName();
        this.holder = bankAccountC.getHolder();
        this.expirationDate = bankAccountC.getExpirationDate();
        this.cardNumber = bankAccountC.getCardNumber();
        this.cryptogram = bankAccountC.getCryptogram();
        this.iban = bankAccountC.getIban();
        this.bic = bankAccountC.getBic();
        this.deer = bankAccountC.getDeer();
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
     * Getter of name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name.
     * @param nameS : new name.
     */
    public void setName(final String nameS) {
        this.name = nameS;
    }

    /**
     * Getter of holder.
     * @return holder.
     */
    public String getHolder() {
        return holder;
    }

    /**
     * Setter of holder.
     * @param holderS : new holder.
     */
    public void setHolder(final String holderS) {
        this.holder = holderS;
    }

    /**
     * Getter of user.
     * @return user.
     */
    public User getUser() {
        return new User(user);
    }

    /**
     * Setter of user.
     * @param userS : new user.
     */
    public void setUser(final User userS) {
        this.user = userS;
    }

    /**
     * Getter of expirationDate.
     * @return expirationDate.
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Setter of expirationDate.
     * @param expirationDateS : new expirationDate.
     */
    public void setExpirationDate(final LocalDate expirationDateS) {
        this.expirationDate = expirationDateS;
    }

    /**
     * Getter of cardNumber.
     * @return cardNumber.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Setter of cardNumber.
     * @param cardNumberS : new cardNumber.
     */
    public void setCardNumber(final String cardNumberS) {
        this.cardNumber = cardNumberS;
    }

    /**
     * Getter of cryptogram.
     * @return cryptogram.
     */
    public String getCryptogram() {
        return cryptogram;
    }

    /**
     * Setter of cryptogram.
     * @param cryptogramS : new cryptogram.
     */
    public void setCryptogram(final String cryptogramS) {
        this.cryptogram = cryptogramS;
    }

    /**
     * Getter of iban.
     * @return iban.
     */
    public String getIban() {
        return iban;
    }

    /**
     * Setter of iban.
     * @param ibanS : new iban.
     */
    public void setIban(final String ibanS) {
        this.iban = ibanS;
    }

    /**
     * Getter of bic.
     * @return bic.
     */
    public String getBic() {
        return bic;
    }

    /**
     * Setter of bic.
     * @param bicS : new bic.
     */
    public void setBic(final String bicS) {
        this.bic = bicS;
    }

    /**
     * Getter of deer.
     * @return deer.
     */
    public String getDeer() {
        return deer;
    }

    /**
     * Setter of deer.
     * @param deerS : new deer.
     */
    public void setDeer(final String deerS) {
        this.deer = deerS;
    }

    /**
     * ToString method.
     * @return toString.
     */
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
        if (!(o instanceof BankAccount)) {
            return false;
        }
        BankAccount that = (BankAccount) o;
        return getId().equals(that.getId());
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
