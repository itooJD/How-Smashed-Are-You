package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;
import com.emptyshit.hsay.dataTypes.EmailTypeConverter;
import java.lang.Object;

import org.greenrobot.greendao.annotation.*;


/**
 * Representation of a Player
 */
@Entity
public class Player {

    @Id
    private long playerID;

    private String playerName;

    @Convert(converter = EmailTypeConverter.class, columnType = String.class)
    private EmailType email;

    private String password;

    @Generated(hash = 30709322)
    Player() {
    }

  @Generated(hash = 822864597)
    Player(long playerID, String playerName, EmailType email, String password) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.email = email;
        this.password = password;
    }

    void setPlayerID(long playerID) {
        this.playerID = playerID;
    }

    String setPlayerName(String playername){
        this.playerName = playername;
        return this.playerName;
    }

    EmailType setEmail(EmailType email){
        this.email = email;
        return this.email;
    }

    String setPassword(String password){
        this.password = password;
        return this.password;
    }

    long getPlayerID() {
        return this.playerID;
    }

    String getPlayerName(){
        return this.playerName;
    }

    EmailType getEmail(){
        return this.email;
    }

    String getPassword(){
        return this.password;
    }
    
    boolean comparePassword(String password){
    	return this.password.equals(password);
    }

    @Override
    boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (playerID != player.playerID) return false;
        if (playerName != null ? !playerName.equals(player.playerName) : player.playerName != null)
            return false;
        if (email != null ? !email.equals(player.email) : player.email != null) return false;
        return password != null ? password.equals(player.password) : player.password == null;

    }

    @Override
    int hashCode() {
        int result = (int) (playerID ^ (playerID >>> 32));
        result = 31 * result + (playerName != null ? playerName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
