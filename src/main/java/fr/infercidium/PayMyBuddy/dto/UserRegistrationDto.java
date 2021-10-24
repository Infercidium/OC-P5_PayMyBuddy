package fr.infercidium.PayMyBuddy.dto;

public class UserRegistrationDto {

    /**
     * Attribute email corresponding to email user.
     */
    private String email;

    /**
     * Attribute password corresponding to password choice.
     */
    private String password;

    /**
     * Attribute password2 corresponding to password2 choice.
     */
    private String password2;

    /**
     * Attribute oldPassword corresponding to old Password.
     */
    private String oldPassword;

    /**
     * Attribute userName corresponding to userName choice.
     */
    private String userName;

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
     * Getter of password2.
     * @return password2.
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * Setter of password2.
     * @param password2S : new password2.
     */
    public void setPassword2(final String password2S) {
        this.password2 = password2S;
    }

    /**
     * Getter of oldPassword.
     * @return old Password.
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Setter of oldPasswordS.
     * @param oldPasswordS : new oldPassword.
     */
    public void setOldPassword(final String oldPasswordS) {
        this.oldPassword = oldPasswordS;
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
}
