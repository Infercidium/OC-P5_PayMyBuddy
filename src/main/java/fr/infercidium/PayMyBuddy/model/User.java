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
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Email(message = "A standard email address format is expected.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password cannot be null or empty.")
    private String password;

    @NotBlank(message = "Username cannot be null or empty.")
    private String userName;

    private BigDecimal pay;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Transfer> historyCredited;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Transfer> historyDebited;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_User")
    private Set<User> knowUser;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Role",
            joinColumns = @JoinColumn(name = "User_id"),
            inverseJoinColumns = @JoinColumn(name = "Authority_id"))
    private List<Authority> authorities;

    @OneToMany
    private List<BankAccount> bankAccounts;

    public User() {
        this.pay = BigDecimal.valueOf(0);
        this.historyCredited = new HashSet<>();
        this.historyDebited = new HashSet<>();
        this.knowUser = new HashSet<>();
        this.authorities = new ArrayList<>();
        this.bankAccounts = new ArrayList<>();
    }

    public User(final String email, final String password, final String userName, final BankAccount bankAccount) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.pay = BigDecimal.valueOf(0);
        this.historyCredited = new HashSet<>();
        this.historyDebited = new HashSet<>();
        this.knowUser = new HashSet<>();
        this.authorities = new ArrayList<>();
        this.bankAccounts = Collections.singletonList(bankAccount);
    }

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

    public Long getId() {
        return id;
    }

    public void setId(final Long idS) {
        this.id = idS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String emailS) {
        this.email = emailS;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String passwordS) {
        this.password = passwordS;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userNameS) {
        this.userName = userNameS;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(final BigDecimal payS) {
        this.pay = payS;
    }

    public Set<Transfer> getHistoryCredited() {
        return new HashSet<>(historyCredited);
    }

    public void setHistoryCredited(final Set<Transfer> historyCreditedS) {
        this.historyCredited = historyCreditedS;
    }

    public Set<Transfer> getHistoryDebited() {
        return new HashSet<>(historyDebited);
    }

    public void setHistoryDebited(final Set<Transfer> historyDebitedS) {
        this.historyDebited = historyDebitedS;
    }

    public Set<User> getKnowUser() {
        return new HashSet<>(knowUser);
    }

    public void setKnowUser(final Set<User> knowUserS) {
        this.knowUser = knowUserS;
    }

    public List<Authority> getAuthorities() {
        return new ArrayList<>(authorities);
    }

    public void setAuthorities(final List<Authority> authoritiesS) {
        this.authorities = authoritiesS;
    }

    public List<BankAccount> getBankAccounts() {
        return new ArrayList<>(bankAccounts);
    }

    public void setBankAccounts(final List<BankAccount> bankAccountsS) {
        this.bankAccounts = bankAccountsS;
    }

    public void addHistoryCredited(final Transfer transfer) {
        historyCredited.add(transfer);
    }

    public void addHistoryDebited(final Transfer transfer) {
        historyDebited.add(transfer);
    }

    public void addKnowUser(final User user) {
        knowUser.add(user);
    }

    public void removeKnowUser(final User user) {
        knowUser.remove(user);
    }

    public void addAuthority(final Authority authority) {
        authorities.add(authority);
    }

    public void removeAuthority(final Authority authority) {
        authorities.remove(authority);
    }

    public void addBankAccount(final BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    public void removeBankAccount(final BankAccount bankAccount) {
        bankAccounts.remove(bankAccount);
    }

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

    public String knowUserString() {
        StringBuilder knowUserString = new StringBuilder();
        for (User user : knowUser) {
            knowUserString.append(user.getUserName()).append(", ");
        }
        return "User : {" + knowUserString + "}";
    }

    public String bankAccountString() {
        StringBuilder bankAccountString = new StringBuilder();
        for (BankAccount bankAccount : bankAccounts) {
            bankAccountString.append(bankAccount.getName()).append(", ");
        }
        return "BankAccount : {" + bankAccountString + "}";
    }
}
