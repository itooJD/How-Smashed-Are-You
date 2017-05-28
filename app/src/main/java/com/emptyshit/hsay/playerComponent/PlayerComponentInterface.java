package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

import java.util.List;

public interface PlayerComponentInterface {
	
	boolean register(String playername, String email, String password, String passwordConfirm);

	boolean withoutRegister();
	
	boolean login(String playername, String password);
	
	boolean delete();

	long getMyId();

    boolean updateMyEmail(String email);

    boolean updatePassword(String password, String passwordConfirm);

	String getMyUsername();
	
	EmailType getMyEmail();

	Player getPlayerById(long id);

	Player getPlayerByName(String name);

	Player getPlayerByEmail(String email);

	List<Player> getAllPlayers();
}
