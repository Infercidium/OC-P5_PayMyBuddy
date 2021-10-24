package fr.infercidium.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

    /**
     * Attribute id corresponding to id generate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    /**
     * Attribute email corresponding to email choice.
     */
    @Email(message = "A standard email address format is expected.")
    @Column(unique = true)
    private String email;

    /**
     * Attribute password corresponding to password choice.
     */
    @NotBlank(message = "Password cannot be null or empty.")
    private String password;

    /**
     * Attribute userName corresponding to userName choice.
     */
    @NotBlank(message = "Username cannot be null or empty.")
    private String userName;

    /**
     * Attribute pay corresponding to money on account.
     */
    private BigDecimal pay;

    /**
     * Attribute historyCredited corresponding to transfer history.
     */
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Transfer> historyCredited;

    /**
     * Attribute historyDebited corresponding to transfer history.
     */
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Transfer> historyDebited;

    /**
     * Attribute knowUser corresponding to others users added.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_User")
    private Set<User> knowUser;

    /**
     * Attribute authorities corresponding to roles of application.
     */
    @ManyToMany
    @JoinTable(name = "User_Role",
            joinColumns = @JoinColumn(name = "User_id"),
            inverseJoinColumns = @JoinColumn(name = "Authority_id"))
    private List<Authority> authorities;

    /**
     * Attribute bankAccounts corresponding to bankAccounts user.
     */
    @OneToMany(fetch = FetchType.EAGER)
    private List<BankAccount> bankAccounts;

    /**
     * Basic builder.
     */
    public User() {
        this.pay = BigDecimal.valueOf(0);
        this.historyCredited = new HashSet<>();
        this.historyDebited = new HashSet<>();
        this.knowUser = new HashSet<>();
        this.authorities = new ArrayList<>();
        this.bankAccounts = new ArrayList<>();
    }

    /**
     * Complete builder.
     * @param emailC attribute.
     * @param passwordC attribute.
     * @param userNameC attribute.
     * @param bankAccountC attribute.
     */
    public User(final String emailC,
                final String passwordC,
                final String userNameC,
                final BankAccount bankAccountC) {
        this.email = emailC;
        this.password = passwordC;
        this.userName = userNameC;
        this.pay = BigDecimal.valueOf(0);
        this.historyCredited = new HashSet<>();
        this.historyDebited = new HashSet<>();
        this.knowUser = new HashSet<>();
        this.authorities = new ArrayList<>();
        this.bankAccounts = Collections.singletonList(bankAccountC);
    }

    /**
     * Copy constructor.
     * @param user to copy.
     */
    public User(final User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userName = user.getUserName();
        this.pay = user.getPay();
        this.historyCredited = user.getHistoryCredited();
        this.historyDebited = user.getHistoryDebited();
        this.knowUser = user.getKnowUser();
        this.authorities = user.getAuthorities();
        this.bankAccounts = user.getBankAccounts();
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
     * Getter of email.
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter of email.
     * @param emailS : new email.
     */
    public void setEmail(final String emailS) {
        this.email = emailS;
    }

    /**
     * Getter of password.
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of password.
     * @param passwordS : new password.
     */
    public void setPassword(final String passwordS) {
        this.password = passwordS;
    }

    /**
     * Getter of userName.
     * @return userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter of userName.
     * @param userNameS : new userName.
     */
    public void setUserName(final String userNameS) {
        this.userName = userNameS;
    }

    /**
     * Getter of pay.
     * @return pay.
     */
    public BigDecimal getPay() {
        return pay;
    }

    /**
     * Setter of pay.
     * @param payS : new pay.
     */
    public void setPay(final BigDecimal payS) {
        this.pay = payS;
    }

    /**
     * Getter of historyCredited.
     * @return historyCredited.
     */
    public Set<Transfer> getHistoryCredited() {
        return new HashSet<>(historyCredited);
    }

    /**
     * Setter of historyCredited.
     * @param historyCreditedS : new historyCredited.
     */
    public void setHistoryCredited(final Set<Transfer> historyCreditedS) {
        this.historyCredited = historyCreditedS;
    }

    /**
     * Getter of historyDebited.
     * @return historyDebited.
     */
    public Set<Transfer> getHistoryDebited() {
        return new HashSet<>(historyDebited);
    }

    /**
     * Setter of historyDebited.
     * @param historyDebitedS : new historyDebited.
     */
    public void setHistoryDebited(final Set<Transfer> historyDebitedS) {
        this.historyDebited = historyDebitedS;
    }

    /**
     * Getter of knowUser.
     * @return knowUser.
     */
    public Set<User> getKnowUser() {
        return new HashSet<>(knowUser);
    }

    /**
     * Setter of knowUser.
     * @param knowUserS : new knowUser.
     */
    public void setKnowUser(final Set<User> knowUserS) {
        this.knowUser = knowUserS;
    }

    /**
     * Getter of authorities.
     * @return authorities.
     */
    public List<Authority> getAuthorities() {
        return new ArrayList<>(authorities);
    }

    /**
     * Setter of authorities.
     * @param authoritiesS : new authorities.
     */
    public void setAuthorities(final List<Authority> authoritiesS) {
        this.authorities = authoritiesS;
    }

    /**
     * Getter of bankAccounts.
     * @return bankAccounts.
     */
    public List<BankAccount> getBankAccounts() {
        return new ArrayList<>(bankAccounts);
    }

    /**
     * Setter of bankAccounts.
     * @param bankAccountsS : new bankAccounts.
     */
    public void setBankAccounts(final List<BankAccount> bankAccountsS) {
        this.bankAccounts = bankAccountsS;
    }

    /**
     * Adding an element.
     * @param transfer added.
     */
    public void addHistoryCredited(final Transfer transfer) {
        historyCredited.add(transfer);
    }

    /**
     * Adding an element.
     * @param transfer added.
     */
    public void addHistoryDebited(final Transfer transfer) {
        historyDebited.add(transfer);
    }

    /**
     * Adding an element.
     * @param user added.
     */
    public void addKnowUser(final User user) {
        knowUser.add(user);
    }

    /**
     * Delete an element.
     * @param user removed.
     */
    public void removeKnowUser(final User user) {
        knowUser.remove(user);
    }

    /**
     * Adding an element.
     * @param authority added.
     */
    public void addAuthority(final Authority authority) {
        authorities.add(authority);
    }

    /**
     * Delete an element.
     * @param authority removed.
     */
    public void removeAuthority(final Authority authority) {
        authorities.remove(authority);
    }

    /**
     * Adding an element.
     * @param bankAccount added.
     */
    public void addBankAccount(final BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    /**
     * Delete an element.
     * @param bankAccount removed.
     */
    public void removeBankAccount(final BankAccount bankAccount) {
        bankAccounts.remove(bankAccount);
    }

    /**
     * ToString method.
     * @return toString.
     */
    @Override
    public String toString() {
        return "User{"
                + "id = " + id
                + ", email = '" + email + '\''
                + ", password = '" + password + '\''
                + ", userName = '" + userName + '\''
                + ", pay = " + pay
                + ", historyCredited = " + historyCredited
                + ", historyDebited = " + historyDebited
                + ", knowUser = " + this.knowUserString()
                + ", authorities = " + authorities
                + ", bankAccount = " + this.bankAccountString()
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
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return getId().equals(user.getId());
    }

    /**
     * HashCode method.
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Anti curl method.
     * @return attribute with only one of these attributes.
     */
    public String knowUserString() {
        StringBuilder knowUserString = new StringBuilder();
        for (User user : knowUser) {
            knowUserString.append(user.getUserName()).append(", ");
        }
        return "User : {" + knowUserString + "}";
    }

    /**
     * Anti curl method.
     * @return attribute with only one of these attributes.
     */
    public String bankAccountString() {
        StringBuilder bankAccountString = new StringBuilder();
        for (BankAccount bankAccount : bankAccounts) {
            bankAccountString.append(bankAccount.getName()).append(", ");
        }
        return "BankAccount : {" + bankAccountString + "}";
    }
}
