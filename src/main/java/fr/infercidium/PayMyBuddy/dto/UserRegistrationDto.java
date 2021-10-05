package fr.infercidium.PayMyBuddy.dto;

public class UserRegistrationDto {

    private String email;
    private String password;
    private String userName;

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
}
