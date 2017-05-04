package com.emptyshit.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;
import com.emptyshit.hsay.playerComponent.Player;
import com.emptyshit.hsay.playerComponent.PlayerComponent;
import com.emptyshit.hsay.playerComponent.PlayerComponentInterface;
import com.emptyshit.hsay.playerComponent.PlayerRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerComponentTest {

    private PlayerRepository playerRepository = new PlayerRepository();
    private PlayerComponentInterface playerComponentInterface = new PlayerComponent(this.playerRepository);

    private String johnnyName = "johnny";
    private String johnnyEmail = "johnny@john.com";
    private String johnnyPassword = "123456";
    @Before
    public void constrcut(){
        playerComponentInterface.register(this.johnnyName, this.johnnyEmail, this.johnnyPassword, this.johnnyPassword);
    }

    @After
    public void destruct(){
        playerComponentInterface.delete();
    }

    @Test
    public void  withoutRegisterTest(){

    }

    @Test
    public void  loginTest(){

    }

    @Test
    public void  deleteTest(){

    }

    @Test
    public void  getUsernameTest(){

    }

    @Test
    public void  getEmailTest(){

    }
}
