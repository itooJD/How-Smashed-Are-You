package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;
import org.greenrobot.greendao.annotation.*;

/**
 * Created by tungu on 09/04/2017.
 */


public class Player {

    private int playerID;

    private String playerName;
    private EmailType email;
    private String password;

    public Player(int id){
        this.playerID = id;
    }

    public Player(String playerName,EmailType email,String password){
        this.email = email;
        this.password = password;
        this.playerName = playerName;
    }

    public String setPlayerName(String playername){
        this.playerName = playername;
        return this.playerName;
    }

    public EmailType setEmail(EmailType email){
        this.email = email;
        return this.email;
    }

    public String setPassword(String password){
        this.password = password;
        return this.password;
    }

    public String getPlayerName(){
        return this.playerName;
    }

    public EmailType getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

}
