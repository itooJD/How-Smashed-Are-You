package com.emptyshit.hsay.dataTypes;

import java.util.regex.Pattern;

/**
 * Representation of the Email
 */
public class EmailType {
    //Reg Ex
    private static final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

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
    public String toString(){
        return new String(this.email);
    }
}
