package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

public interface PlayerComponentInterface {
	
	boolean register(String playername, String email, String password, String passwordConfirm);

	boolean withoutRegister();
	
	boolean login(String playername, String password);
	
	boolean delete();
	
	String getUsername();
	
	EmailType getEmail();
}
