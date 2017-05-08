package com.emptyshit.playerComponent;

import android.content.Context;

import com.emptyshit.hsay.BuildConfig;
import com.emptyshit.hsay.application.App;
import com.emptyshit.hsay.dataTypes.EmailType;
import com.emptyshit.hsay.playerComponent.DaoMaster;
import com.emptyshit.hsay.playerComponent.DaoSession;
import com.emptyshit.hsay.playerComponent.Player;
import com.emptyshit.hsay.playerComponent.PlayerRepository;
import com.emptyshit.hsay.playerComponent.PlayerRepositoryInterface;

import org.greenrobot.greendao.database.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;

import java.util.List;

/**
 * Created by tungu on 04/05/2017.
 */

@RunWith(RobolectricTestRunner.class)
public class PlayerRepositoryTest {

    private PlayerRepositoryInterface playerRepositoryInterface;

    private Player john;
    private Player doe;
    private Player three;

    private Context context;
    private DaoSession daoSession;

    @Before
    public void construct(){

        this.context = RuntimeEnvironment.application;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this.context, "db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        playerRepositoryInterface = new PlayerRepository(daoSession);

        this.john = new Player(new Long(1), "john", new EmailType("john@test.de"), "123465");
        this.doe = new Player(new Long(2), "doe", new EmailType("doe@test.de"), "132456");
        this.three = new Player(new Long(3), "three", new EmailType("three@test.de"), "123456");
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
        List<Player> allPlayers = playerRepositoryInterface.getAllPlayers();
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
