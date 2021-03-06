package com.emptyshit.playerComponent;

import android.content.Context;

import com.emptyshit.hsay.dataTypes.EmailType;
import com.emptyshit.hsay.playerComponent.DaoMaster;
import com.emptyshit.hsay.playerComponent.DaoSession;
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

@RunWith(RobolectricTestRunner.class)
public class PlayerComponentTest {

    private Context context;
    private DaoSession daoSession;
    private PlayerRepository playerRepository;
    private PlayerComponentInterface playerComponentInterface;

    private String johnnyName = "johnny";
    private String johnnyEmail = "johnny@john.com";
    private String johnnyPassword = "123456";
    @Before
    public void constrcut(){
        this.context = RuntimeEnvironment.application;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this.context, "db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        
        playerRepository = new PlayerRepository(daoSession);
        playerComponentInterface = new PlayerComponent(this.playerRepository, this.context);

        playerComponentInterface.register(this.johnnyName, this.johnnyEmail, this.johnnyPassword, this.johnnyPassword);
    }

    @After
    public void destruct(){
        playerComponentInterface.delete();
    }

    @Test
    public void  withoutRegisterTest(){
        playerComponentInterface.delete();
        assertEquals("playersize = 0", 0, playerComponentInterface.getAllPlayers().size());
        playerComponentInterface.withoutRegister();
        assertEquals("playersize = 0", 0, playerComponentInterface.getAllPlayers().size());
        this.playerComponentInterface.delete();
    }

    @Test
    public void  loginTest(){
        assertEquals("Login with the right information", true, playerComponentInterface.login(this.johnnyName, this.johnnyPassword));
        assertEquals("Login with the wrong information", false, playerComponentInterface.login("abc", "abc"));
    }

    @Test
    public void  registerAndDeleteTest(){
        assertNotNull("John should be present",playerComponentInterface.getPlayerByName(this.johnnyName));
        playerComponentInterface.delete();
        assertNull("John should me deleted",playerComponentInterface.getPlayerByName(this.johnnyName));
    }

    @Test
    public void  getUsernameTest(){
        assertEquals("" ,"johnny" ,playerComponentInterface.getMyUsername());
    }

    @Test
    public void  getEmailTest(){
        assertEquals("",new EmailType(this.johnnyEmail), playerComponentInterface.getMyEmail());
    }

    @Test
    public void loggedInTest(){
        assertEquals("",true, this.playerComponentInterface.loggedIn());
        this.playerComponentInterface.delete();
        this.playerComponentInterface.withoutRegister();
        assertEquals("",false, this.playerComponentInterface.loggedIn());
    }
}
