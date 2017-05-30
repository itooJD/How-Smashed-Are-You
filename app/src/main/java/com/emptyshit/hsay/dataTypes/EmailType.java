package com.emptyshit.hsay.dataTypes;

import java.util.regex.Pattern;

/**
 * Representation of the Email
 */
public class EmailType {
    //Reg Ex
    private static final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{1,6}$";

    private String email;

    public EmailType(String email){
        if(!checkEmail(email) || email == null){
            throw new IllegalArgumentException("invalid email!");
        }
        this.email = email;
    }

    private boolean checkEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN,email);
    }

    public String getEmail(){
        return new String(this.email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailType emailType = (EmailType) o;

        return email != null ? email.equals(emailType.email) : emailType.email == null;

    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public String toString(){
        return new String(this.email);
    }
}
