package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

public interface PlayerComponentInterface {
	
	public boolean register(String playername, EmailType email, String password, String passwordConfirm);

	public boolean withoutRegister();
	
	public boolean login(String playername, String password);
	
	public boolean delete();
	
	public String getUsername();
	
	public String getEmail();
}
