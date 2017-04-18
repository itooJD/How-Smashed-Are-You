package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

/**
 * Created by tungu on 18/04/2017.
 */

public class PlayerRepository implements PlayerRepositoryInterface{

    private PlayerDao n = null;

    @Override
    public Player findPlayerById(int id) {
        return null;
    }

    @Override
    public Player findPlayerByUserName(String username) {
        return null;
    }

    @Override
    public Player findPlayerByEmailType(EmailType email) {
        return null;
    }
}
