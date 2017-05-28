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
        this.daoSession = new DaoMaster(db).newSession();
        this.playerRepository = new PlayerRepository(this.daoSession);
        this.playerComponentInterface = new PlayerComponent(this.playerRepository);

        this.john = new Player(new Long(1), "john", new EmailType("john@test.de"), "123456");
        this.doe = new Player(new Long(2), "doe", new EmailType("doe@test.de"), "123456");
        this.three = new Player(new Long(3), "three", new EmailType("three@test.de"), "123456");
        this.playerComponentInterface.register("john","john@test.de","123456","123456");
        this.playerComponentInterface.register("doe","doe@test.de","123456","123456");
        this.playerComponentInterface.register("three","three@test.de","123456","123456");
    }

    @After
    public void destruct(){
        this.playerComponentInterface.login("john", "123456");
        this.playerComponentInterface.delete();
        this.playerComponentInterface.login("doe", "123456");
        this.playerComponentInterface.delete();
        this.playerComponentInterface.login("three", "123456");
        this.playerComponentInterface.delete();
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
        assertEquals("find john by Id", this.john, this.playerComponentInterface.getPlayerById(1));
        assertEquals("find doe by Id", this.doe, this.playerComponentInterface.getPlayerById(2));
        assertEquals("find three by Id", this.three, this.playerComponentInterface.getPlayerById(3));
    }

    @Test
    public void findPlayerByNameTest(){
        assertEquals("find john by name", this.john, this.playerComponentInterface.getPlayerByName("john"));
        assertEquals("find doe by name", this.doe, this.playerComponentInterface.getPlayerByName("doe"));
        assertEquals("find three by name", this.three, this.playerComponentInterface.getPlayerByName("three"));
    }

    @Test
    public void findPlayerByEmailTypeTest(){
        assertEquals("find john by EMail", this.john, this.playerComponentInterface.getPlayerByEmail("john@test.de"));
        assertEquals("find doe by EMail", this.doe, this.playerComponentInterface.getPlayerByEmail("doe@test.de"));
        assertEquals("find three by EMail", this.three, this.playerComponentInterface.getPlayerByEmail("three@test.de"));
    }

    @Test
    public void updateEmailTest(){
        this.playerComponentInterface.login("doe", "123456");
        assertEquals("update EMail of doe with invalid Email", false, this.playerComponentInterface.updateMyEmail("keineEmail"));
        assertEquals("find doe by old EMail", this.doe, this.playerComponentInterface.getPlayerByEmail("doe@test.de"));

        assertEquals("update EMail of doe with valid Email", true, this.playerComponentInterface.updateMyEmail("nichtDoe@test.de"));
        Player nichtDoe = new Player(new Long(2), "doe", new EmailType("nichtDoe@test.de"), "123456");
        assertEquals("find nichtDoe by new EMail", nichtDoe, this.playerComponentInterface.getPlayerByEmail("nichtDoe@test.de"));
    }

    @Test
    public void updatePasswordTest(){
        this.playerComponentInterface.login("doe", "123456");
        assertEquals("update password of doe with invalid password confirmation", false, this.playerComponentInterface.updatePassword("neuewPassword", "falschesPassword"));
        assertEquals("log in doe with old password", true, this.playerComponentInterface.login("doe", "123456"));
        assertEquals("log in doe with invalid new password", false, this.playerComponentInterface.login("doe", "neuesPassword"));

        assertEquals("update password with valid inputs", true, this.playerComponentInterface.updatePassword("neuesPassword", "neuesPassword"));
        assertEquals("log in doe with invalid old password", false, this.playerComponentInterface.login("doe", "123456"));
        assertEquals("log in doe with new password", true, this.playerComponentInterface.login("doe", "neuesPassword"));
    }
}
