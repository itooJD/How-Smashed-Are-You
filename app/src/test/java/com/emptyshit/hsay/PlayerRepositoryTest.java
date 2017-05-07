package com.emptyshit.hsay;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.emptyshit.hsay.dataTypes.EmailType;
import com.emptyshit.hsay.playerComponent.*;

import java.util.List;


/**
 * Created by Alex on 30.04.2017.
 */

public class PlayerRepositoryTest {

    private PlayerRepository playerRepository;
    private List<Player> players;

    @Before
    public void setup(){
        Player josh = new Player(111);
        EmailType emailJosh = new EmailType("josh@mail.com");
        josh.setEmail(emailJosh);
        josh.setPlayerName("theLegend27");
        players.add(josh);

        Player ted = new Player(222);
        EmailType emailTed = new EmailType("ted@mail.com");
        josh.setEmail(emailTed);
        josh.setPlayerName("Ted");
        players.add(ted);
    }

    @Test
    public void testFindPlayerById(){
        Player player = playerRepository.findPlayerById(111);
        assertTrue(player == players.get(1));
        assertEquals(111, player.getPlayerID());
    }

    @Test
    public void testFindPlayerByUserName(){
        Player player = playerRepository.findPlayerByUserName("theLegend27");
        assertTrue(player == players.get(1));
        assertTrue(player.getPlayerName().equals("theLegend27"));
    }

    @Test
    public void testFindPlayerByEmailType(){
        Player player = playerRepository.findPlayerByUserName("josh@mail.com");
        assertTrue(player == players.get(1));
        assertTrue(player.getEmail().equals("josh@mail.com"));
    }
}
