package fr.infercidium.PayMyBuddy.dto;

public class BankAccountDto {

    /**
     * Attribute name corresponding to name choice.
     */
    private String name;

    /**
     * Attribute holder corresponding to holder of account.
     */
    private String holder;

    /**
     * Attribute expirationDate corresponding to expiration Date of card.
     */
    private String expirationDate;

    /**
     * Attribute cardNumber corresponding to card Number.
     */
    private String cardNumber;

    /**
     * Attribute cryptogram corresponding to cryptogram card.
     */
    private String cryptogram;

    /**
     * Attribute iban corresponding to iban account of card.
     */
    private String iban;

    /**
     * Attribute bic corresponding to bic account of card.
     */
    private String bic;

    /**
     * Attribute password corresponding to password choice.
     */
    private String password;

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
     * Getter of expiration Date.
     * @return expiration Date.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Setter of expiration Date.
     * @param expirationDateS : new expiration Date.
     */
    public void setExpirationDate(final String expirationDateS) {
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
     * @param bicS : new Bic.
     */
    public void setBic(final String bicS) {
        this.bic = bicS;
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
}
