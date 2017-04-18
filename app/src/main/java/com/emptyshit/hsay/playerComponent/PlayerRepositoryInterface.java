package com.emptyshit.hsay.playerComponent;
import com.emptyshit.hsay.dataTypes.EmailType;


/**
 * Created by tungu on 18/04/2017.
 */

public interface PlayerRepositoryInterface {

    public Player findPlayerById(int id);

    public Player findPlayerByUserName(String username);

    public Player findPlayerByEmailType(EmailType email);
}
