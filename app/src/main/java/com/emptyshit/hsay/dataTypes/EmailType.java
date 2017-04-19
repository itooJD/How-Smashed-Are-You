package com.emptyshit.hsay.dataTypes;

import java.util.regex.Pattern;

/**
 * Created by huynh_phuong_nguyen on 13.04.17.
 */

public class EmailType {
    //Reg Ex
    private static final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    private String email;

    public EmailType(String email){
        if(!checkEmail(email)){
            throw new IllegalArgumentException("invalid email!");
        }
        this.email = email;
    }

    private boolean checkEmail(String email) {
        return Pattern.matches(EMAIL_PATTERN,email);
    }

    @Override
    public String toString(){
        return this.email;
    }
}
