package com.emptyshit.playerComponent;



import com.emptyshit.hsay.dataTypes.EmailType;
import com.emptyshit.hsay.playerComponent.Player;
import com.emptyshit.hsay.playerComponent.PlayerRepository;
import com.emptyshit.hsay.playerComponent.PlayerRepositoryInterface;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by tungu on 04/05/2017.
 */

public class PlayerRepositoryTest {

    private PlayerRepositoryInterface playerRepositoryInterface = new PlayerRepository();
    private Player john;
    private Player doe;
    private Player three;

    @Before
    public void construct(){
        this.john = new Player(1, "john", new EmailType("john@test.de"), "123465");
        this.doe = new Player(2, "doe", new EmailType("doe@test.de"), "132456");
        this.three = new Player(3, "three", new EmailType("three@test.de"), "123456");
        playerRepositoryInterface.save(this.john);
        playerRepositoryInterface.save(this.doe);
        playerRepositoryInterface.save(this.three);
    }

    @After
    public void destruct(){
        playerRepositoryInterface.delete(1);
        playerRepositoryInterface.delete(2);
        playerRepositoryInterface.delete(3);
    }

    @Test
    public void getAllPlayersTest(){
        ArrayList<Player> allPlayers = playerRepositoryInterface.getAllPlayers();
        assertEquals("Number of Players", 3, allPlayers.size());
        assertEquals("Existence of Player john", true, allPlayers.contains(this.john));
        assertEquals("Existence of Player doe", true, allPlayers.contains(this.doe));
        assertEquals("Existence of Player three", true, allPlayers.contains(this.three));
    }

    @Test
    public void findPlayerByIdTest(){
        assertEquals("", this.john, this.playerRepositoryInterface.findPlayerById(1));
        assertEquals("", this.doe, this.playerRepositoryInterface.findPlayerById(2));
        assertEquals("", this.three, this.playerRepositoryInterface.findPlayerById(3));
    }

    @Test
    public void findPlayerByNameTest(){
        assertEquals("", this.john, this.playerRepositoryInterface.findPlayerByName("john"));
        assertEquals("", this.doe, this.playerRepositoryInterface.findPlayerByName("doe"));
        assertEquals("", this.three, this.playerRepositoryInterface.findPlayerByName("three"));
    }

    @Test
    public void findPlayerByEmailTypeTest(){
        assertEquals("", this.john, this.playerRepositoryInterface.findPlayerByEmail("john@test.de"));
        assertEquals("", this.doe, this.playerRepositoryInterface.findPlayerByEmail("doe@test.de"));
        assertEquals("", this.three, this.playerRepositoryInterface.findPlayerByEmail("three@test.de"));
    }
}
