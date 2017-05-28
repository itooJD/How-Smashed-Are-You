package com.emptyshit.playerComponent;

import android.content.Context;

import com.emptyshit.hsay.dataTypes.EmailType;
import com.emptyshit.hsay.playerComponent.DaoMaster;
import com.emptyshit.hsay.playerComponent.DaoSession;
import com.emptyshit.hsay.playerComponent.Player;
import com.emptyshit.hsay.playerComponent.PlayerComponent;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;
import com.emptyshit.hsay.playerComponent.PlayerRepository;

import org.greenrobot.greendao.database.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
public class PlayerRepositoryTest {

    private Context context;
    private DaoSession daoSession;
    private PlayerRepository playerRepository;
    private PlayerComponentInterface playerComponentInterface;

    private Player john;
    private Player doe;
    private Player three;

    @Before
    public void construct(){

        this.context = RuntimeEnvironment.application;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this.context, "db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        playerRepository = new PlayerRepository(daoSession);
        playerComponentInterface = new PlayerComponent(this.playerRepository);

        this.john = new Player(new Long(1), "john", new EmailType("john@test.de"), "123456");
        this.doe = new Player(new Long(2), "doe", new EmailType("doe@test.de"), "123456");
        this.three = new Player(new Long(3), "three", new EmailType("three@test.de"), "123456");
        playerComponentInterface.register("john","john@test.de","123456","123456");
        playerComponentInterface.register("doe","doe@test.de","123456","123456");
        playerComponentInterface.register("three","three@test.de","123456","123456");
    }

    @After
    public void destruct(){
        playerComponentInterface.login("john", "123456");
        playerComponentInterface.delete();
        playerComponentInterface.login("doe", "123456");
        playerComponentInterface.delete();
        playerComponentInterface.login("three", "123456");
        playerComponentInterface.delete();
    }

    @Test
    public void getAllPlayersTest(){
        List<Player> allPlayers = playerComponentInterface.getAllPlayers();
        assertEquals("Number of Players", 3, allPlayers.size());
        for(Player player : allPlayers){
            System.out.println(player);
        }
        assertEquals("Existence of Player john", true, allPlayers.contains(this.john));
        assertEquals("Existence of Player doe", true, allPlayers.contains(this.doe));
        assertEquals("Existence of Player three", true, allPlayers.contains(this.three));
    }

    @Test
    public void getPlayerByIdTest(){
        assertEquals("", this.john, this.playerComponentInterface.getPlayerById(1));
        assertEquals("", this.doe, this.playerComponentInterface.getPlayerById(2));
        assertEquals("", this.three, this.playerComponentInterface.getPlayerById(3));
    }

    @Test
    public void findPlayerByNameTest(){
        assertEquals("", this.john, this.playerComponentInterface.getPlayerByName("john"));
        assertEquals("", this.doe, this.playerComponentInterface.getPlayerByName("doe"));
        assertEquals("", this.three, this.playerComponentInterface.getPlayerByName("three"));
    }

    @Test
    public void findPlayerByEmailTypeTest(){
        assertEquals("", this.john, this.playerComponentInterface.getPlayerByEmail("john@test.de"));
        assertEquals("", this.doe, this.playerComponentInterface.getPlayerByEmail("doe@test.de"));
        assertEquals("", this.three, this.playerComponentInterface.getPlayerByEmail("three@test.de"));
    }
}
