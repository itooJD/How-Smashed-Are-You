package com.emptyshit.hsay.playerComponent;

import java.util.List;

/**
 * Created by tungu on 04/05/2017.
 */

public interface PlayerRepositoryInterface {

    List<Player> getAllPlayers();

    Player save(Player player);

    Player delete(long id);

    Player findPlayerById(long id);

    Player findPlayerByName(String username);

    Player findPlayerByEmail(String email);
}
