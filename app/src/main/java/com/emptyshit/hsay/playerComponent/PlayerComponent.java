package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

public class PlayerComponent implements PlayerComponentInterface {

	PlayerRepository playerRepository;

	public PlayerComponent(PlayerRepository playerRepository){
		this.playerRepository = playerRepository;
	}

	@Override
	public boolean register(String playername, String emailString, String password, String passwordConfirm) {
		if (!(checkValidString(playername) || checkValidString(emailString) || checkValidString(password)
				|| checkValidString(passwordConfirm))) {
			return false;
		}

		if (password.equals(passwordConfirm)) {
			EmailType email = new EmailType(emailString);
			Player player = new Player();
			player.setPlayerName(playername);
			player.setEmail(email);
			player.setPassword(password);
			saveLocalPlayer(player);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean withoutRegister() {
		if(getLocalPlayer() == null){
			Player player = new Player();
			return true;
		}
		return false;
	}

	@Override
	public boolean login(String playername, String password) {
		Player player = playerRepository.findPlayerByName(playername);
		if(player != null){
			player.comparePassword(password);
		}
		return false;
	}

	@Override
	public boolean delete() {
		Player player = getLocalPlayer();
		if(player != null){
			
			return true;
		}
		return false;
	}

	@Override
	public String getUsername() {
		//TODO
		//try{
		Player player = getLocalPlayer();
	// } catch()
		if(player != null){
			return player.getPlayerName();
		}
		return null;
	}

	@Override
	public String getEmail() {
		//TODO
		return null;
	}

	private Player getLocalPlayer() {
		//TODO
		// load from local file
		// if file not exist
		// 		return null
		// else return Player
		return null;
	}

	private boolean saveLocalPlayer(Player player) {
		//TODO
		// load or create file
		// save player into the file
		return true;

	}

	private boolean checkValidString(String text) {
		return !text.equals("") && !(text == null);
	}
}
