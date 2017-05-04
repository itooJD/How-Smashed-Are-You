package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

public class PlayerComponent implements PlayerComponentInterface {

    @Override
    public boolean createDummyPlayer() {
        return false;
    }

    @Override
    public boolean confirmPlayer(String playerName, String password) {
        return false;
    }

    @Override
    public boolean registerPlayer(String playerName, EmailType email, String password) {
        return false;
    }
}
