package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;
import com.emptyshit.hsay.dataTypes.EmailTypeConverter;

import java.io.Serializable;
import java.lang.Object;
import org.greenrobot.greendao.annotation.*;

@Entity
public class Player implements Serializable{

    @Id
    @Generated
    private Long playerId;

    @Transient
    private static final long serialVersionUID = 1L;

    @Property
    private String playerName;

    @Convert(converter = EmailTypeConverter.class, columnType = String.class)
    private EmailType email;

    @Property
    private String password;

    @Generated(hash = 1860436718)
    public Player(Long playerId, String playerName, EmailType email,
            String password) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.email = email;
        this.password = password;
    }

    @Generated(hash = 30709322)
    public Player() {
    }

    void setPlayerID(Long playerId) {
        this.playerId = playerId;
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

    Long getPlayerID() {
        return this.playerId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;
        if (!playerName.equals(player.playerName)) return false;
        if (!email.equals(player.email)) return false;
        return password.equals(player.password);

    }

    @Override
    public int hashCode() {
        int result = playerName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    public Long getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}