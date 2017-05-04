package com.emptyshit.hsay.playerComponent;

import com.emptyshit.hsay.dataTypes.EmailType;

public class PlayerComponent implements PlayerComponentInterface {

	PlayerRepository playerRepository;

	public PlayerComponent(PlayerRepository playerRepository){
		this.playerRepository = playerRepository;
	}

	@Override
	public boolean register(String playername, String email, String password, String passwordConfirm) {
		if (!(checlValidString(playername) || checlValidString(email) || checlValidString(password)
				|| checlValidString(passwordConfirm))) {
			return false;
		}

		if (password.equals(passwordConfirm)) {
			Player player = new Player(playername, email, password);
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
			return false;
		}
	}

	@Override
	public boolean login(String playername, String password) {
		Player player = playerRepository.findUserbyPlayerName();
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
	}

	private Player getLocalPlayer() {
		//TODO
		// load from local file
		// if file not exist
		// 		return null
		// else return Player
	}

	private boolean saveLocalPlayer(Player player) {
		//TODO
		// load or create file
		// save player into the file

	}

	private boolean checkValidString(String string) {
		return !playername.equals("") && !playername == null;
	}
}
