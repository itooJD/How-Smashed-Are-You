package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

public interface PlayerComponentInterface {

    public boolean createDummyPlayer();

    public boolean confirmPlayer(String playerName, String password);

    public boolean registerPlayer(String playerName, EmailType email, String password);
}
