package fr.infercidium.PayMyBuddy.dto;

public class UserRegistrationDto {

    private String email;
    private String password;
    private String password2;
    private String oldPassword;
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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userNameS) {
        this.userName = userNameS;
    }
}
